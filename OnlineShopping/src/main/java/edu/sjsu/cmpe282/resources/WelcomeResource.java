package edu.sjsu.cmpe282.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.view.Viewable;

import edu.sjsu.cmpe282.dto.Cart;
import edu.sjsu.cmpe282.dto.Catalog;
import edu.sjsu.cmpe282.dto.Product;
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

	/*@POST
	@Path("signin")
	@Produces("text/html")
	public Response signIn(@FormParam("userid") String userid,
			@FormParam("password") String password) {
		UserResource userResource = new UserResource();
		User user = userResource.signIn(userid, password);
		if(user!= null) {
			return Response.ok(new Viewable("/index", user)).build();
		}
		return Response.ok(new Viewable("/signin",  "Incorrect username/password")).build();
	}
*/
	
	@POST
	@Path("signin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signIn(@FormParam("userid") String userid,
			@FormParam("password") String password) {
		UserResource userResource = new UserResource();
		User user = userResource.signIn(userid, password);
		
		
		
		if(user!= null) {
			return Response.ok(new Viewable("/index", user)).build();
		}
		return Response.ok(new Viewable("/signin",  "Incorrect username/password")).build();
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
		User user = new User(firstName, lastName, userid, password,(short)0);
		UserResource userResource = new UserResource();
		User user1 = userResource.signUp(user);
		if(user1!= null) {
			return Response.ok(new Viewable("/index", user1)).build();
		}
		return Response.ok(new Viewable("/signup",  "Incorrect information. Please try again.")).build();
	}
	
	@GET
	@Path("signout")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject signOut() {
		String json = "{'redirect':'./'}";
		JSONObject obj=null;
		    try {
				 obj = new JSONObject(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return obj;
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
	@Path("allCatalogs")
	@Produces("application/json")
	public List<Catalog> getAllCatalogs() {
		CatalogResource catalogResource = new CatalogResource();
		List<Catalog> resource = catalogResource.getAllCatalogs();
		return resource;
	}

	@GET
	@Path("store")
	@Produces("text/html")
	public Response viewStorePage() {
		CatalogResource catalogResource = new CatalogResource();
		List<Catalog> resource = catalogResource.getAllCatalogs();
		return Response.ok(new Viewable("/store", resource)).build();
	}

	@GET
	@Path("viewProducts")
	@Produces("text/html")
	public Response viewProductsPage(
			@QueryParam("catalogName") String catalogName) {
		ProductResource productResource = new ProductResource();
		List<Product> resource = productResource.viewProducts(catalogName);
		return Response.ok(new Viewable("/viewProducts", resource)).build();
	}

	@POST
	@Path("addToCart/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addToCart(@PathParam("user_id") String userId,
			@QueryParam("cata") String catalogName,
			@QueryParam("product_id") int productId,
			@QueryParam("quan") int quantity) {
		ShoppingCartResource shoppingCart = new ShoppingCartResource();
		shoppingCart.addProductToCart(userId, catalogName, productId, quantity);
		return true;

	}
	
	@POST
	@Path("removeFromCart/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean removeFromCart(@PathParam("user_id") String userId,
			@QueryParam("cata") String catalogName,
			@QueryParam("product_id") int productId,
			@QueryParam("quan") int quantity) {
		ShoppingCartResource shoppingCart = new ShoppingCartResource();
		shoppingCart.removeProductFromCart(userId, catalogName, productId, quantity);
		return true;

	}

	@GET
	@Path("viewCart")
	@Produces("text/html")
	public Response viewShoppingCart(@QueryParam("user_id") String userId) {
		ShoppingCartResource shoppingCartResource = new ShoppingCartResource();
		Cart shoppingCart = shoppingCartResource.getShoppingCart(userId);
		return Response.ok(new Viewable("/viewCart", shoppingCart)).build();
	}

	@POST
	@Path("placeOrder")
	@Produces("text/html")
	public Response placeOrder(@QueryParam("user_id") String userId) {
		ShoppingCartResource shoppingCart = new ShoppingCartResource();
		String[] resource = shoppingCart.placeOrder(userId);
		return Response.ok(new Viewable(resource[0], resource[1])).build();
	}

	@GET
	@Path("viewCartHistory")
	@Produces("text/html")
	public Response viewCartHistory(@QueryParam("user_id") String userId) {
		ShoppingCartResource shoppingCartResource = new ShoppingCartResource();
		List<Cart> shoppingCarts = shoppingCartResource.getCartHistory(userId);
		return Response.ok(new Viewable("/viewHistory", shoppingCarts)).build();
	}

}
