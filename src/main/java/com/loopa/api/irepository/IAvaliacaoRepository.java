package com.loopa.api.irepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loopa.api.model.Avaliacao;
import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;

@Repository
public interface IAvaliacaoRepository extends JpaRepository<Avaliacao, Long>{

	List<Avaliacao> findByCliente(Cliente cliente);
	
	Avaliacao findByClienteAndProfissional(Cliente cliente, Profissional profissional);
}
