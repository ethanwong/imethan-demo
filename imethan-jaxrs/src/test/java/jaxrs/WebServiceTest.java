package jaxrs;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 
 * TicketWebServiceTest.java
 * 
 * @author Ethan
 * @since JDK 1.7
 * @see
 */
public class WebServiceTest {

	private HttpClientUtils http = new HttpClientUtils();
	private String url = "http://localhost:8080/jaxrs/webservice/rs";

	@Test
	public void testOne() throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		map.put("id", "123");
		String results = http.doPost(url + "/customer/testOne.json", map);

		System.out.println(results);
	}

}
