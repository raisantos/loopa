package com.loopa.api.irepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loopa.api.model.Atendimento;
import com.loopa.api.model.Cliente;

@Repository
public interface IAtendimentoRepository extends JpaRepository<Atendimento, Long>{

	List<Atendimento> findByCliente(Cliente cliente);
}
