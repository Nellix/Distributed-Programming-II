package it.polito.dp2.NFFG.sol3.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.NotFoundException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.lab2.NoGraphException;
//import it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType.Nffgs;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.PoliciesType;
import it.polito.dp2.NFFG.sol3.jaxb.PolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.ReachabilityPolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.TraversalPolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.VerificationResultType;
import it.polito.dp2.NFFG.sol3.service.neo4j.NEO4JService;

import it.polito.dp2.NFFG.sol3.service.ServiceException;


public class NFFGSService {

	Map <String,NffgType> mapNffg = NFFGSDB.getMapNffg();
	Map <String,PolicyType> mapPolicy = NFFGSDB.getMapPolicy();
	
	private static NFFGSService service = new NFFGSService();
	private NEO4JService neo4j = NEO4JService.getService(); 
	
	public static NFFGSService getService() {
		return service;
	}

	
	public void loadNffgService(NffgServiceType nffgservice) throws  NffgVerifierException, ServiceException    {
		// TODO Auto-generated method stub
				
		mapNffg.clear();
		mapPolicy.clear();
		
		NffgServiceType.Nffgs nffgs = nffgservice.getNffgs();
		PoliciesType policies = new PoliciesType();
	
		for(NffgType n : nffgs.getNffg())
		{
			policies = n.getPolicies();
			
			for(Object p : policies.getReachabilityPolicy())
			{
				synchronized(mapPolicy)
				{
					addReachabilityPolicy(p);
				}			
			}
			
			for(Object p : policies.getTraversalPolicy())
			{
				synchronized(mapPolicy)
				{
					addTraversalPolicy(p);
				}
			}
		
				addNffg(n);
			
			System.out.println("[SERVICE] Added new nffg");
			neo4j.loadNFFG(n);
		}
		
		
	}


	private void addNffg(NffgType n) throws NffgVerifierException  {
		// TODO Auto-generated method stub
		if(mapNffg.containsKey(n.getName()))
			throw new NffgVerifierException("Nffg already present");
		else
		{
			mapNffg.put(n.getName(),n);
			System.out.println("ADDEDD "+n.getName());
		}
	}


	private void addTraversalPolicy(Object p) throws NffgVerifierException {
		// TODO Auto-generated method stub
		if(mapPolicy.containsKey(((TraversalPolicyType)p).getName()))
			throw new NffgVerifierException("Policy already present");
		else
		{
	//		System.out.println("ADDEDD policy T");
			mapPolicy.put(((TraversalPolicyType)p).getName(),((TraversalPolicyType)p));
		}
	}


	private void addReachabilityPolicy(Object p) throws NffgVerifierException {
		// TODO Auto-generated method stub
		
		if(mapPolicy.containsKey(((ReachabilityPolicyType)p).getName()))
			throw new NffgVerifierException("Policy already present");
		else
		{
			mapPolicy.put(((ReachabilityPolicyType)p).getName(),((ReachabilityPolicyType)p));
	//		System.out.println("ADDEDD R policy");
		}
	}


	public 	Map <String,NffgType> getMapNffg() {
		// TODO Auto-generated method stub
		return mapNffg;
	}


	public NffgType loadSingleNffg(NffgType n) throws NffgVerifierException, ServiceException {
		// TODO Auto-generated method stub
		if(mapNffg.containsKey(n.getName()))
			throw new NffgVerifierException(); 
		else
		{
			mapNffg.put(n.getName(), n);
			neo4j.loadNFFG(n);
		}
		return n;
		
	}


	public Nffgs getALLNffg() {
		// TODO Auto-generated method stub
		Nffgs data = new Nffgs();
		Set<NffgType> set = new HashSet<>(mapNffg.values());
		System.out.println("[SERVICE] #Nffg : "+set.size());
		List<NffgType >set1 = data.getNffg();

		for(NffgType nffg : set)
			set1.add(nffg);
		
		return data;
		

		
	}


	public 	Map <String,PolicyType> getMapPolicy() {
		// TODO Auto-generated method stub
		return mapPolicy;
	}


	public void loadSinglePolicy(ReachabilityPolicyType reachabilityPolicyType) throws NffgVerifierException {
		// TODO Auto-generated method stub
		
			if(mapPolicy.containsKey(reachabilityPolicyType.getName()))
				throw new NffgVerifierException("Policy already present in the service");
			else
			{
				//System.out.println("[SERVICE] Policy Added");
				mapPolicy.put(reachabilityPolicyType.getName(), reachabilityPolicyType);
			}		
	}


	public PoliciesType getALLPolicies() {
		// TODO Auto-generated method stub
		
		Collection<PolicyType> c = mapPolicy.values();
		PoliciesType polis = new PoliciesType();
		
		for(PolicyType p : c)
		{
			if(p instanceof TraversalPolicyType)
				polis.getTraversalPolicy().add((TraversalPolicyType)p);
			else
				polis.getReachabilityPolicy().add((ReachabilityPolicyType)p);
		}
			
		return polis;
	}


	public PolicyType deletePolicy(String policyName) {
		// TODO Auto-generated method stub
		return mapPolicy.remove(policyName);
	}


	public VerificationResultType testReachabilityPolicy(String name) throws NoGraphException, ServiceException {
		// TODO Auto-generated method stub
			PolicyType p = mapPolicy.get(name);
			
			boolean result = neo4j.testReachability(p);
			
			VerificationResultType r = new VerificationResultType();
			r.setVerificationResult(result);
			r.setVerificationMessage("Policy verification result : "+result);
			
			XMLGregorianCalendar calendar;
			GregorianCalendar gc = new GregorianCalendar();
			Calendar c = new GregorianCalendar();
			gc.setTime(c.getTime());
			gc.setTimeZone(c.getTimeZone());
			try {
				calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			} catch (DatatypeConfigurationException e) {
				throw new Error(e);
			}
			
			r.setVerificationTime(calendar);
			System.out.println("[SERVICE] Tested "+p.getName()+" at time "+r.getVerificationTime());
			p.setVerificationResult(r);
			mapPolicy.replace(p.getName(),p);
				
		return r;
	}


	public PoliciesType getPoliciesofNffg(String name) {
		// TODO Auto-generated method stub
		Collection<PolicyType> c = mapPolicy.values();
		PoliciesType polis = new PoliciesType();
		
		for(PolicyType p : c)
		{
			if(p.getNffg().compareTo(name)==0)
				if(p instanceof TraversalPolicyType)
					polis.getTraversalPolicy().add((TraversalPolicyType)p);
				else
					polis.getReachabilityPolicy().add((ReachabilityPolicyType)p);
		}
			
		return polis;
	}


	public PoliciesType getPoliciesByTime(Long time) {
		// TODO Auto-generated method stub
		PoliciesType policies = new PoliciesType();
		
		for(PolicyType p : mapPolicy.values())
			if(p.getVerificationResult()!=null)
				if(p.getVerificationResult().getVerificationTime().toGregorianCalendar().getTimeInMillis() >= time)
					if(p instanceof TraversalPolicyType)
						policies.getTraversalPolicy().add((TraversalPolicyType)p);
					else
						policies.getReachabilityPolicy().add((ReachabilityPolicyType)p);

					
				return policies;
	}
	
	
}

