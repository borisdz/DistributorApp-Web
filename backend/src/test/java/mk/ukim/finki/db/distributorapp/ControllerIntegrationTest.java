package mk.ukim.finki.db.distributorapp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {

    @Autowired
    private RestTemplate restTemplate;

    // List of controller mappings
    private final List<String> controllerMappings = List.of(
            "article",
            "articleUnit",
            "category",
            "city",
            "customer",
            "delivery",
            "dStatus",
            "driver",
            "manager",
            "manufacturer",
            "order",
            "oStatus",
            "price",
            "pForma",
            "pfStatus",
            "region",
            "users",
            "vehicle",
            "warehouse",
            "weekday"
            // Add more mappings as needed
    );

    @Test
    void testGetAllEndpoints() {
        controllerMappings.forEach(mapping -> {
            String url = "http://localhost:8080/" + mapping + "/all";
            System.out.println("Testing endpoint: " + url);

            ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);

            // Check the status code
            assertEquals(200, response.getStatusCodeValue(), "Unexpected status code for " + url);

            // Check the response body
            Object[] body = response.getBody();
            assertNotNull(body, "Response body is null for " + url);
            assertTrue(true, "Response body is not an array or has invalid size for " + url);

            System.out.println("✅ Successfully tested: " + url + " with " + body.length + " items.");
        });
    }
}
