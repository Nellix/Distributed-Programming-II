package it.polito.dp2.NFFG.sol3.test1.client3;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.lab3.test1.Caching;
import it.polito.dp2.NFFG.sol3.jaxb.CachingType;

import it.polito.dp2.NFFG.sol3.jaxb.ObjectFactory;

public class NFFGClient3 implements it.polito.dp2.NFFG.lab3.test1.NFFGClient3{
	
	WebTarget target ;
	private ObjectFactory object;

	public NFFGClient3(WebTarget target) {
		// TODO Auto-generated constructor stub
		this.target = target;
		this.object = new ObjectFactory();
		System.out.println("[CLIENT3] started");
	}

	@Override
	public void setCaching(Caching c) throws ServiceException {
		// TODO Auto-generated method stub
		
	CachingType cache = new CachingType();
		
		if(c.compareTo(Caching.DISABLED)==0)
			cache.setStatus(false);
		else
			cache.setStatus(true);
	
		JAXBElement<CachingType> xml = object.createCache(cache);
		try{
		 cache = target.path("caching")
				.request().accept(MediaType.APPLICATION_XML)
				.post(Entity.entity(xml,MediaType.APPLICATION_XML),CachingType.class);
		 
		 System.out.println("[CLIENT3] Cache setted to "+c.name());
		}catch (Exception ex) {
			throw new ServiceException("An error occured during the loading of the nffg", ex);		
		}
		
	}

	@Override
	public Caching getCaching() throws ServiceException {
		// TODO Auto-generated method stub
		
		CachingType cache = new CachingType();
		
		try{
		 cache = target.path("caching")
				.request().accept(MediaType.APPLICATION_XML)
				.get(CachingType.class);
		 
		 System.out.println("[CLIENT3] Cache getted is "+cache.isStatus());

		}catch (Exception ex) {
			throw new ServiceException("An error occured during the loading of the nffg", ex);		
		}
		if(cache.isStatus())
			return Caching.ENABLED;
		else
			return Caching.DISABLED;
		
	}

}