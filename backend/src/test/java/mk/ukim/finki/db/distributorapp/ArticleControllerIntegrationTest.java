package mk.ukim.finki.db.distributorapp;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(ArticleControllerIntegrationTest.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void testGetAllArticles() {
//        logger.info();
//    }

    @Test
    public void testAddEditDeleteArticle() throws Exception {
        String newArticle = """
                {
                    "id": null,
                    "name": "Test Article",
                    "manufacturer": "Test Manufacturer",
                    "manufacturerId": 1,
                    "price": 99.99,
                    "category": "Test Category",
                    "categoryId": 1,
                    "weight": 10,
                    "image": "test_image.jpg"
                }
                """;

        String response = mockMvc.perform(put("/article/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newArticle))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long articleId = extractIdFromResponse(response);

        String updatedArticle = """
                {
                    "id": %d,
                    "name": "Updated Test Article",
                    "manufacturer": "Test Manufacturer",
                    "manufacturerId": 1,
                    "price": 199.99,
                    "category": "Test Category",
                    "categoryId": 1,
                    "weight": 15,
                    "image": "updated_image.jpg"
                }
                """.formatted(articleId);

        mockMvc.perform(put("/article/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedArticle))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Test Article"));

        mockMvc.perform(delete("/article/delete/"+articleId))
                .andExpect(status().isOk());

        mockMvc.perform(get("/article/"+articleId))
                .andExpect(status().isNotFound());
    }

    private Long extractIdFromResponse(String response) {
        return Long.valueOf(response.replaceAll("[^0-9]",""));
    }
}
