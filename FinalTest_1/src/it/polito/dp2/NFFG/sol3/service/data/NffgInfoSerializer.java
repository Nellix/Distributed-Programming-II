package it.polito.dp2.NFFG.sol3.service.data;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;


import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFFG.LinkReader;
import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.NffgVerifierFactory;
import it.polito.dp2.NFFG.NodeReader;
import it.polito.dp2.NFFG.PolicyReader;
import it.polito.dp2.NFFG.ReachabilityPolicyReader;
import it.polito.dp2.NFFG.TraversalPolicyReader;
import it.polito.dp2.NFFG.sol3.jaxb.FunctionalType;
import it.polito.dp2.NFFG.sol3.jaxb.LinkType;
import it.polito.dp2.NFFG.sol3.jaxb.NameEntityType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType.Nffgs;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType.Nodes;
import it.polito.dp2.NFFG.sol3.jaxb.NodeType;
import it.polito.dp2.NFFG.sol3.jaxb.NodeType.Links;
import it.polito.dp2.NFFG.sol3.jaxb.ObjectFactory;
import it.polito.dp2.NFFG.sol3.jaxb.PoliciesType;
import it.polito.dp2.NFFG.sol3.jaxb.ReachabilityPolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.TraversalPolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.VerificationResultType;

public class NffgInfoSerializer {
	
	private NffgVerifier verifier;
	private ObjectFactory object;
	private NffgServiceType serviceJAXB;



	public NffgInfoSerializer() throws NffgVerifierException {
		NffgVerifierFactory factory = NffgVerifierFactory.newInstance();
		verifier = factory.newNffgVerifier();
		this.object = new ObjectFactory();
		this.serviceJAXB = object.createNffgServiceType();
		CopyAll();
		
	}


	private void CopyAll() {
		// TODO Auto-generated method stub
	serviceJAXB.setNffgs(CopyNffgs());
	}


	private Nffgs CopyNffgs() {
		// TODO Auto-generated method stub
		
		NffgServiceType.Nffgs nffgs = new NffgServiceType.Nffgs();
		List<NffgType> nffglist = nffgs.getNffg() ;
		
		for(NffgReader nf : verifier.getNffgs())
		{
			NffgType nffg = new NffgType();
			nffg.setName(nf.getName());
			nffg.setUpdateTime(convertToXMLCalendar(nf.getUpdateTime()));
			
			nffg.setNodes(CopyNodes(nf.getNodes()));
			
			nffg.setPolicies(CopyPolicies(nf.getName()));
			
			nffglist.add(nffg);
		}
		
		return nffgs;
	}


	private PoliciesType CopyPolicies(String string) {
		// TODO Auto-generated method stub
		
		PoliciesType polis = new PoliciesType(); 
		List<ReachabilityPolicyType> Rpolicylist = polis.getReachabilityPolicy();
		List<TraversalPolicyType> Tpolicylist = polis.getTraversalPolicy();
		
		for(PolicyReader p : verifier.getPolicies(string))
		{

			String nffg = p.getNffg().getName();
	
			VerificationResultType v = null;
			if(p.getResult()!=null)
			{
				v = new VerificationResultType();
				v.setVerificationMessage(p.getResult().getVerificationResultMsg());
				v.setVerificationResult(p.getResult().getVerificationResult());
				v.setVerificationTime(convertToXMLCalendar(p.getResult().getVerificationTime()));
			}
			
			if(p instanceof TraversalPolicyReader)
			{
				TraversalPolicyReader t = (TraversalPolicyReader)p;
				TraversalPolicyType tr = new TraversalPolicyType();
				
				NameEntityType dst = new NameEntityType();
				dst.setName(((TraversalPolicyReader)p).getDestinationNode().getName());
				NameEntityType src = new NameEntityType();
				src.setName(((TraversalPolicyReader)p).getSourceNode().getName());

				tr.setName(t.getName());
				tr.setDstNode(dst);
				tr.setSrcNode(src);
				tr.setPolicyResult(t.isPositive());
				tr.setVerificationResult(v);
					
				TraversalPolicyType.TraversedFTypes types = new TraversalPolicyType.TraversedFTypes();
				
				List<FunctionalType> typeList = types.getFType();
				
				for(it.polito.dp2.NFFG.FunctionalType ft : t.getTraversedFuctionalTypes())
				{
					typeList.add(FunctionalType.valueOf(ft.name()));
				}
				
				tr.setTraversedFTypes(types);
			
				tr.setNffg(nffg);
				
				Tpolicylist.add(tr);
				//System.out.println("[Serializer] Aggiunta Traversal policy");

				
			}else if(p instanceof ReachabilityPolicyReader)
			{
				ReachabilityPolicyType reach = new ReachabilityPolicyType();
				NameEntityType dst = new NameEntityType();
				dst.setName(((ReachabilityPolicyReader)p).getDestinationNode().getName());
				NameEntityType src = new NameEntityType();
				src.setName(((ReachabilityPolicyReader)p).getSourceNode().getName());
				
				reach.setName(p.getName());

				reach.setPolicyResult(p.isPositive());

				reach.setVerificationResult(v);
				reach.setNffg(nffg);
				reach.setDstNode(dst);
				reach.setSrcNode(src);
				
				Rpolicylist.add(reach);
				
		//		System.out.println("[Serializer] Aggiunta Reachability policy");
			}
			
		}
		
		
		return polis;
	}


	private Nodes CopyNodes(Set<NodeReader> nodes) {
		// TODO Auto-generated method stub
		
		NffgType.Nodes Nodes = new NffgType.Nodes();
		List<NodeType> listNode = Nodes.getNode();
		
		for(NodeReader nd : nodes)
		{
			NodeType node = new NodeType();
			node.setName(nd.getName());
			node.setType(FunctionalType.valueOf(nd.getFuncType().name()));
			node.setLinks(Copylinks(nd.getLinks()));
			
			listNode.add(node);
		}
			
		return Nodes;
	}


	private Links Copylinks(Set<LinkReader> set) {
		// TODO Auto-generated method stub
		
		NodeType.Links links = new NodeType.Links();	
		List<LinkType> linklist = links.getLink();
		
		for(LinkReader lk : set)
		{
			LinkType link = new LinkType();
			link.setName(lk.getName());

			NameEntityType name = new NameEntityType();
			name.setName(lk.getDestinationNode().getName());
			
			link.setDestinationNode(name);
			
			linklist.add(link);
			
		}
		return links;
	}


	public static XMLGregorianCalendar convertToXMLCalendar(Calendar c) {
		XMLGregorianCalendar calendar;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(c.getTime());
		gc.setTimeZone(c.getTimeZone());
		try {
			calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (DatatypeConfigurationException e) {
			throw new Error(e);
		}
		return calendar;
	
		
	}
	

	public NffgServiceType getServiceJAXB() {
		return serviceJAXB;
	}




	public void setServiceJAXB(NffgServiceType serviceJAXB) {
		this.serviceJAXB = serviceJAXB;
	}



}
