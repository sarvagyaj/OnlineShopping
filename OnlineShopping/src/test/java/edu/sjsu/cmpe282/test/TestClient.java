package edu.sjsu.cmpe282.test;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TestClient {
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		System.out.println(service.path("hello")
				.accept(MediaType.TEXT_PLAIN)
				.get(String.class));
		
		System.out.println(service.path("user/signin")
				.accept(MediaType.TEXT_HTML)
				.get(String.class));
		
		String input = "{\"firstName\":\"Metallica\",\"lastName\":\"Fade\",\"email\":\"456\",\"password\":\"123\"}";
		System.out.println(service.path("user/signup").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input).getEntity(String.class));
	//	System.out.println(service.path("rest").path("user/signin").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input).getEntity(String.class));
	
		
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/OnlineShopping")
				.build();
	}

}
