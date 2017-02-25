package it.polito.dp2.NFFG.sol3.client1;

import java.util.List;


import it.polito.dp2.NFFG.sol3.jaxb.NameEntityType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.ReachabilityPolicyType;

public class ClientFunc {

	public static NffgType findNFFGbyName(String name, List<NffgType> list) {
		// TODO Auto-generated method stub
		 
		 for(NffgType n : list)
		 {
			 if(n.getName().compareTo(name)==0)
				 return n;
		 }
		
		return null;
	}

	public static ReachabilityPolicyType createRechabilityPolicy(String name, String nffgName, boolean isPositive,
			String srcNodeName, String dstNodeName) {
		// TODO Auto-generated method stub
		ReachabilityPolicyType policy = new ReachabilityPolicyType();
		policy.setName(name);
		policy.setPolicyResult(isPositive);
		NameEntityType src = new NameEntityType();
		src.setName(srcNodeName);
		NameEntityType dest = new NameEntityType();
		dest.setName(dstNodeName);
		policy.setSrcNode(src);
		policy.setDstNode(dest);
		policy.setNffg(nffgName);
		
		return policy;
	}

}
