package jaxrs;

import java.awt.print.Book;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

/**
 * Client.java
 *
 * @author Ethan Wong
 * @time 2015年11月14日下午1:26:36
 */
public class ClientTest {
	
	@Test
	public void test(){
		Client client = ClientBuilder.newBuilder().newClient();
		WebTarget target = client.target("http://localhost:8080/jaxrs/webservice/rs");
		target = target.path("/customer/testOne.json").queryParam("id", "123456").property("username", "imthan");
		 
		Invocation.Builder builder = target.request();
		Response response = builder.get();
		Book book = builder.get(Book.class);
		System.out.println("response:"+response);
		System.out.println("book:"+book);
	}
	


}


