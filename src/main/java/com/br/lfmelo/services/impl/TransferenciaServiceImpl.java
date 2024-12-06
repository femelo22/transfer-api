package com.br.lfmelo.services.impl;

import com.br.lfmelo.entities.Usuario;
import com.br.lfmelo.entities.dtos.TranferenciaDTO;
import com.br.lfmelo.enums.TipoUsuario;
import com.br.lfmelo.services.TransferenciaService;
import com.br.lfmelo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SqsSender sqsSender;

    @Override
    public void validarDadosEnviarMensagem(TranferenciaDTO dto) {
        Usuario pagador = usuarioService.buscarPorId(dto.getPayer());
        Usuario beneficiario = usuarioService.buscarPorId(dto.getPayee());// Para validar se Payee existe

        validarSaldo(pagador.getCarteira().getSaldo(), dto.getValue());
        validarPermisaoTransferencia(pagador);
        validarPartesTransferencia(pagador, beneficiario);

        sqsSender.publicar(dto);
    }

    private void validarSaldo(BigDecimal saldoPagador, BigDecimal valorTransferencia) {
        if (saldoPagador.compareTo(valorTransferencia) < 0) {
            throw new RuntimeException("Insufficient Balance");
        }
    }

    private void validarPermisaoTransferencia(Usuario pagador) {
        if(pagador.getTipoUsuario().equals(TipoUsuario.LOJISTA)) {
            throw new RuntimeException("Shopkeepers cannot carry out transfers");
        }
    }

    private void validarPartesTransferencia(Usuario pagador, Usuario beneficiario) {
        if(pagador.getId().equals(beneficiario.getId())) {
            throw new RuntimeException("The payer cannot be the same as the beneficiary");
        }
    }

}
