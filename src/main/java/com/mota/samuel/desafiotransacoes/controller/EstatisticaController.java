package com.mota.samuel.desafiotransacoes.controller;

import com.mota.samuel.desafiotransacoes.model.Estatistica;
import com.mota.samuel.desafiotransacoes.model.Transacao;
import com.mota.samuel.desafiotransacoes.service.EstatisticaService;
import com.mota.samuel.desafiotransacoes.service.TransacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    LocalDateTime hNow = LocalDateTime.now();

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private EstatisticaService estatisticaService;

    @GetMapping(produces = { "application/json" })
    @ApiOperation("Retorna estatísticas  das transações que aconteceram nos últimos 60 segundos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK!"),
    })
    public ResponseEntity<Estatistica> getStatistics() throws Exception {

        List<Transacao> transacoes = transacaoService.find();
        Estatistica estatistica = estatisticaService.create(transacoes);




        return ResponseEntity.ok(estatistica);
    }
}