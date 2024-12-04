package com.br.lfmelo.services;

import com.br.lfmelo.entities.dtos.TranferenciaDTO;

public interface TransferenciaService {
    void validarDadosEnviarMensagem(TranferenciaDTO dto);
}
