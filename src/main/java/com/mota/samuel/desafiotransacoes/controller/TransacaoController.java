package com.mota.samuel.desafiotransacoes.controller;

import com.mota.samuel.desafiotransacoes.model.Transacao;
import com.mota.samuel.desafiotransacoes.service.TransacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/transacao")
@Api("Transação API")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    @PostMapping(produces="application/json")
    @ResponseBody
    @ApiOperation("Cria uma transação")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 422, message = "Unprocessable Entity!"),
    })


    public ResponseEntity<Transacao> create(@RequestBody JSONObject transacao) {

        System.out.println(LocalDateTime.now());


        try {
            if (transacaoService.isJSONValid(transacao.toString()) ) {

                Transacao transacaoCreated = transacaoService.create(transacao);
                var uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();




                if (transacaoService.isDataHoraFuture(transacaoCreated) || Integer.parseInt(transacao.get("valor").toString())<0) {



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
    @ApiOperation("Deleta todas transações")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK!"),
    })
    public ResponseEntity<?> delete() {
        try {
            transacaoService.delete();
            return ResponseEntity.ok("");
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

