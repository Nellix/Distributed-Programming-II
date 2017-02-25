package it.polito.dp2.NFFG.sol3.service.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
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
import it.polito.dp2.NFFG.lab2.NoGraphException;
import it.polito.dp2.NFFG.sol3.jaxb.ObjectFactory;
import it.polito.dp2.NFFG.sol3.jaxb.PoliciesType;
import it.polito.dp2.NFFG.sol3.jaxb.PolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.ReachabilityPolicyType;
import it.polito.dp2.NFFG.sol3.jaxb.VerificationResultType;
import it.polito.dp2.NFFG.sol3.service.NFFGSService;
import it.polito.dp2.NFFG.sol3.service.ServiceException;

@Path("/Policies")
@Api(value = "/Policies", description = "Manage the stored policies")
public class PoliciesResources {

	private NFFGSService service = NFFGSService.getService();
	private ObjectFactory object = new ObjectFactory();
	
	
	@GET
	@ApiOperation(value = "Retrieve all stored policies", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<PoliciesType> getALLPolicies() {
	
		System.out.println("[Service] GET ALL Policies");
		
		PoliciesType p = new PoliciesType();
		
		synchronized(service.getMapPolicy())
		{
			p = service.getALLPolicies();
		}
				
			return object.createPolicies(p);
	}
	
	
	@Path("{name}/verificationResult")
	@POST
	@ApiOperation(value = "Test Reachability policy", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
//	@Consumes({MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<VerificationResultType> testReachabilityPolicy(@PathParam("name") String name) throws NoGraphException, ServiceException {
	
		System.out.println("[Service] TEST "+name);
		
		VerificationResultType result = new VerificationResultType();
		
		synchronized(service.getMapPolicy())
		{
			if(service.getMapPolicy().containsKey(name))
				result = service.testReachabilityPolicy(name);
			else
				throw new NotFoundException("PolicyNotFound");
		}
				
		return object.createVerificationResult(result);
	}
	
	
	@Path("{name}")
	@POST
	@ApiOperation(value = "load a ReachabilityPolicy", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_XML})
	public JAXBElement<ReachabilityPolicyType> loadReachabilityPolicy(@PathParam("name") String name, JAXBElement<ReachabilityPolicyType> policy)
	{ 
		
		System.out.println("[Service] Post  Policy "+name);	
		
		if(service.getMapNffg().containsKey(policy.getValue().getNffg()))
		{	
				try{
						synchronized(service.getMapPolicy())
						{
							service.loadSinglePolicy(policy.getValue());
						}
	
					}catch (NffgVerifierException e) {
							throw new ForbiddenException("Policy not valid: " + e.getMessage());
					}
		
			return object.createReachabilityPolicy(policy.getValue());
		}
		else
			throw new NotFoundException("Nffg refers to policy not found");
	}
	
	
	@Path("{name}")
	@DELETE
	@ApiOperation(value = "Delete a policy", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<ReachabilityPolicyType> deletePolicy(@PathParam("name") String policyName) {
		
		System.out.println("[SERVICE] DELETE "+policyName);
		
		PolicyType pol;
		synchronized (service.getMapPolicy()) {
			pol = service.deletePolicy(policyName);
			
			if (pol == null)				
				throw new NotFoundException("Policy not found");

		}
		
		return  object.createReachabilityPolicy((ReachabilityPolicyType)pol);  //factory.createPolicy(((ReachabilityPolicy) pol).toXMLObject());
	}
	
	
	
	@Path("Nffgs/{name}")
	@GET
	@ApiOperation(value = "Get all policies of nffg", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<PoliciesType> getPoliciesByNffg(@PathParam("name") String name) throws NoGraphException {
	
		System.out.println("[Service] Get all policies of  "+name);
		PoliciesType set = new PoliciesType();
		
		if(service.getMapNffg().containsKey(name))
		{
		
		synchronized(service.getMapPolicy())
		{
			set = service.getPoliciesofNffg(name);
		}
				
		if(set==null)
			throw new NotFoundException("Policy Not Found");
	
		
		return object.createPolicies(set);
		}else
			throw new NotFoundException("Nffg Not Found");
	}



	
	@Path("verificationTime/{time}")
	@GET
	@ApiOperation(value = "Get all policies by verification time", notes = "xml format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Produces({MediaType.APPLICATION_XML})
	public JAXBElement<PoliciesType> getPoliciesByTime(@PathParam("time") Long time) throws NoGraphException {
	
		System.out.println("[Service] Get all policies with ver. time   "+time);
		PoliciesType set = new PoliciesType();
		
		
		synchronized(service.getMapPolicy())
		{
			set = service.getPoliciesByTime(time);
		}
				
		if(set==null)
			throw new NotFoundException("Policy Not Found");
	
		
		return object.createPolicies(set);
		
	}


}
