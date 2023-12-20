package exceptionManager;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ExceptionMapperManager implements ExceptionMapper<InvalidCredentialException> {

	@Override
	public Response toResponse(InvalidCredentialException exception) {
		return Response.status(Response.Status.BAD_REQUEST).entity(prepareMessage(exception)).type("text/plain")
				.build();
	}

	private String prepareMessage(InvalidCredentialException exception) {
		StringBuilder message = new StringBuilder();
		
			message.append(exception.getMessage() + "\n");
		return message.toString();
	}
}
