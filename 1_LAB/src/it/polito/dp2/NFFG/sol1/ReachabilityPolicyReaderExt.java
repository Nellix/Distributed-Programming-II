package it.polito.dp2.NFFG.sol1;


import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;
import it.polito.dp2.NFFG.sol1.jaxb.ReachabilityPolicyType;

public class ReachabilityPolicyReaderExt extends PolicyReaderExt implements ReachabilityPolicyReader {
	
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
