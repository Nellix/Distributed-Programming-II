package it.polito.dp2.NFFG.sol3.client1;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NffgVerifierFactory;
import it.polito.dp2.NFFG.lab3.NFFGClient;
import it.polito.dp2.NFFG.lab3.NFFGClientException;
import it.polito.dp2.NFFG.sol3.service.data.NffgInfoSerializer;


public class NFFGClientFactory extends it.polito.dp2.NFFG.lab3.NFFGClientFactory{

	WebTarget target;
	NffgInfoSerializer serializer ;
	
	@Override
	public NFFGClient newNFFGClient() throws NFFGClientException {
		// TODO Auto-generated method stub
		
		Client client = ClientBuilder.newClient();
		String URL = System.getProperty("it.polito.dp2.NFFG.lab3.URL");
		if (URL == null) {
			URL = "http://localhost:8080/NffgService/rest/";
		}
		
		try {
			target = client.target(new URI(URL));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			serializer =  new NffgInfoSerializer();
		} catch (NffgVerifierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new NFFGClientImp(serializer,target);
		
		
	}

}
