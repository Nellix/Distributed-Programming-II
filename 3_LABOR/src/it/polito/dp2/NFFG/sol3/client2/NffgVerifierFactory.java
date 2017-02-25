package it.polito.dp2.NFFG.sol3.client2;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.sol3.client1.NFFGClientImp;
import it.polito.dp2.NFFG.sol3.service.data.NffgInfoSerializer;

public class NffgVerifierFactory extends it.polito.dp2.NFFG.NffgVerifierFactory{

	WebTarget target;
	
	@Override
	public NffgVerifier newNffgVerifier() throws NffgVerifierException {
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
	
		
		
		return new NffgVerifierImpl(target);
	}

}
