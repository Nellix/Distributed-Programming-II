package it.polito.dp2.NFFG.sol3.client2;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.PoliciesType;
import it.polito.dp2.NFFG.sol3.jaxb.PolicyType;
import it.polito.dp2.NFFG.sol3.service.data.NffgReaderExt;
import it.polito.dp2.NFFG.sol3.service.data.PolicyReaderExt;



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
		PolicySet.clear();
		
		Set<NffgReader> set = new HashSet<>();
		set = getNffgs();
		
	    System.out.println("[CLIENT2] Scarico tutte le policy dal server");
	    
	    PoliciesType policies = target.path("Policies/")
	    					.request().accept(MediaType.APPLICATION_XML)
	    					.get(PoliciesType.class);
	
	    System.out.println("size reach "+policies.getReachabilityPolicy().size());
	    System.out.println("size trav "+policies.getTraversalPolicy().size());


	    for(PolicyType p : policies.getReachabilityPolicy())
			try {
				
				for(NffgReader e : set)
				{			
					if(e.getName().compareTo(p.getNffg())==0)
					{
						PolicySet.add(new PolicyReaderExt(p,e));

					}
				}
			} catch (NffgVerifierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    for(PolicyType p : policies.getTraversalPolicy())
				try {
				
					for(NffgReader e : set)
						if(e.getName().compareTo(p.getNffg())==0)
						{
							PolicySet.add(new PolicyReaderExt(p,e));
						}
					} catch (NffgVerifierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	    System.out.println("SIZE : "+PolicySet.size());
	    return PolicySet;
	    }
	

	@Override
	public Set<PolicyReader> getPolicies(String arg0) {
		// TODO Auto-generated method stub
		NffgReader nr = getNffg(arg0);
		
	
		Set<PolicyReader> set =  new CopyOnWriteArraySet<>();
		
	    System.out.println("[CLIENT2] Scarico tutte le policy di "+arg0);
	    
	    PoliciesType policies = target.path("Policies")
	    					.path("Nffgs")
	    					.path(arg0)
	    					.request().accept(MediaType.APPLICATION_XML)
	    					.get(PoliciesType.class);
	
	    
	    for(PolicyType p : policies.getReachabilityPolicy())
			try {
				set.add(new PolicyReaderExt(p,nr));
			} catch (NffgVerifierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    for(PolicyType p : policies.getTraversalPolicy())
				try {
					set.add(new PolicyReaderExt(p,nr));
				} catch (NffgVerifierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	    return set;
		
	    
	}

	@Override
	public Set<PolicyReader> getPolicies(Calendar arg0) {
		// TODO Auto-generated method stub
			
		Set<PolicyReader> set =  new CopyOnWriteArraySet<>();
		
	    System.out.println("[CLIENT2] Scarico tutte le policy con verification time  "+arg0);
	    
	    PoliciesType policies = target.path("Policies")
	    					.path("verificationTime")
	    					.path(String.valueOf(arg0.getTimeInMillis()))
	    					.request().accept(MediaType.APPLICATION_XML)
	    					.get(PoliciesType.class);
	
	    
	    for(PolicyType p : policies.getReachabilityPolicy())
			try {
				NffgReader nr = getNffg(p.getNffg());
				set.add(new PolicyReaderExt(p,nr));
			} catch (NffgVerifierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
	    for(PolicyType p : policies.getTraversalPolicy())
				try {
					NffgReader nr = getNffg(p.getNffg());
					set.add(new PolicyReaderExt(p,nr));
				} catch (NffgVerifierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	    return set;
	}

}
