package edu.sjsu.cmpe282.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

import edu.sjsu.cmpe282.dto.User;

@Path("/try")
public class MyController {

	@GET
	@Path("/one")
	@Produces("text/html")
	public Response index() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", "usul");
		List<String> l = new ArrayList<String>();
		l.add("light saber");
		l.add("fremen clothes");
		map.put("items", l);
		return Response.ok(new Viewable("/test", map)).build();
	}
	
	@GET
	@Path("/two")
	@Produces("text/html")
	public Response index1() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("user", "usul");
		map.put("items", "as");
		list.add(map);
		return Response.ok(new Viewable("/test1", list)).build();
	}
	
	@GET
	@Path("/three")
	@Produces("text/html")
	public Response index2() {
		
		User user1 = new User("a","a","b","c");
		User user2 = new User("b","a","b","c");
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		return Response.ok(new Viewable("/test1", users)).build();
	}

}