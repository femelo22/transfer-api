package com.br.lfmelo.entities;

import com.br.lfmelo.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "TBL_USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 200)
    private String nome;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "senha", length = 50)
    private String senha;

    @Column(name = "cpf_cnpj", length = 14)
    private String cpfCnpj;

    @Enumerated(EnumType.ORDINAL)
    private TipoUsuario tipoUsuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carteira", referencedColumnName = "id", unique = true, nullable = false)
    private Carteira carteira;

    @OneToMany(mappedBy = "usuario")
    private List<Notificacao> notificacaos;
}
