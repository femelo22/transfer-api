package com.br.lfmelo.repositories;

import com.br.lfmelo.entities.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
