package com.br.lfmelo.services.impl;

import com.br.lfmelo.entities.Usuario;
import com.br.lfmelo.entities.dtos.TranferenciaDTO;
import com.br.lfmelo.services.TransferenciaService;
import com.br.lfmelo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SqsSender sqsSender;

    @Override
    public void validarDadosEnviarMensagem(TranferenciaDTO dto) {
        Usuario pagador = usuarioService.buscarPorId(dto.getPayer());

        usuarioService.buscarPorId(dto.getPayee()); // Para validar se Payee existe

        if (pagador.getCarteira().getSaldo().compareTo(dto.getValue()) > 0) {
            sqsSender.publicar(dto);
        } else {
            throw new RuntimeException("Insufficient Balance");
        }
    }
}
