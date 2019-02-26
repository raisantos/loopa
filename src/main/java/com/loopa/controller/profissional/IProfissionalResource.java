package com.loopa.controller.profissional;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IProfissionalResource {

	public List<Profissional> retrieveAllProfissionais();
	public Profissional retrieveProfissional(long id);
	public void deleteProfissional(long id);
	public ResponseEntity<Object> createProfissional( Profissional profissional);
	public ResponseEntity<Object> updateProfissional(Profissional profissional, long id);
}
