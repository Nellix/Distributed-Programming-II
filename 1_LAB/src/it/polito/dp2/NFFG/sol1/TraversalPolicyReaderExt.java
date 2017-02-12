package it.polito.dp2.NFFG.sol1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFFG.FunctionalType;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.TraversalPolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.PolicyType;
import it.polito.dp2.NFFG.sol1.jaxb.TraversalPolicyType;

public class TraversalPolicyReaderExt extends PolicyReaderExt implements TraversalPolicyReader {

	
	private Set<FunctionalType> ft;
	
	public TraversalPolicyReaderExt(PolicyType p, NffgReaderExt nr) throws NffgVerifierException {
		super(p, nr);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.ft=new HashSet<FunctionalType>();
		
		for(it.polito.dp2.NFFG.sol1.jaxb.FunctionalType ft :((TraversalPolicyType)this.entity).getTraversedFTypes().getFType())
				this.ft.add(FunctionalType.valueOf(ft.name()));
		
	}

	@Override
	public NodeReader getDestinationNode() {
		// TODO Auto-generated method stub
		return nffg.getNode(((TraversalPolicyType)this.entity).getDstNode().getName());
	}

	@Override
	public NodeReader getSourceNode() {
		// TODO Auto-generated method stub
		return nffg.getNode(((TraversalPolicyType)this.entity).getSrcNode().getName());
	}

	@Override
	public Set<FunctionalType> getTraversedFuctionalTypes() {
		// TODO Auto-generated method stub
		return ft;
	}



	

}
