package it.polito.dp2.NFFG.sol1;

import java.util.List;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol1.jaxb.NffgType;
import it.polito.dp2.NFFG.sol1.jaxb.NffgType.Policies;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;

public class ReachabilityPolicyReaderExt extends PolicyReaderExt implements ReachabilityPolicyReader {
	
	private ReachabilityPolicyType reach ;
	
	
	public ReachabilityPolicyReaderExt(ReachabilityPolicyType p, NffgReaderExt nr) throws NffgVerifierException {
		// TODO Auto-generated constructor stub
		super(p,nr);
	}



	@Override
	public NodeReader getDestinationNode() {
		// TODO Auto-generated method stub
		return nffg.getNode(((ReachabilityPolicyType)this.entity).getDstNode().getName());
	}


	@Override
	public NodeReader getSourceNode() {
		// TODO Auto-generated method stub
		return nffg.getNode(((ReachabilityPolicyType)this.entity).getSrcNode().getName());
	}


}
