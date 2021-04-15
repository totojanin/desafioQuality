package com.desafioQuality.desafioQuality;

import com.desafioQuality.desafioQuality.controllers.GeneralController;
import com.desafioQuality.desafioQuality.dtos.GeneralDTO;
import com.desafioQuality.desafioQuality.services.GeneralService;
import com.desafioQuality.desafioQuality.services.GeneralTestUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GeneralController.class)
public class GeneralControllerTest {
    @MockBean
    private GeneralService generalService;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getAllProducts() throws Exception {
        List<GeneralDTO> articulos = GeneralTestUtils.getArticulos();

        when(generalService.findArticuloByFilters()).thenReturn(articulos);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/articles"))
                .andExpect(status().isOk())
                .andReturn();

        List<GeneralDTO> responseArticles =
                objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<GeneralDTO>>() {
                });

        Assertions.assertEquals(articulos, responseArticles);
    }
}
