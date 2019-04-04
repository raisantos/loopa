package com.loopa.api.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loopa.api.model.Servico;


@Repository
public interface IServicoRepository extends JpaRepository<Servico, Long>{

}
