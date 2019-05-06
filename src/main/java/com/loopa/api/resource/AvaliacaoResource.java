package com.loopa.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loopa.api.iservice.IAvaliacaoService;
import com.loopa.api.model.Avaliacao;

@RestController
@CrossOrigin
public class AvaliacaoResource {
	
	@Autowired
	IAvaliacaoService avaliacaoService;
	
	AvaliacaoResource(){
	}
	
	//@GetMapping("/avaliacoes")
	public List<Avaliacao> retrieveAllAvaliacoes() {
		return avaliacaoService.retrieveAllAvaliacoes();
	}

	//@GetMapping("/avaliacoes/{id}")
	//public Avaliacao retrieveAvaliacao(@PathVariable long id) {
		//return avaliacaoService.retrieveAvaliacao(id);

	//}

	@DeleteMapping("/avaliacoes/{id}")
	public void deleteAvaliacao(@PathVariable long id) {
		avaliacaoService.deleteAvaliacao(id);;
	}

	@PostMapping("/avaliacoes/{profissional}/{nota}")
	public ResponseEntity<Object> createAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable long profissional, @PathVariable int nota) {
		return avaliacaoService.createAvaliacao(avaliacao, profissional, nota);
	}
		
	@PutMapping("/avaliacoes/{id}")
	public ResponseEntity<Object> updateAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable long id) {
		return avaliacaoService.updateAvaliacao(avaliacao, id);
	}

	@GetMapping("/avaliacoes")
	public List<Avaliacao> findByCliente() {
		return avaliacaoService.findByCliente();
	}
	
	@GetMapping("/avaliacoes/{profissional}")
	public Avaliacao findByClienteAndProfissional(@PathVariable long profissional) {
		return avaliacaoService.findByClienteAndProfissional(profissional);
	}
}
