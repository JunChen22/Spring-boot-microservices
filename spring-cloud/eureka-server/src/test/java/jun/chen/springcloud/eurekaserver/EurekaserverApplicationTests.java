package jun.chen.springcloud.eurekaserver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class EurekaserverApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private TestRestTemplate testTemplate;

	@Test
	void catalogLoad() {
		String expectedResponseBody = "{\"applications\":{\"versions__delta\":\"1\",\"apps__hashcode\":\"\",\"application\":[]}}";
		ResponseEntity entity = testTemplate.getForEntity("/eureka/apps", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals(expectedResponseBody, entity.getBody());
	}

	@Test
	void healthy() {
		String expectedResponseBody = "{\"status\":\"UP\"}";
		ResponseEntity<String> entity = testTemplate.getForEntity("/actuator/health", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals(expectedResponseBody, entity.getBody());
	}

}
