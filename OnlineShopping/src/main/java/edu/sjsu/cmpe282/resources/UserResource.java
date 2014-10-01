package edu.sjsu.cmpe282.resources;


import edu.sjsu.cmpe282.dao.UserDao;
import edu.sjsu.cmpe282.dto.User;

public class UserResource {
	
	private UserDao userDao; 
	
	public UserResource() {
		userDao = new UserDao();
	}
	
/*	@GET
	@Path("/signin")
	@Produces("text/html")
	public Response signInPage() {
		 return Response.ok(new Viewable("/signin")).build();
	}*/
	
/*	@POST
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
	}*/

	
	public String[] signIn( String userid,
			String password) {
		String[] result= new String[2];
		if(userDao.login(userid, password)) {
			result[1] = "Hello, "+userDao.getFirstName(userid);
			result[0] = "/catalogue";
			return result;
		}
		result[0]="/signin";
		result[1] = "Incorrect username/password";
		return result;
	}
	
	public String[] signUp(User user) {
		String[] result = new String[2];
		if(userDao.addUser(user)) {
			result[1] = "Hello, "+user.getFirstName();
			result[0] = "/catalogue";
			return result;
		}
		result[0]="/signup";
		result[1] = "Invalid information ";
		return result;
	}
	
}
