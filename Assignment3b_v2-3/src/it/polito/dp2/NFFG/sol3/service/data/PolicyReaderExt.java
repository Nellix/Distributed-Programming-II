package it.polito.dp2.NFFG.sol3.service.data;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol3.jaxb.PolicyType;


public class PolicyReaderExt extends NamedEntityExt implements PolicyReader{

	protected NffgReader nffg;
	
	public PolicyReaderExt(PolicyType p, NffgReader e) throws NffgVerifierException {
		// TODO Auto-generated constructor stub
		super(p);
		this.nffg = e;
	}



	@Override
	public NffgReader getNffg() {
		// TODO Auto-generated method stub
		return nffg;
	}

	@Override
	public VerificationResultReader getResult() {
		// TODO Auto-generated method stub
		if(((PolicyType)this.entity).getVerificationResult() != null)
			return new VerificationResultReaderExt(((PolicyType)this.entity).getVerificationResult(),this);
		return null;
	}

	@Override
	public Boolean isPositive() {
		// TODO Auto-generated method stub
		return ((PolicyType)this.entity).isPolicyResult();	
	}



}
