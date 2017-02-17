package it.polito.dp2.NFFG.sol3.service.neo4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

import it.polito.dp2.NFFG.lab2.NoGraphException;
import it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.sol3.jaxb.LinkType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.NodeType;
//import it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.sol3.service.jaxrs.Localhost_Neo4JXMLRest.Resource;
import it.polito.dp2.NFFG.sol3.service.jaxrs.Node;
import it.polito.dp2.NFFG.sol3.service.jaxrs.Paths;
import it.polito.dp2.NFFG.sol3.service.jaxrs.Property;
import it.polito.dp2.NFFG.sol3.service.jaxrs.Relationship;

public class NEO4JNffg {
	
	private Resource resource ;
	private Map<String,String> nodemap;
	private NffgType nffg;

	public NEO4JNffg(NffgType nffg, Resource resource) {
		// TODO Auto-generated constructor stub
		this.resource = resource;
		this.nodemap = new ConcurrentHashMap<>();
		this.nffg =  new NffgType();
		this.nffg = nffg;
		
	}
	
	public NEO4JNffg() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, String> getNodemap() {
		return nodemap;
	}

	public void setNodemap(Map<String, String> nodemap) {
		this.nodemap = nodemap;
	}

	public void loadNFFG() {
		// TODO Auto-generated method stub
		
			NffgType.Nodes nodes = nffg.getNodes();
		
			System.out.println("[loadNFFGNodes] Carico l'nffg. #Nodi : "+nodes.getNode().size());

			for(NodeType  node : nodes.getNode())
			{
				Node n = new Node();
				Property p = new Property();
				p.setName(NEO4JService.NAME_PROP);
				p.setValue(node.getName());
				n.getProperty().add(p);
				
			try{	
				Node id = resource.node().postXmlAsNode(n);
				nodemap.put(node.getName(), id.getId());
				
			} catch (WebApplicationException ex) {
				nodemap = null;
				//throw new ServiceException("Unable to load node "+node.getName()+ "\"", ex);
			}
		}
		
			for(NodeType no : nodes.getNode())
			{
				NodeType.Links links = no.getLinks();
				
				for(LinkType link : links.getLink())
				{
					Relationship r = new Relationship();
					r.setType("Link");
					
					String idSrc = nodemap.get(no.getName());
					String idDst = nodemap.get(link.getDestinationNode().getName());
					
					r.setDstNode(idDst);
					r.setSrcNode(idSrc);
			try{		
					resource.nodeNodeidRelationship(idSrc).postXmlAsRelationship(r);	
					
				} catch (WebApplicationException ex) {

					nodemap = null;
				//	throw new ServiceException("Unable to load relation between \"" + no.getName() +" "+idSrc+"  "+ "\" and \"" + link.getDestinationNode().getName() +" "+idDst+" "+ "\"", ex);
				}
				}	
			}
			
				
	}

	public boolean testReachability(String src, String dest) throws  NoGraphException, ServiceException {
		// TODO Auto-generated method stub

		System.out.println("[testReachability] Source Node : "+src);
		System.out.println("[testReachability] Dest   Node : "+dest);
		
		Paths path = null ;
		
		String srcID = null, destID = null;	
		srcID = nodemap.get(src);
		destID = nodemap.get(dest);
				
		if(srcID==null && destID==null)
		{
			throw new NotFoundException("ID NOT FOUND");
		}else
		{
		try{
					
				path = resource.nodeNodeidPaths(srcID).getAsPaths(destID);		
				System.out.println("#Paths : "+path.getPath().size());
			
			} catch (WebApplicationException ex) {
				throw new ServiceException("Error finding path between \"" + src + "\" and \"" + dest + "\"", ex);
			}
			
		if(path.getPath().size() > 0)
    	{
    		System.out.println("[testReachability] REACHABILITY");
    		return true;
    	}else
    	{
    		System.out.println("[testReachability] Not REACHABILITY");
    		return false;
    	}			
	}
	}

}
