package com.br.lfmelo.enums;

import lombok.Getter;
@Getter
public enum StatusNotificacao {

    PENDENTE(0, "Pendente"),
    ENVIADO(1, "Enviado"),
    CANCELADO(2, "Cancelado");

    private Integer codigo;
    private String label;

    StatusNotificacao(Integer codigo, String label) {
        this.codigo = codigo;
        this.label = label;
    }
}
