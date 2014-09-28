package edu.sjsu.cmpe282.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

@Path("/catalogue")
public class ProductResource {

	@GET
	@Produces("text/html")
	public Response signInPage() {
		 return Response.ok(new Viewable("/catalogue")).build();
	}
	
}
