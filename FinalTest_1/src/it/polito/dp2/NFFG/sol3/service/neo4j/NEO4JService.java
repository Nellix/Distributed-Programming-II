package it.polito.dp2.NFFG.sol3.service.neo4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import com.sun.jersey.api.client.Client;

import it.polito.dp2.NFFG.lab2.NoGraphException;
//import it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.PolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.ReachabilityPolicyType;
import it.polito.dp2.NFFG.sol3.service.jaxrs.Localhost_Neo4JXMLRest;

//import  it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.sol3.service.ServiceException;


public class NEO4JService {

	private static NEO4JService service = new NEO4JService();
	private Localhost_Neo4JXMLRest.Resource resource;
	private Map<String, NEO4JNffg> nffgMap;
	private Boolean init = false;
	
	static final String NAME_PROP = "name";
	static final String BELONG_RELATION = "belongs";
	static final String LINK_TYPE = "Link";
	static final String CORE_LABEL = "NFFG";
	static final String DEFAULT_URL = "http://localhost:8080/Neo4JXML/rest";
	
	
	public NEO4JService() {
		String URL = System.getProperty("it.polito.dp2.NFFG.lab3.NEO4JURL");
		if (URL == null) {
			URL = DEFAULT_URL;
		}
		Client client = Localhost_Neo4JXMLRest.createClient();
		try {
			resource = Localhost_Neo4JXMLRest.resource(client, new URI(URL));
		} catch (URISyntaxException ex) {
			resource = Localhost_Neo4JXMLRest.resource(client);
		}
		nffgMap = new HashMap<>();
		init = cleanGraph();
	}


	private Boolean cleanGraph() {
		// TODO Auto-generated method stub
		
		try{
			String result = resource.nodes().deleteAsXml(String.class);
			System.out.println("[LOADNffg]Delete ALL "+result);
			return true;
			
		} catch (WebApplicationException ex) {
			return false;
		}	
	}


	public static NEO4JService getService() {
		// TODO Auto-generated method stub
		return service;
	}
	
	
	public void loadNFFG(NffgType nffg) throws ServiceException   {
		// TODO Auto-generated method stub
	
		if(nffg == null)
			throw new NullPointerException("The name of Nffg is null");		
		else{
		
			synchronized(this)
			{
				if(!init)
					init = cleanGraph();
				if(!init)
					throw new ServiceException("Unable to clean nodes"); 
			}
			
				NEO4JNffg complete_nffg = new NEO4JNffg(nffg,resource);
				complete_nffg.loadNFFG();
				System.out.println("[SERVICE-Ne04j] Added new nffg");
				nffgMap.put(nffg.getName(),complete_nffg);
			}
		
	}


	public boolean testReachability(PolicyType p) throws  NoGraphException, ServiceException {
		// TODO Auto-generated method stub
		System.out.println("Policy "+p.getName()+"  Nffg "+p.getNffg());
		
		NEO4JNffg nffg = new NEO4JNffg();
				nffg = nffgMap.get(p.getNffg());
				
		//System.out.println("nffg "+nffg.ge);
		String dest = ((ReachabilityPolicyType)p).getDstNode().getName();
		String src = ((ReachabilityPolicyType)p).getSrcNode().getName();
		
		System.out.println("[SERVICE NEO4J] src "+ src +" dst "+dest);
		
		return  nffg.testReachability(src,dest);
	}

	
	
	
}
