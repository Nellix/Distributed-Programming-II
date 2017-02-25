package it.polito.dp2.NFFG.sol3.service.data;

import java.util.Calendar;

import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.VerificationResultReader;
import it.polito.dp2.NFFG.sol3.jaxb.VerificationResultType;

public class VerificationResultReaderExt implements VerificationResultReader {

	private VerificationResultType result;
	private PolicyReader policy;
	
	public VerificationResultReaderExt(VerificationResultType verificationResult, PolicyReader entity) {
		// TODO Auto-generated constructor stub
		this.result = verificationResult;
		this.policy = entity;
		
		
	}

	@Override
	public PolicyReader getPolicy() {
		// TODO Auto-generated method stub
		return policy;
	}

	@Override
	public Boolean getVerificationResult() {
		// TODO Auto-generated method stub
		return result.isVerificationResult();
	}

	@Override
	public String getVerificationResultMsg() {
		// TODO Auto-generated method stub
		return result.getVerificationMessage();
	}

	@Override
	public Calendar getVerificationTime() {
		// TODO Auto-generated method stub
		return result.getVerificationTime().toGregorianCalendar();
	}

}
