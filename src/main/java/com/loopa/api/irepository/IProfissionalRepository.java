package com.loopa.api.irepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.loopa.api.model.Profissional;


@Repository
public interface IProfissionalRepository extends JpaRepository<Profissional, Long>{

	@Transactional(readOnly=true)
	Profissional findByEmail(String email);
}
