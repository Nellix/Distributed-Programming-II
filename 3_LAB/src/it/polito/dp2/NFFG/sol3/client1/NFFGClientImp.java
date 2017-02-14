package it.polito.dp2.NFFG.sol3.client1;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.sun.research.ws.wadl.Response;

import it.polito.dp2.NFFG.NffgReader;
import it.polito.dp2.NFFG.NffgVerifier;
import it.polito.dp2.NFFG.lab3.AlreadyLoadedException;
import it.polito.dp2.NFFG.lab3.NFFGClient;
import it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.lab3.UnknownNameException;
import it.polito.dp2.NFFG.sol3.client1.data.NffgInfoSerializer;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.ObjectFactory;

public class NFFGClientImp implements NFFGClient {
	
	private NffgInfoSerializer serializer ;
	private WebTarget target;
	private ObjectFactory object;

	public NFFGClientImp(NffgInfoSerializer serializer, WebTarget target) {
		// TODO Auto-generated constructor stub
		
		this.target = target;
		this.serializer = serializer;
	}

	@Override
	public void loadNFFG(String name) throws UnknownNameException, AlreadyLoadedException, ServiceException {
		// TODO Auto-generated method stub
		
		NffgType nffg = ClientFunc.findNFFGbyName(name,serializer.getServiceJAXB().getNffgs().getNffg());
		
		if(name == null || nffg == null)
			throw new UnknownNameException("The nffg with name \"" + name + "\" does not exists"); 
		
		JAXBElement<NffgType> xml =  object.createNffg(nffg);
		
		try{
		 
		NffgType response = target.path("Nffgs/"+name)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(xml,MediaType.APPLICATION_XML),NffgType.class);
  
		System.out.println("[CLIENT1] Caricato Nffg "+xml.getName());
		
	  	}catch (WebApplicationException ex) {
	  		
			if (ex.getResponse().getStatus() == 403)
				throw new AlreadyLoadedException("Nffg already loaded in the service", ex);
			if (ex.getResponse().getStatus() == 400)
				throw new ServiceException("Nffg malformed", ex);
			else
				throw new ServiceException("Nffg not loaded, something went wrong", ex);
	
	  	} catch (Exception ex) {
	  		
			throw new ServiceException("An error occured during the loading of the nffg", ex);
		}
		
	}

	@Override
	public void loadAll() throws AlreadyLoadedException, ServiceException {
		// TODO Auto-generated method stub
		JAXBElement<NffgServiceType> xml =  object.createNffgService(serializer.getServiceJAXB());
		
		try{
		 
			NffgServiceType response = target.path("Nffgs/")
					.request(MediaType.APPLICATION_XML)
					.post(Entity.entity(xml,MediaType.APPLICATION_XML),NffgServiceType.class);
  
		System.out.println("[CLIENT1] Caricato NffgService. #Nffg "+xml.getValue().getNffgs().getNffg().size());
		
	  	}catch (WebApplicationException ex) {
	  		
			if (ex.getResponse().getStatus() == 403)
				throw new AlreadyLoadedException("Nffg already loaded in the service", ex);
			if (ex.getResponse().getStatus() == 400)
				throw new ServiceException("Nffg malformed", ex);
			else
				throw new ServiceException("Nffg not loaded, something went wrong", ex);
	
	  	} catch (Exception ex) {
	  		
			throw new ServiceException("An error occured during the loading of the nffg", ex);
		}
	}

	@Override
	public void loadReachabilityPolicy(String name, String nffgName, boolean isPositive, String srcNodeName,
			String dstNodeName) throws UnknownNameException, ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void unloadReachabilityPolicy(String name) throws UnknownNameException, ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean testReachabilityPolicy(String name) throws UnknownNameException, ServiceException {
		// TODO Auto-generated method stub
		return false;
	}

}
