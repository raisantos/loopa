package com.loopa.api.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loopa.api.iservice.IAvaliacaoService;
import com.loopa.api.iservice.IBuscaService;
import com.loopa.api.iservice.IServicoService;
import com.loopa.api.model.Avaliacao;

@RestController
public class AvaliacaoResource {
	
	@Autowired
	IAvaliacaoService avaliacaoService;
	
	AvaliacaoResource(){
	}
	
	@GetMapping("/avaliacoes")
	public List<Avaliacao> retrieveAllAvaliacaos() {
		return avaliacaoService.retrieveAllAvaliacoes();
	}

	@GetMapping("/avaliacoes/{id}")
	public Avaliacao retrieveAvaliacao(@PathVariable long id) {
		return avaliacaoService.retrieveAvaliacao(id);

	}

	@DeleteMapping("/avaliacoes/{id}")
	public void deleteAvaliacao(@PathVariable long id) {
		avaliacaoService.deleteAvaliacao(id);;
	}

	@PostMapping("/avaliacoes")
	public ResponseEntity<Object> createAvaliacao(@RequestBody Avaliacao avaliacao) {
		return avaliacaoService.createAvaliacao(avaliacao);
	}
		
	@PutMapping("/avaliacoes/{id}")
	public ResponseEntity<Object> updateAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable long id) {
		return avaliacaoService.updateAvaliacao(avaliacao, id);
	}

}
