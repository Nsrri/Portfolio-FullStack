package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptionManager.InvalidCredentialException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Occupation;
import service.OccupationService;



@Path("/occupation")
public class OccupationController {
	
	OccupationService occupationServ  = new OccupationService();
	Logger logger = LogManager.getLogger(ViewerController.class);
	
	@RolesAllowed("ADMIN")
	@Path("")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOccupationById(@QueryParam("id") int id) {
		System.out.print("Hello");
		Occupation occupation =  this.occupationServ.getById(id);
		return  occupation == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(occupation).build();
 
	}
	
	@RolesAllowed("ADMIN")
	@Path("createnewoccupation")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewOccupation(Occupation occupation) throws InvalidCredentialException {
		 int status = 0;

			status = occupationServ.addNewRecord(occupation);
		  return status == 0 ?  Response.status(Response.Status.BAD_REQUEST).build() : Response.status(Response.Status.CREATED).entity(occupation).build();
 
	}
	
	@Path("/updateexistingoccupation")
	@PUT
	public Response updateOccupation(@QueryParam("name") String name, @QueryParam("id") int id) {
		int result = 0;
			result = occupationServ.updateRecordSpecific(name, id);
		
		//Update the email address of an existing user
		  return result == 0 ?  Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.ACCEPTED).build();
 
	}
	
	@Path("/deleteoccupation")
	@DELETE
	public Response deleteOccupation(@QueryParam("id") int id) {
		  int result = occupationServ.deleteRecord(id);
		  return result == 0 ?  Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).build();
	
	}
	

}
