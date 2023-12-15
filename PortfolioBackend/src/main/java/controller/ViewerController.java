package controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Viewer;
import service.ViewerService;

@Path("/v2")
public class ViewerController {
	// here validation should be based on the email address
	ViewerService viewService  = new ViewerService();
	Logger logger = LogManager.getLogger(ViewerController.class);
	
	
	@Path("/getallviewer")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllViewer() {
		List<Viewer> viewer =  this.viewService.getViewerAll();
		logger.info(viewer.size());
		return  viewer == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(viewer).build();
 
	}
	

	@Path("/validateuser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateRegisteredUser(@QueryParam ("id") int id) {
		Viewer viewer =  this.viewService.getViewerById(id);
		logger.info(viewer);
		return  viewer == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).entity(viewer).build();
 
	}
	
	@Path("/createnewaccount")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewAccount(Viewer viewer) {
		
		 int status = viewService.createNewViewerAccount(viewer);
		  return status == 0 ?  Response.status(Response.Status.BAD_REQUEST).build() : Response.status(Response.Status.CREATED).entity(viewer).build();
 
	}
	
	@Path("/updateexistingviewer")
	@PUT
	public Response updateExistingViewer(@QueryParam("email") String email, @QueryParam("id") int id) {
		  int result = viewService.updateViewer(email, id);
		  
		  return result == 0 ?  Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.ACCEPTED).build();
 
	}
	
	@Path("/deleteviewer")
	@DELETE
	public Response deleteViewer(@QueryParam("id") int id) {
		  int result = viewService.deleteViewer(id);
		  return result == 0 ?  Response.status(Response.Status.NOT_FOUND).build() : Response.status(Response.Status.OK).build();
	
	}
}
//    
//
//    public void readProperties() {
// 	   
// 	   try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties") ){
//
//        Properties prop = new Properties();
//
//        // load a properties file
//        prop.load(input);
//
//        // get the property value and print it out
//        dbUrl = prop.getProperty("DATA_BASE_URL");
//        clientUrl = prop.getProperty(" CLIENT_URL");
//
// 	   } catch (IOException ex) {
// 		   ex.printStackTrace();;
// 	   }
//    }


