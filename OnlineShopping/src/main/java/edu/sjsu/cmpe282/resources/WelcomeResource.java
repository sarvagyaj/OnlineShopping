package edu.sjsu.cmpe282.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;


@Path("/")
public class WelcomeResource {
	 @GET
	    @Produces("text/html")
	    public Response index() {
	        return Response.ok(new Viewable("/index")).build();
	    }
}
