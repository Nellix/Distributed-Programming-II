package it.polito.dp2.NFFG.sol3.service.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
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
import it.polito.dp2.NFFG.lab3.UnknownNameException;
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
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<NffgServiceType> getALLNffgs() {
	
		Nffgs all = new Nffgs();
				all= service.getALLNffg();
				NffgServiceType out = new NffgServiceType();
				out.setNffgs(all);
				
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
	public JAXBElement<NffgServiceType> loadNffgService(JAXBElement<NffgServiceType> nffg) throws it.polito.dp2.NFFG.sol3.service.ServiceException {
		
		NffgServiceType nffgservice = nffg.getValue();
	
		service.loadNffgService(nffgservice);
		
		return object.createNffgService(nffg.getValue());
	}
	
	
	
	@Path("{name}")
	@GET
	@ApiOperation(value = "Retrieve all stored nffgs", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<NffgType> getNffg(@PathParam("name") String name) {
		
		NffgType nffg = new NffgType();
		
			nffg = service.getMapNffg().get(name);
		
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
	public JAXBElement<NffgType> loadSingleNffg(@PathParam("name") String name,JAXBElement<NffgType> nffg) {
		
		NffgType n = nffg.getValue();
	
			synchronized(service.getMapNffg())
			{
				try {
					service.loadSingleNffg(n);
				} catch (it.polito.dp2.NFFG.sol3.service.ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
	
		
		return object.createNffg(n);
	}
	
}
