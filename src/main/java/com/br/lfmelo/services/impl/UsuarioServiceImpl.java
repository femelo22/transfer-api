package com.br.lfmelo.services.impl;

import com.br.lfmelo.entities.Usuario;
import com.br.lfmelo.repositories.UsuarioRepository;
import com.br.lfmelo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID: " + id + " not found"));
    }
}
