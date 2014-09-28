package edu.sjsu.cmpe282.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

import edu.sjsu.cmpe282.dao.UserDao;
import edu.sjsu.cmpe282.dto.User;

@Path("/user")
public class UserResource {
	
	private UserDao userDao; 
	
	public UserResource() {
		userDao = new UserDao();
	}
	
	@GET
	@Path("/signin")
	@Produces("text/html")
	public Response signInPage() {
		 return Response.ok(new Viewable("/signin")).build();
	}
	
	@POST
	@Path("/signin")
	@Produces("text/html")
	public Response signIn(@FormParam("userid") String userid,
			@FormParam("password") String password) {
		if(userDao.login(userid, password)) {
			String firstName = userDao.getFirstName(userid);
			return Response.ok(new Viewable("/catalogue")).build();
		}
		return Response.ok(new Viewable("/signin")).build();
	}
	
	@POST
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signUp(User user) {
		userDao.addUser(user);
		String result = "User saved : " + user.getFirstName();
		return Response.status(201).entity(result).build();
	}

	
	
}
