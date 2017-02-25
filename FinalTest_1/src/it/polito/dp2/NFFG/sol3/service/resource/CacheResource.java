package it.polito.dp2.NFFG.sol3.service.resource;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.lab3.test1.Caching;
import it.polito.dp2.NFFG.sol3.jaxb.CachingType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType;
import it.polito.dp2.NFFG.sol3.jaxb.ObjectFactory;
import it.polito.dp2.NFFG.sol3.service.NFFGSService;
import it.polito.dp2.NFFG.sol3.service.ServiceException;


@Path("caching")
@Api(value = "caching", description = "Manage cache")
public class CacheResource {
	
	private NFFGSService service = NFFGSService.getService();
	private ObjectFactory object = new ObjectFactory();
	
	
	
	@GET
	@ApiOperation(value = "Retrieve cache", notes = "plaintext")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.TEXT_PLAIN})
	public String getCache() {
	
	
		AtomicBoolean cache = new AtomicBoolean();
		
		cache = service.getCache();
		
		System.out.println("[Service] GET cache "+cache.get());
		
		if(cache.get())
			return "enabled";
		else
			return "disabled";
	}
	
	
	@GET
	@ApiOperation(value = "Retrieve cache", notes = "plaintext")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<CachingType> getCacheXML() {

		AtomicBoolean cache = new AtomicBoolean();
		
		cache = service.getCache();
		
		System.out.println("[Service] GET cache "+cache.get());
		CachingType c = new CachingType();
		c.setStatus(cache.get());
		
		return object.createCache(c);
	}

	
	
	@POST
	@ApiOperation(value = "set chache", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_XML})
	public JAXBElement<CachingType> loadNffgService(JAXBElement<CachingType> cache) throws ServiceException  {
	
		
		synchronized(service.getCache())
		{
			service.setCache(cache.getValue());
		}
		
		return object.createCache(cache.getValue());
	}
	
}
