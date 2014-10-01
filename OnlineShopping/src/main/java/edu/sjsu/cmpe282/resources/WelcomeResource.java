package edu.sjsu.cmpe282.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

import edu.sjsu.cmpe282.dto.Catalog;
import edu.sjsu.cmpe282.dto.User;

@Path("/")
public class WelcomeResource {
	@GET
	@Produces("text/html")
	public Response index() {
		return Response.ok(new Viewable("/index")).build();
	}

	@GET
	@Path("signin")
	@Produces("text/html")
	public Response signInPage() {
		return Response.ok(new Viewable("/signin")).build();
	}

	@POST
	@Path("signin")
	@Produces("text/html")
	public Response signIn(@FormParam("userid") String userid,
			@FormParam("password") String password) {
		UserResource userResource = new UserResource();
		String[] resource = userResource.signIn(userid, password);
		return Response.ok(new Viewable(resource[0], resource[1])).build();
	}

	@GET
	@Path("signup")
	@Produces("text/html")
	public Response signUpPage() {
		return Response.ok(new Viewable("/signup")).build();
	}

	@POST
	@Path("signup")
	@Produces("text/html")
	public Response signUp(@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("email") String userid,
			@FormParam("password") String password) {
		User user = new User(firstName, lastName, userid, password);
		UserResource userResource = new UserResource();
		String[] resource = userResource.signUp(user);
		return Response.ok(new Viewable(resource[0], resource[1])).build();
	}

	@GET
	@Path("addProduct")
	@Produces("text/html")
	public Response addProductPage() {
		return Response.ok(new Viewable("/addProduct")).build();
	}

	@POST
	@Path("addProduct")
	@Produces("text/html")
	public Response addProduct(@FormParam("catalogName") String catalogName,
			@FormParam("prodName") String prodName,
			@FormParam("desc") String desc, @FormParam("price") double price,
			@FormParam("quantity") int quantity) {
		ProductResource prodResource = new ProductResource();
		String[] resource = prodResource.addProduct(catalogName, prodName,
				desc, price, quantity);
		return Response.ok(new Viewable(resource[0], resource[1])).build();
	}

	@GET
	@Path("addCatalog")
	@Produces("text/html")
	public Response addCatalogPage() {
		return Response.ok(new Viewable("/addCatalog")).build();
	}

	@POST
	@Path("addCatalog")
	@Produces("text/html")
	public Response addCatalog(@FormParam("catalogName") String catalogName,
			@FormParam("desc") String desc) {
		CatalogResource catalogResource = new CatalogResource();
		String[] resource = catalogResource.addCatalog(catalogName, desc);
		return Response.ok(new Viewable(resource[0], resource[1])).build();
	}

	@GET
	@Path("store")
	@Produces("text/html")
	public Response viewStorePage() {
		CatalogResource catalogResource = new CatalogResource();
		List<Catalog> resource = catalogResource.viewStore();
		return Response.ok(new Viewable("/store",resource)).build();
	}
}
