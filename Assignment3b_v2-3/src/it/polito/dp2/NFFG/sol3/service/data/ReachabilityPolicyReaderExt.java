package it.polito.dp2.NFFG.sol3.service.data;


import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;
import it.polito.dp2.NFFG.sol3.jaxb.PolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.ReachabilityPolicyType;

public class ReachabilityPolicyReaderExt extends PolicyReaderExt implements ReachabilityPolicyReader {
	
	public ReachabilityPolicyReaderExt(ReachabilityPolicyType p, NffgReaderExt nr) throws NffgVerifierException {
		// TODO Auto-generated constructor stub
		super(p,nr);
	}


	public ReachabilityPolicyReaderExt(PolicyType p, NffgReader e) throws NffgVerifierException {
		// TODO Auto-generated constructor stub
		super(p,e);
	}


	@Override
	public NodeReader getDestinationNode() {
		// TODO Auto-generated method stub
		return nffg.getNode(((ReachabilityPolicyType)this.entity).getDstNode().getName().toString());
	}


	@Override
	public NodeReader getSourceNode() {
		// TODO Auto-generated method stub
		return nffg.getNode(((ReachabilityPolicyType)this.entity).getSrcNode().getName());
	}


}
