package com.cognizant.assetservice.controller;
 
import com.cognizant.assetservice.dto.AssetServiceDTO;
import com.cognizant.assetservice.service.AssetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
 
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
 
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@WebMvcTest(controllers = AssetServiceController.class)
@AutoConfigureMockMvc(addFilters = false) // disables security (important)
class AssetControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private AssetService service;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    // CREATE TEST
    @Test
    void shouldCreateAsset() throws Exception {
 
        AssetServiceDTO request = AssetServiceDTO.builder()
                .name("Gold")
                .symbol("GLD")
                .type("Commodity")
                .currentPrice(new BigDecimal("50000"))
                .listedSince(LocalDate.now())
                .build();
 
        AssetServiceDTO response = AssetServiceDTO.builder()
                .name("Gold")
                .symbol("GLD")
                .type("Commodity")
                .currentPrice(new BigDecimal("50000"))
                .listedSince(LocalDate.now())
                .build();
 
        when(service.saveAsset(any())).thenReturn(response);
 
        mockMvc.perform(post("/api/assets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gold"))
                .andExpect(jsonPath("$.symbol").value("GLD"));
    }
 
   
    // GET BY ID TEST
  
    @Test
    void shouldGetAssetById() throws Exception {
 
        AssetServiceDTO dto = AssetServiceDTO.builder()
                .name("Silver")
                .symbol("SLV")
                .type("Commodity")
                .currentPrice(new BigDecimal("30000"))
                .listedSince(LocalDate.now())
                .build();
 
        when(service.getAssetById(1L)).thenReturn(dto);
 
        mockMvc.perform(get("/api/assets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Silver"));
    }
 
    
    // GET ALL TEST
    
    @Test
    void shouldGetAllAssets() throws Exception {
 
        List<AssetServiceDTO> list = List.of(
                AssetServiceDTO.builder()
                        .name("Gold")
                        .symbol("GLD")
                        .type("Commodity")
                        .currentPrice(new BigDecimal("50000"))
                        .listedSince(LocalDate.now())
                        .build()
        );
 
        when(service.getAllAssets()).thenReturn(list);
 
        mockMvc.perform(get("/api/assets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }
 
   
    // UPDATE TEST
   
    @Test
    void shouldUpdateAsset() throws Exception {
 
        AssetServiceDTO updated = AssetServiceDTO.builder()
                .name("Oil")
                .symbol("OIL")
                .type("Energy")
                .currentPrice(new BigDecimal("70000"))
                .listedSince(LocalDate.now())
                .build();
 
        when(service.updateAsset(any(), any())).thenReturn(updated);
 
        mockMvc.perform(put("/api/assets/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Oil"));
    }
 
    
    // DELETE TEST
    
    @Test
    void shouldDeleteAsset() throws Exception {
 
        mockMvc.perform(delete("/api/assets/1"))
                .andExpect(status().isNoContent());
    }
}
 