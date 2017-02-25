package it.polito.dp2.NFFG.sol3.service.data;

import it.polito.dp2.NFFG.NamedEntityReader;
import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.sol3.jaxb.NameEntityType;

public class NamedEntityExt implements NamedEntityReader{

	protected NameEntityType entity;
	
	
	public NamedEntityExt(NameEntityType entity) throws NffgVerifierException {
		if (entity == null)
			throw new NffgVerifierException("Entity is null");
		this.entity = entity;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return entity.getName();
	}

}
