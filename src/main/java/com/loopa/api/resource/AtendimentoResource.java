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

import com.loopa.api.iservice.IAtendimentoService;
import com.loopa.api.model.Atendimento;
import com.loopa.api.model.Avaliacao;

@RestController
@CrossOrigin
public class AtendimentoResource {
	
	@Autowired
	IAtendimentoService atendimentoService;
	
	AtendimentoResource(){
	}

	//@GetMapping("/atendimentos")
	//public List<Atendimento> retrieveAllAtendimentos() {
	//	return atendimentoService.retrieveAllAtendimentos();
	//}

	@GetMapping("/atendimentos/{id}")
	public Atendimento retrieveAtendimento(@PathVariable long id) {
		return atendimentoService.retrieveAtendimento(id);

	}

	@DeleteMapping("/atendimentos/{id}")
	public void deleteAtendimento(@PathVariable long id) {
		atendimentoService.deleteAtendimento(id);;
	}

	@PostMapping("/atendimentos/{profissional}")
	public ResponseEntity<Object> createAtendimento(@PathVariable long profissional) {
		return atendimentoService.createAtendimento(profissional);
	}
		
	@PutMapping("/atendimentos/{id}")
	public ResponseEntity<Object> updateAtendimento(@RequestBody Atendimento atendimento, @PathVariable long id) {
		return atendimentoService.updateAtendimento(atendimento, id);
	}
	
	@GetMapping("/atendimentos")
	public List<Atendimento> findByCliente() {
		return atendimentoService.findByCliente();
	}
}
