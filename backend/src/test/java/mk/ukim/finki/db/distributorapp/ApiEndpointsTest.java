package mk.ukim.finki.db.distributorapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiEndpointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAllEndpoints() throws Exception {
        List<String> urls = List.of(
                "/article/all",
                "/articleUnit/all",
                "/category/all",
                "/city/all",
                "/customer/all",
                "/delivery/all",
                "/dStatus/all",
                "/driver/all",
                "/manager/all",
                "/manufacturer/all",
                "/order/all",
                "/oStatus/all",
                "/price/all",
                "/pForma/all",
                "/pfStatus/all",
                "/region/all",
                "/users/all",
                "/vehicle/all",
                "/warehouse/all",
                "/weekday/all"
        );

        StringBuilder summary = new StringBuilder();
        boolean allTestsPassed = true;

        for (String url : urls) {
            try {
                mockMvc.perform(get(url))
                        .andExpect(status().isOk());
                summary.append("SUCCESS: ").append(url)
                        .append("\n");
            } catch (Exception e) {
                allTestsPassed = false;
                summary.append("FAILED: ").append(url)
                        .append(" - ").append(e.getMessage())
                        .append("\n");
            }
        }

        System.out.println("\nTest Summary:\n"+summary);
        assertTrue(allTestsPassed,"Some endpoints failed. Check the summary for details.");
    }
}
