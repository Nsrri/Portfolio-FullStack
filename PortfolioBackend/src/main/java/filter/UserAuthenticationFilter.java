package filter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import jakarta.annotation.Priority;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;


/*
 * This class checks if the user has the required permission to access the service and it manage different access for different users
 * and for now we are using the basic authentication but there are more to check: API KEY...
 * Part of JAX-RS 2.0
 */
  
public class UserAuthenticationFilter implements ContainerRequestFilter  {
	
	
	
/*
 * You can use the @Context annotation to inject context objects such as HttpHeaders, UriInfo, HttpServletRequest 
 * into class field or method parameter in application subclasses, root resource classes, and providers.
 */
	@Context
	private ResourceInfo resourceInfo; //An injectable class to access the resource class and resource method matched by the current request
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";

	@Override
	public void filter(ContainerRequestContext requestContext) {
		Method method = resourceInfo.getResourceMethod();
		// check if the access level is not all if it is there is no need to filter
		if (!method.isAnnotationPresent(PermitAll.class)) {
			// check if the access level is denied for all the services then the request should be aborted
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users.").build());
				return;
			}
			// this get the authorization field and its type request header
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
			
			// check if the authorization  field is empty then the request would be aborted 
			if (authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
				return;
			}
			
			// Get the username and password from the header and delete the "Basic" part from it
			// Will we get it as 
			final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
			
			// This part will decode the credentials via base64
			String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword.getBytes()));
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String username = tokenizer.nextToken();
			final String password = tokenizer.nextToken();
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
				if (!isUserAllowed(username, password, rolesSet)) {
					requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
					return;
				}
			}
		}
	}

	private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
		boolean isAllowed = false;
		if (username.equals("admin") && password.equals("Jafari9090!")) {
			String userRole = "ADMIN";
			if (rolesSet.contains(userRole)) {
				isAllowed = true;
			}
		}
		return isAllowed;
	}

}
