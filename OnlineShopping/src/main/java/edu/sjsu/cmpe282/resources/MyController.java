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

@Path("/try.html")
public class MyController {

	 @GET
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

}