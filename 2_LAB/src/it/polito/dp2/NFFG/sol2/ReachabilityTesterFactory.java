package it.polito.dp2.NFFG.sol2;

import java.net.URI;
import java.net.URISyntaxException;

import com.sun.jersey.api.client.Client;

import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NffgVerifierFactory;
import it.polito.dp2.NFFG.lab2.ReachabilityTester;
import it.polito.dp2.NFFG.lab2.ReachabilityTesterException;
import it.polito.dp2.NFFG.sol2.jaxrs.Localhost_Neo4JXMLRest;
import it.polito.dp2.NFFG.sol2.jaxrs.Localhost_Neo4JXMLRest.Resource;

public class ReachabilityTesterFactory extends it.polito.dp2.NFFG.lab2.ReachabilityTesterFactory{

	@Override
	public ReachabilityTester newReachabilityTester() throws ReachabilityTesterException {
		// TODO Auto-generated method stub
		NffgVerifier verifier = null ;
		Resource resource = null;
		
		NffgVerifierFactory factory = NffgVerifierFactory.newInstance();
		try {

			verifier = factory.newNffgVerifier();		
			String URL = System.getProperty("it.polito.dp2.NFFG.lab2.URL");
			Client client = Localhost_Neo4JXMLRest.createClient();
			resource  =  Localhost_Neo4JXMLRest.resource(client, new URI(URL));
		
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NffgVerifierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new it.polito.dp2.NFFG.sol2.ReachabilityTester(verifier,resource);		
	}

}
