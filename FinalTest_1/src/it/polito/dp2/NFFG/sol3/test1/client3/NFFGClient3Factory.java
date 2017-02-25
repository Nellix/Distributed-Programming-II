package it.polito.dp2.NFFG.sol3.test1.client3;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import it.polito.dp2.NFFG.lab3.NFFGClientException;
import it.polito.dp2.NFFG.lab3.test1.NFFGClient3;

public class NFFGClient3Factory extends it.polito.dp2.NFFG.lab3.test1.NFFGClient3Factory{

	WebTarget target;

	@Override
	public NFFGClient3 newNFFGClient3() throws NFFGClientException {
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
		System.out.println("[CLIENT3] pre started");

		return new it.polito.dp2.NFFG.sol3.test1.client3.NFFGClient3(target);
	}

}
