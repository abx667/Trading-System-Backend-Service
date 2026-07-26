package com.cognizant.traderservice.controller;
 

import com.cognizant.traderservice.dto.TraderRequestDTO;
import com.cognizant.traderservice.dto.TraderResponseDTO;
import com.cognizant.traderservice.service.TraderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
 
import java.time.LocalDateTime;
 
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@WebMvcTest(TraderController.class)
@AutoConfigureMockMvc(addFilters = false)
class TraderControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private TraderService traderService;
 
    @Autowired
    private ObjectMapper objectMapper;
 
    @Test
    @WithMockUser(authorities = "SCOPE_admin")
    void shouldCreateTrader() throws Exception {
 
        TraderRequestDTO request = TraderRequestDTO.builder()
                .tradingCompanyId(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .phone("9999999999")
                .build();
 
        TraderResponseDTO response = TraderResponseDTO.builder()
                .id(1L)
                .tradingCompanyId(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@test.com")
                .phone("9999999999")
                .createdAt(LocalDateTime.now())
                .build();
 
        Mockito.when(traderService.saveTrader(any())).thenReturn(response);
 
        mockMvc.perform(post("/api/traders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }
}
 