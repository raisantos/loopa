package com.loopa.api.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loopa.api.model.Avaliacao;

@Repository
public interface IAvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

}
