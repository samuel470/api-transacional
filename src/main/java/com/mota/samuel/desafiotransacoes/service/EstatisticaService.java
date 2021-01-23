package com.mota.samuel.desafiotransacoes.service;

import com.mota.samuel.desafiotransacoes.model.Estatistica;
import com.mota.samuel.desafiotransacoes.model.Transacao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstatisticaService {

    public Estatistica create(List<Transacao> transacoes) throws Exception {

        var estatistica = new Estatistica();
        LocalDateTime hNow = LocalDateTime.now();
        int tempo = 60;

        LocalDateTime hConv = hNow.minusSeconds(tempo);



        estatistica
                .setCount(
                        (int) transacoes
                        .stream()
                        .filter(date -> date.getDataHora().isAfter(hConv))
//                        .filter(date -> date.getDataHora().isBefore(hNow))
                        .count()
                );

        estatistica
                .setSum(
                        BigDecimal.valueOf(
                                transacoes
                                        .stream()
//                                        .filter(date -> date.getDataHora().isAfter(hConv))
//                                        .filter(date -> date.getDataHora().isBefore(hNow))
                                        .mapToDouble(t -> t.getValor().doubleValue())
                                        .sum())
                        .setScale(2, RoundingMode.HALF_UP));

        estatistica
                .setMin(
                        BigDecimal.valueOf(
                                transacoes
                                        .stream()
//                                        .filter(date -> date.getDataHora().isAfter(hConv))
//                                        .filter(date -> date.getDataHora().isBefore(hNow))
                                        .mapToDouble(t -> t.getValor().doubleValue())
                                        .min().orElse(0.0))
                        .setScale(2, RoundingMode.HALF_UP));

        estatistica
                .setAvg(
                        BigDecimal.valueOf(
                                transacoes
                                        .stream()
//                                        .filter(date -> date.getDataHora().isAfter(hConv))
//                                        .filter(date -> date.getDataHora().isBefore(hNow))
                                        .mapToDouble(t -> t.getValor().doubleValue())
                                        .average().orElse(0.0))
                        .setScale(2, RoundingMode.HALF_UP));


        estatistica
                .setMax(
                        BigDecimal.valueOf(
                                transacoes
                                        .stream()
//                                        .filter(date -> date.getDataHora().isAfter(hConv))
//                                        .filter(date -> date.getDataHora().isBefore(hNow))
                                        .mapToDouble(t -> t.getValor().doubleValue())
                                        .max().orElse(0.0))
                        .setScale(2, RoundingMode.HALF_UP));


        return estatistica;

    }
}