package com.loopa.api.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loopa.api.model.Usuario;


@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

}
