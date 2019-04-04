package com.loopa.api.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loopa.api.model.Atendimento;

@Repository
public interface IAtendimentoRepository extends JpaRepository<Atendimento, Long>{

}
