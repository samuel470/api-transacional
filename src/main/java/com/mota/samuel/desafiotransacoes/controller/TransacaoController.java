package com.mota.samuel.desafiotransacoes.controller;

import com.mota.samuel.desafiotransacoes.model.Transacao;
import com.mota.samuel.desafiotransacoes.service.TransacaoService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;




@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    @PostMapping
    @ResponseBody
    public ResponseEntity<Transacao> create(@RequestBody JSONObject transacao) {

        try {
            if (transacaoService.isJSONValid(transacao.toString())) {

                Transacao transacaoCreated = transacaoService.create(transacao);
                var uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

                if (transacaoService.isDataHoraFuture(transacaoCreated)) {

                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);

                } else {

                    transacaoService.add(transacaoCreated);
                    return ResponseEntity.created(uri).body(null);

                }
            } else {

                return ResponseEntity.badRequest().body(null);

            }
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);

        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        try {
            transacaoService.delete();
            return ResponseEntity.ok("Todas as informações foram apagadas com sucesso");
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

