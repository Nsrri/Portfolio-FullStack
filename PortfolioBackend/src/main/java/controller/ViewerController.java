package controller;

import java.time.LocalDate;
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
import model.Viewer;
import service.ViewerService;
import utils.Helper;
import validator.InputValidator;


@Path("/viewer")
public class ViewerController {
	
	ViewerService viewService  = new ViewerService();
	InputValidator validator = new InputValidator();
	Logger logger = LogManager.getLogger(ViewerController.class);
	Helper helper = new Helper();
	
	
	
	
	//Get viewer by id, the purpose of this service is to validate if user already exists and also validate if the client user is the valid user
	@RolesAllowed({"ADMIN", "VIEWER"})
	@Path("/validateuser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateRegisteredUser(@QueryParam ("id") int id, @QueryParam("email") String email) throws InvalidCredentialException {
		Viewer viewer = null;
		
		// Check if the email address is valid and if the email of retrieved user is the same as client email
		logger.info( viewer == null ? "The requested object is null" : "The id of retrieved object is: " + viewer.getViewerId());
//		if(!helper.userExists(email, id)) {
	      	viewer = viewService.getById(id);
//		}

		
		 // Check if user exists and email validation is done in the helper class
		return  viewer == null 
				? Response.status(Response.Status.NOT_FOUND).build()
				: Response.status(Response.Status.OK).entity("You already have an account -> " + viewer).build();
 
	}
	
	
	// Get a list of all viewer
	@RolesAllowed({"ADMIN"})
	@Path("/allviewer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllViewer() {
		
		List<Viewer> viewer =  this.viewService.getAllRecords();
		
		 logger.info("Viewer list size: " + viewer.size());
		 // If the viewer object is not null it will give a list of all existing records in viewer
		    return viewer.isEmpty()
		            ? Response.status(Response.Status.NOT_FOUND).build()
		            : Response.status(Response.Status.OK).entity(viewer).build();
	}
	
	

	@RolesAllowed({"ADMIN", "VIEWER"})
	@Path("/createnewaccount")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewAccount(Viewer viewer) throws InvalidCredentialException {
		int status = 0;
		 // Check if the user Credentials are correct then insert new record
		if(validator.emailValidator(viewer.getEmail()) 
				&& validator.passwordValidator(viewer.getPassword())
				&& validator.validRange(viewer.getBirthdate(), LocalDate.of(1200, 01, 01),LocalDate.now())) {
			status = viewService.addNewRecord(viewer);
			logger.info( viewer == null ? "The new Record wasn't added" : "The new Recoid is added " + viewer.getViewerId());
			
		} else {
			
			status = 0;
		}
		return status == 0 ?   Response.status(Response.Status.BAD_REQUEST).build() : Response.status(Response.Status.OK).entity(viewer).build();
			 
 
	}
	
	@RolesAllowed("VIEWER")
	@Path("/updateexistingviewer")
	@PUT
	public Response updateEmail(@QueryParam("email") String email, @QueryParam("id") int id) throws InvalidCredentialException {
		int result = 0;
		if(validator.emailValidator(email)) {
			result = viewService.updateRecordSpecific(email, id);
			
		}
		//Update the email address of an existing user
		  return result == 0 
				  ?  Response.status(Response.Status.NOT_FOUND).build() 
				  : Response.status(Response.Status.OK).build();
 
	}
	@RolesAllowed({"ADMIN", "VIEWER"})
	@Path("/deleteviewer")
	@DELETE
	public Response deleteViewer(@QueryParam("id") int id, @QueryParam("email") String email) throws InvalidCredentialException {
		int result = 0;
		
		// Need to be fixed but for now is all good
//        if(!helper.userExists(email, id)) {
//  		
//        }

        result = viewService.deleteRecord(id);
        
		  return result == 0 
				  ?  Response.status(Response.Status.NOT_FOUND).build() 
				  : Response.status(Response.Status.OK).build();
	
	}
	
	
	@RolesAllowed("ADMIN")
	@Path("/occupation")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByOccupation(@QueryParam ("id") int id) {
		List<Viewer> viewer =  this.viewService.extractViewerByOccupation(id);
		 logger.info("Viewer list size: " + viewer.size());
		logger.info(viewer);
		 // Check if user exists
		return  viewer == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(viewer).build();
 
	}
	

}



