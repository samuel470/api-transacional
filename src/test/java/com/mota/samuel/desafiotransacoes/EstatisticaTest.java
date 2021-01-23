package com.mota.samuel.desafiotransacoes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc

public class EstatisticaTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Ao verificar requisição das estatisticas, deve retornar status 200")
    public void verificaRequisiçaõ() throws Exception {

        MvcResult mvcResult = mockMvc
                                    .perform(
                                            MockMvcRequestBuilders.get("/estatistica")
                                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
