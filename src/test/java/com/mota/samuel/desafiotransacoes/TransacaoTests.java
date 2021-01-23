package com.mota.samuel.desafiotransacoes;

import com.mota.samuel.desafiotransacoes.model.Transacao;
import com.mota.samuel.desafiotransacoes.service.TransacaoService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransacaoTests {

    @Autowired
    private TransacaoService transacoesService;

//    @BeforeAll
//    public void setUp() {
//
//        List<Transacao> transacaos = new ArrayList<>();
//
//    }


    @Test
    @DisplayName("Deve Criar uma transacao com sucesso!")

    public void criatransacaoTest() throws Exception {

        JSONObject jsonTransacao = new JSONObject();

        jsonTransacao.put("valor", "22.88");
        jsonTransacao.put("dataHora", "2019-07-15T03:36:53.232Z");


        Transacao transacao = transacoesService.create(jsonTransacao);

        // Validação

        assertNotNull(transacao);
        assertEquals(transacao.getValor().toString(), jsonTransacao.get("valor"));
        assertEquals(transacao.getDataHora(), ZonedDateTime.parse("2019-07-15T03:36:53.232Z").toLocalDateTime());

    }



    @Test
    @DisplayName("Deve criar somente transacoes realizadas no passado")
    public void verificaDataTest() throws Exception {

        JSONObject jsonTransacao = new JSONObject();

        jsonTransacao.put("valor", "45");
        jsonTransacao.put("dataHora", "2025-02-16T03:54:23.654Z");


        Transacao transacao = transacoesService.create(jsonTransacao);
        boolean TransacaoInFuture = transacoesService.isDataHoraFuture(transacao);

         assertTrue(TransacaoInFuture);

    }
}


