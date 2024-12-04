package com.br.lfmelo.enums;
import lombok.Getter;
@Getter
public enum TipoUsuario {

    USUARIO(0, "Usuario"),
    LOJISTA(1, "Lojista");

    private Integer codigo;
    private String label;

    TipoUsuario(int codigo, String label) {
        this.codigo = codigo;
        this.label = label;
    }
}
