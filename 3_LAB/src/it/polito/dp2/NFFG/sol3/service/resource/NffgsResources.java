package it.polito.dp2.NFFG.sol3.service.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import it.polito.dp2.NFFG.NffgVerifierException;
import it.polito.dp2.NFFG.lab3.ServiceException;
//import it.polito.dp2.NFFG.lab3.ServiceException;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType;
import it.polito.dp2.NFFG.sol3.jaxb.NffgServiceType.Nffgs;
import it.polito.dp2.NFFG.sol3.jaxb.NffgType;
import it.polito.dp2.NFFG.sol3.jaxb.ObjectFactory;
import it.polito.dp2.NFFG.sol3.service.NFFGSService;


@Path("/Nffgs")
@Api(value = "/Nffgs", description = "Manage the stored nffgs")
public class NffgsResources {

	private NFFGSService service = NFFGSService.getService();
	private ObjectFactory object = new ObjectFactory();
	
	public NffgsResources(){}
	
	
	@GET
	@ApiOperation(value = "Retrieve all stored nffgs", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<NffgServiceType> getALLNffgs() {
	
		System.out.println("[Service] GET ALL Nffgs");
		
		Nffgs all = new Nffgs();
		all= service.getALLNffg();
		NffgServiceType out = new NffgServiceType();
		out.setNffgs(all);
				
		if(all.getNffg().size()==0)
			throw new NotFoundException("No Nffgs stored in the server");
		
		return object.createNffgService(out);
	}
	
	@POST
	@ApiOperation(value = "load a complete NffgService", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_XML})
	public JAXBElement<NffgServiceType> loadNffgService(JAXBElement<NffgServiceType> nffg) throws ServiceException  {
		
		System.out.println("[Service] Post  ALL service ");

		NffgServiceType nffgservice = nffg.getValue();

	try{
		synchronized(service.getMapNffg())
		{
				service.loadNffgService(nffgservice);
		}
	} catch (NffgVerifierException e) {
	
		throw new ForbiddenException("Nffg not valid: " + e.getMessage());
	} catch (ServiceException | NullPointerException e) {
		throw new InternalServerErrorException("Unable to load the nffg: " + e.getMessage());
	}
		return object.createNffgService(nffg.getValue());
	}
	
	
	
	@Path("{name}")
	@GET
	@ApiOperation(value = "Retrieve all stored nffgs", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<NffgType> getNffg(@PathParam("name") String name) {
		
		System.out.println("[Service] GET  Nffg "+name);
		
		NffgType nffg = new NffgType();
		nffg = service.getMapNffg().get(name);
		
		if(nffg == null)
			throw new NotFoundException("Nffg not found");
		
		return object.createNffg(nffg);
	}
	
	

	@Path("{name}")
	@POST
	@ApiOperation(value = "load an Nffg", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_XML})
	public JAXBElement<NffgType> loadSingleNffg(@PathParam("name") String name,JAXBElement<NffgType> nffg) throws ServiceException {
		
		System.out.println("[Service] POST a single Nffg "+name);

		NffgType n = nffg.getValue();
	
			synchronized(service.getMapNffg())
			{
				try {
					service.loadSingleNffg(n);
				} catch (NffgVerifierException e) {
					
					throw new ForbiddenException("Nffg not valid: " + e.getMessage());
				} catch (ServiceException | NullPointerException e) {
					throw new InternalServerErrorException("Unable to load the nffg: " + e.getMessage());
				}
			}
	
		return object.createNffg(n);
	}
	
}
