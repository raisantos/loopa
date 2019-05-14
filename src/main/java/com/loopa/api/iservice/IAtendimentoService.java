package com.loopa.api.iservice;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.loopa.api.model.Atendimento;

public interface IAtendimentoService {
	
	public List<Atendimento> retrieveAllAtendimentos();
	public Atendimento retrieveAtendimento(@PathVariable long id);
	public void deleteAtendimento(@PathVariable long id);
	public ResponseEntity<Object> createAtendimento(@PathVariable long profissional);
	public ResponseEntity<Object> updateAtendimento(@RequestBody Atendimento atendimento, @PathVariable long id);
	public List<Atendimento> findByCliente();
}
