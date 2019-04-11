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

import com.loopa.api.iservice.IAtendimentoService;
import com.loopa.api.iservice.IBuscaService;
import com.loopa.api.model.Atendimento;

@RestController
public class AtendimentoResource {
	
	@Autowired
	IAtendimentoService atendimentoService;
	
	AtendimentoResource(){
	}

	@GetMapping("/atendimentos")
	public List<Atendimento> retrieveAllAtendimentos() {
		return atendimentoService.retrieveAllAtendimentos();
	}

	@GetMapping("/atendimentos/{id}")
	public Atendimento retrieveAtendimento(@PathVariable long id) {
		return atendimentoService.retrieveAtendimento(id);

	}

	@DeleteMapping("/atendimentos/{id}")
	public void deleteAtendimento(@PathVariable long id) {
		atendimentoService.deleteAtendimento(id);;
	}

	@PostMapping("/atendimentos")
	public ResponseEntity<Object> createAtendimento(@RequestBody Atendimento atendimento) {
		return atendimentoService.createAtendimento(atendimento);
	}
		
	@PutMapping("/atendimentos/{id}")
	public ResponseEntity<Object> updateAtendimento(@RequestBody Atendimento atendimento, @PathVariable long id) {
		return atendimentoService.updateAtendimento(atendimento, id);
	}
}
