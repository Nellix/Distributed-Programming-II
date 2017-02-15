package it.polito.dp2.NFFG.sol3.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.lab2.ServiceException;
import it.polito.dp2.NFFG.lab2.UnknownNameException;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType.Nffgs;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.ObjectFactory;
import it.polito.dp2.NFFG.sol3.jaxb.PolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.ReachabilityPolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.TraversalPolicyType;
import it.polito.dp2.NFFG.sol3.service.neo4j.NEO4JService;


public class NFFGSService {

	Map <String,NffgType> mapNffg = NFFGSDB.getMapNffg();
	Map <String,PolicyType> mapPolicy = NFFGSDB.getMapPolicy();
	
	private static NFFGSService service = new NFFGSService();
	private NEO4JService neo4j = NEO4JService.getService(); 
	
	public static NFFGSService getService() {
		return service;
	}

	
	public void loadNffgService(NffgServiceType nffgservice) throws it.polito.dp2.NFFG.sol3.service.ServiceException    {
		// TODO Auto-generated method stub
				
		NffgServiceType.Nffgs nffgs = nffgservice.getNffgs();
		NffgType.Policies policies = new NffgType.Policies();
	
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
		
			synchronized(mapNffg){
				addNffg(n);
			}
			System.out.println("[SERVICE] Added new nffg");
			neo4j.loadNFFG(n);
		}
		
		
	}


	private void addNffg(NffgType n) {
		// TODO Auto-generated method stub
		
		mapNffg.put(n.getName(),n);
		
	}


	private void addTraversalPolicy(Object p) {
		// TODO Auto-generated method stub
		
		mapPolicy.put(((TraversalPolicyType)p).getName(),((TraversalPolicyType)p));

	}


	private void addReachabilityPolicy(Object p) {
		// TODO Auto-generated method stub
		
		mapPolicy.put(((ReachabilityPolicyType)p).getName(),((ReachabilityPolicyType)p));
		
	}


	public 	Map <String,NffgType> getMapNffg() {
		// TODO Auto-generated method stub
		return mapNffg;
	}


	public NffgType loadSingleNffg(NffgType n) throws it.polito.dp2.NFFG.sol3.service.ServiceException {
		// TODO Auto-generated method stub
		if(mapNffg.containsKey(n.getName()))
			return null; 
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
	
	
}

