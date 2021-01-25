package com.mota.samuel.desafiotransacoes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mota.samuel.desafiotransacoes.controller.TransacaoController;
import com.mota.samuel.desafiotransacoes.model.Transacao;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransacaoService {

    private static Logger logger = LoggerFactory.getLogger(TransacaoController.class);

    private List<Transacao> transacaos;


    public void createTransacaoList() {
        if(transacaos == null) {
            transacaos = new ArrayList<>();
        }
    }


    public boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }



    private BigDecimal parseValor(JSONObject transacao) {
        return new BigDecimal((String) transacao.get("valor"));
    }


    private LocalDateTime parseDataHora(JSONObject transacao) {
        var startDate = (String) transacao.get("dataHora");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return ZonedDateTime.parse(startDate, formatter.withZone(ZoneId.of("UTC"))).toLocalDateTime();
    }


    public boolean isDataHoraFuture(Transacao transacao) {


        if (transacao.getDataHora().isBefore(LocalDateTime.now())) {
            return false;
        }

        return transacao.getDataHora().isAfter(LocalDateTime.now());
    }


    private void setTransacaoValues(JSONObject jsonTransacao, Transacao transacao) {

        transacao.setValor(jsonTransacao.get("valor") != null ? parseValor(jsonTransacao) : transacao.getValor());
        transacao.setDataHora(jsonTransacao.get("dataHora") != null ? parseDataHora(jsonTransacao) : transacao.getDataHora());
    }


    public Transacao create(JSONObject jsonTransacao) {

        Transacao transacao = new Transacao();
        setTransacaoValues(jsonTransacao,transacao);

        return transacao;
    }


    public void add(Transacao transacao) {
        createTransacaoList();
        transacaos.add(transacao);
    }

    public void delete() {
        if (transacaos.isEmpty()){
            logger.info("Não existe transações a serem excluidas");

        }else {

            transacaos.clear();
            logger.info("Transações Excluidas!");

        }

    }

    public List<Transacao> find() {
        createTransacaoList();
        return transacaos;
    }






}
