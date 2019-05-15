package com.loopa.api.iservice;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;


public interface IProfissionalService {

	public List<Profissional> retrieveAllProfissionais();
	public Profissional retrieveProfissional(long id);
	public void deleteProfissional(long id);
	public ResponseEntity<Object> createProfissional( Profissional profissional);
	public ResponseEntity<Object> updateProfissional(Profissional profissional, long id);
	public ResponseEntity<Object> checkIn(double latitude, double longitude);
	public Profissional findByEmail(String email);
}
