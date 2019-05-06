package com.loopa.api.iservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.loopa.api.model.Avaliacao;

public interface IAvaliacaoService {

	public List<Avaliacao> retrieveAllAvaliacoes();
	public Avaliacao retrieveAvaliacao(@PathVariable long id);
	public void deleteAvaliacao(@PathVariable long id);
	public ResponseEntity<Object> createAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable long profissional, @PathVariable int nota);
	public ResponseEntity<Object> updateAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable long id);
	public List<Avaliacao> findByCliente();
	public Avaliacao findByClienteAndProfissional(long profissional);
}
