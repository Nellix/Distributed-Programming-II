package it.polito.dp2.NFFG.sol1;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.sol1.jaxb.NffgServiceType;
import it.polito.dp2.NFFG.sol1.jaxb.NffgType;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;
import it.polito.dp2.NFFG.sol1.jaxb.TraversalPolicyType;

public class NffgVerifierExt implements it.polito.dp2.NFFG.NffgVerifier{

	private NffgServiceType verifier ;
	private Set<NffgReader> nffgs;
	private Set<PolicyReader> policies;
	
	public NffgVerifierExt(NffgServiceType verifierType) throws NffgVerifierException
	{
		this.verifier = verifierType;
		this.nffgs = new HashSet<>();
		this.policies = new HashSet<>();
		
		init();
		
	}
	
	private void init() throws NffgVerifierException {
		// TODO Auto-generated method stub
		NffgServiceType.Nffgs Nffgs = new NffgServiceType.Nffgs();
		Nffgs = verifier.getNffgs();
		
		for(NffgType n : Nffgs.getNffg())
		{
			NffgReaderExt nr = new NffgReaderExt(n);
			nffgs.add(nr);
			System.out.println("ADD nffg");
			for(ReachabilityPolicyType p : n.getPolicies().getReachabilityPolicy())
				if (this.getPolicies(p.getName()) != null) {
					throw new NffgVerifierException("Duplicated Policy Name");
				}else
					policies.add(new ReachabilityPolicyReaderExt(p,nr));
			
			for(TraversalPolicyType p : n.getPolicies().getTraversalPolicy())
				if (this.getPolicies(p.getName()) != null) {
					throw new NffgVerifierException("Duplicated Policy Name");
				}else
					policies.add(new TraversalPolicyReaderExt(p,nr));
		}
		
		
		
	}

	@Override
	public NffgReader getNffg(String arg0) {
		// TODO Auto-generated method stub
		for(NffgReader nffg : nffgs)
		if(nffg.getName().compareTo(arg0)==0)
			return nffg;
		return null;
	}

	@Override
	public Set<NffgReader> getNffgs() {
		// TODO Auto-generated method stub
		return nffgs;
	}

	@Override
	public Set<PolicyReader> getPolicies() {
		// TODO Auto-generated method stub
		if(policies.size()!=0)
			return policies;
		else
			return null;
	}

	@Override
	public Set<PolicyReader> getPolicies(String arg0) {
		// TODO Auto-generated method stub
		Set<PolicyReader> policy = new HashSet<>();
		
			for(PolicyReader p : policies)
				if(p.getNffg().getName().equals(arg0))
					policy.add(p);
			
			if(policy.size()!=0)
				return policies;
			else
				return null;
		}

	@Override
	public Set<PolicyReader> getPolicies(Calendar arg0) {
		// TODO Auto-generated method stub
		Set<PolicyReader> policy = new HashSet<>();
		
		for(PolicyReader p : policies)
			if(p.getResult().getVerificationTime().equals(arg0))
				policy.add(p);
		

		if(policy.size()!=0)
			return policies;
		else
			return null;
	}
}
