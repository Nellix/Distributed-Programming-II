package it.polito.dp2.NFFG.sol2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.WebApplicationException;

import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.lab2.NoGraphException;
import it.polito.dp2.NFFG.lab2.ServiceException;
import it.polito.dp2.NFFG.lab2.UnknownNameException;
import it.polito.dp2.NFFG.sol2.jaxrs.Localhost_Neo4JXMLRest.Resource;
import it.polito.dp2.NFFG.sol2.jaxrs.Nodes.Node;
import it.polito.dp2.NFFG.sol2.jaxrs.Paths;
import it.polito.dp2.NFFG.sol2.jaxrs.Property;
import it.polito.dp2.NFFG.sol2.jaxrs.Relationship;

public class ReachabilityTester implements it.polito.dp2.NFFG.lab2.ReachabilityTester{

	private NffgVerifier verifier ;
	private Resource resource ;
	private String current_nffg;
	private Map<String,String> nodemap;
	
	
	
	public ReachabilityTester(NffgVerifier verifier, Resource resource) {
		// TODO Auto-generated constructor stub
	this.verifier = verifier;
	this.resource = resource;
	current_nffg = null;
	this.nodemap = new HashMap<String,String>();
	
	
	}

	@Override
	public void loadNFFG(String name) throws UnknownNameException, ServiceException {
		// TODO Auto-generated method stub
		String idDst=null,idSrc=null;
		NffgReader nffg = verifier.getNffg(name);
		
		if(nffg == null)
		{
			nodemap=null;
			current_nffg = null;
			throw new UnknownNameException("The NFFG with name \"" + name + "\" does not exist.");		
		}else{
		
				current_nffg = name;
				
			try{
				
				//delete all
				String result = resource.nodes().deleteAsXml(String.class);
				System.out.println("[LOADNffg]Delete ALL "+result);
			} catch (WebApplicationException ex) {
				current_nffg = null;
				nodemap = null;
				throw new ServiceException("Unable to delete nodes", ex);
			}	
				
			System.out.println("[loadNFFGNodes] Carico l'nffg. #Nodi : "+nffg.getNodes().size());

			for(NodeReader node : nffg.getNodes())
			{
				it.polito.dp2.NFFG.sol2.jaxrs.Node n = new it.polito.dp2.NFFG.sol2.jaxrs.Node();
				Property p = new Property();
				p.setName("name");
				p.setValue(node.getName());
				n.getProperty().add(p);
				
			try{	
				it.polito.dp2.NFFG.sol2.jaxrs.Node id = resource.node().postXmlAsNode(n);
				nodemap.put(node.getName(), id.getId());
				
			} catch (WebApplicationException ex) {

				current_nffg = null;
				nodemap = null;
				throw new ServiceException("Unable to load node "+node.getName()+ "\"", ex);
			}
			
			}
			
		
			for(NodeReader node : nffg.getNodes())
			{
				for(LinkReader link : node.getLinks())
				{
					Relationship r = new Relationship();
					r.setType("Link");
					
					idSrc = nodemap.get(link.getSourceNode().getName());
					idDst = nodemap.get(link.getDestinationNode().getName());
					
					r.setDstNode(idDst);
					r.setSrcNode(idSrc);
			try{		
					resource.nodeNodeidRelationship(idSrc).postXmlAsRelationship(r);	
					
				} catch (WebApplicationException ex) {

					current_nffg = null;
					nodemap = null;
					throw new ServiceException("Unable to load relation between \"" + link.getSourceNode().getName() + "\" and \"" + link.getDestinationNode().getName() + "\"", ex);
				}
				}
			}
				}
		
	}

	@Override
	public boolean testReachability(String srcName, String destName)
			throws UnknownNameException, ServiceException, NoGraphException {
		// TODO Auto-generated method stub

		System.out.println("[testReachability] Source Node : "+srcName);
		System.out.println("[testReachability] Dest   Node : "+destName);
		
		Paths path ;
		
		String srcID = null, destID = null;	
		srcID = nodemap.get(srcName);
		destID = nodemap.get(destName);
				
		if(srcID==null && destID==null)
		{
			throw new UnknownNameException("ID NOT FOUND");
		}else
		{
		try{
					
				path = resource.nodeNodeidPaths(srcID).getAsPaths(destID);		
				System.out.println("#Paths : "+path.getPath().size());
			
			} catch (WebApplicationException ex) {
				throw new ServiceException("Error finding path between \"" + srcName + "\" and \"" + destName + "\"", ex);
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

	@Override
	public String getCurrentGraphName() {
		// TODO Auto-generated method stub
		return current_nffg;
	}

}
