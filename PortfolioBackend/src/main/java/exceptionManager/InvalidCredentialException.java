package exceptionManager;

import jakarta.ws.rs.core.Response;

public class InvalidCredentialException extends Exception {
	 public Response toResponse(InvalidCredentialException ex) {
	        return Response.status(Response.Status.BAD_REQUEST).entity(prepareMessage(ex)).type("text/plain").build();
	    } 
	 
	 private String prepareMessage(InvalidCredentialException exception) {
			
			return "The email or password is not valid: " + exception;
		}
  
}
