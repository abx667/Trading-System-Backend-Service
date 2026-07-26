package com.cognizant.assetservice.integration;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
 

 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
class AssetIntegrationTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    void fullFlowTest() throws Exception {
 
        String json = """
                {
                    "name":"Tesla",
                    "symbol":"TSLA",
                    "type":"STOCK",
                    "currentPrice":500,
                    "listedSince":"2020-01-01"
                }
                """;
 
        mockMvc.perform(post("/api/assets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}