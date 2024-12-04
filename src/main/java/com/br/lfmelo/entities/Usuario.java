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

    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "senha", length = 50, nullable = false)
    private String senha;

    @Column(name = "cpf_cnpj", length = 14, unique = true)
    private String cpfCnpj;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Enumerated(EnumType.ORDINAL)
    private TipoUsuario tipoUsuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carteira", referencedColumnName = "id", unique = true, nullable = false)
    private Carteira carteira;

    @OneToMany(mappedBy = "usuario")
    private List<Notificacao> notificacaos;
}
