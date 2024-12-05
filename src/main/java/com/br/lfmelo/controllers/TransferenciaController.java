package com.br.lfmelo.controllers;

import com.br.lfmelo.entities.dtos.TranferenciaDTO;
import com.br.lfmelo.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransferenciaController {
    @Autowired
    private TransferenciaService service;

    @PostMapping
    public ResponseEntity<?> realizarTransferencia(@RequestBody TranferenciaDTO dto) {
        service.validarDadosEnviarMensagem(dto);
        return ResponseEntity.ok().body("Transfer processing started");
    }
}
