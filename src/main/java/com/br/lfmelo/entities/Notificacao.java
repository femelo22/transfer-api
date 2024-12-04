package com.br.lfmelo.entities;

import com.br.lfmelo.enums.StatusNotificacao;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity(name = "TBL_NOTIFICACOES")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mensagem")
    private String mensagem;

    @Enumerated(EnumType.ORDINAL)
    private StatusNotificacao status;

    @CreationTimestamp
    private LocalDateTime dataEnvio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
}
