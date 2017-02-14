package it.polito.dp2.NFFG.sol3.client1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.dp2.NFFG.sol3.jaxb.NffgType;

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

}
