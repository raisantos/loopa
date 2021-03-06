package com.loopa.api.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loopa.api.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long>{

}
