package it.polito.dp2.NFFG.sol3.client2;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.service.data.NffgReaderExt;


public class NffgVerifierImpl implements it.polito.dp2.NFFG.NffgVerifier{

	private WebTarget target ;
	private Set<NffgReader> NffgSet;
	private Set<PolicyReader> PolicySet;
	
	public NffgVerifierImpl(WebTarget target) {
		// TODO Auto-generated constructor stub
		this.target = target;
		this.NffgSet = new CopyOnWriteArraySet<>();
		this.PolicySet = new CopyOnWriteArraySet<>();
	}

	@Override
	public NffgReader getNffg(String arg0) {
		// TODO Auto-generated method stub
		NffgReaderExt n = null;
		if(arg0==null)
			throw new NullPointerException();
		else
		{
	    	NffgType nffg = target.path("Nffgs")
		    						.path(arg0)
		    						.request().accept(MediaType.APPLICATION_XML)
		    						.get(NffgType.class);
			
	    	try {
				n = new NffgReaderExt(nffg);
		
	    	} catch (NffgVerifierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		    		System.out.println("[CLIENT2] Scarico nffg "+nffg.getName()+"dal server");
		   
		    		return n;
		}
		
		
	}

	@Override
	public Set<NffgReader> getNffgs(){
		// TODO Auto-generated method stub
		
		NffgSet.clear();
		
	    System.out.println("[CLIENT2] Scarico tutti gli nffg dal server");
	    
	    	NffgServiceType nffgs = target.path("Nffgs/")
	    						.request().accept(MediaType.APPLICATION_XML)
	    						.get(NffgServiceType.class);

	    	
	    	NffgServiceType.Nffgs Nffgs = new NffgServiceType.Nffgs ();
	    	Nffgs = nffgs.getNffgs();
	    	
	    	for(NffgType n : Nffgs.getNffg() )
				try {
					NffgSet.add(new NffgReaderExt(n));
				} catch (NffgVerifierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	
		return NffgSet;
	}

	@Override
	public Set<PolicyReader> getPolicies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PolicyReader> getPolicies(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PolicyReader> getPolicies(Calendar arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
