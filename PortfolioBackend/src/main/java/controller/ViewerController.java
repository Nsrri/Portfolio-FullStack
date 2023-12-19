package controller;

import java.util.List;
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
import model.Viewer;
import service.ViewerService;
import validator.InputValidator;


@Path("/viewer")
public class ViewerController {
	
	ViewerService viewService  = new ViewerService();
	InputValidator validator = new InputValidator();
	Logger logger = LogManager.getLogger(ViewerController.class);
	
	@RolesAllowed("ADMIN")
	@Path("")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllViewer() {
		List<Viewer> viewer =  this.viewService.getAllRecords();
		
		 logger.info("Viewer list size: " + viewer.size());
		 logger.info(viewer);
		 // If the viewer object is not null it will give a list of all existing records in viewer
		    return viewer.isEmpty()
		            ? Response.status(Response.Status.NOT_FOUND).build()
		            : Response.status(Response.Status.OK).entity(viewer).build();
	}
	
	
	
	@RolesAllowed("ADMIN")
	@Path("validateuser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateRegisteredUser(@QueryParam ("id") int id) {
		Viewer viewer =  this.viewService.getById(id);
		logger.info(viewer);
		 // Check if user exists
		return  viewer == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity("You already have an account").build();
 
	}
	@RolesAllowed("ADMIN")
	@Path("createnewaccount")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewAccount(Viewer viewer) throws InvalidCredentialException {
		 int status = 0;
		
		 // Check if the user Credentials are correct then insert new record
		if(!validator.EmailValidator(viewer.getEmail()) && !validator.passwordValidator(viewer.getPassword())) {
			throw new InvalidCredentialException();
		}
			status = viewService.addNewRecord(viewer);
		  return status == 0 ?  Response.status(Response.Status.BAD_REQUEST).build() : Response.status(Response.Status.CREATED).entity(viewer).build();
 
	}
	
	@Path("/updateexistingviewer")
	@PUT
	public Response updateEmail(@QueryParam("email") String email, @QueryParam("id") int id) {
		int result = 0;
		if(validator.EmailValidator(email)) {
			result = viewService.updateRecordSpecific(email, id);
		}
		//Update the email address of an existing user
		  return result == 0 ?  Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.ACCEPTED).build();
 
	}
//	
	@Path("/deleteviewer")
	@DELETE
	public Response deleteViewer(@QueryParam("id") int id) {
		  int result = viewService.deleteRecord(id);
		  return result == 0 ?  Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).build();
	
	}
	

}



