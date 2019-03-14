package com.loopa.api.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loopa.api.model.Profissional;


@Repository
public interface IProfissionalRepository extends JpaRepository<Profissional, Long>{

}
