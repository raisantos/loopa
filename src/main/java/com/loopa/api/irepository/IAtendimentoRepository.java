package com.loopa.api.irepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loopa.api.model.Atendimento;
import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;

@Repository
public interface IAtendimentoRepository extends JpaRepository<Atendimento, Long>{

	List<Atendimento> findByCliente(Cliente cliente);
	Optional<Atendimento> findByProfissionalAndCodigo(Profissional profissional, String codigo);
}
