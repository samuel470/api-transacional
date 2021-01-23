package com.mota.samuel.desafiotransacoes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    private BigDecimal valor;
    private LocalDateTime dataHora;


}