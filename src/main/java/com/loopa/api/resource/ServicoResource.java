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

import com.loopa.api.iservice.IBuscaService;
import com.loopa.api.iservice.IServicoService;
import com.loopa.api.model.Servico;

@RestController
public class ServicoResource {
	
	@Autowired
	IServicoService servicoService;
	
	ServicoResource(){
	}

	@GetMapping("/servicos")
	public List<Servico> retrieveAllServicos() {
		return servicoService.retrieveAllServicos();
	}

	@GetMapping("/servicos/{id}")
	public Servico retrieveServico(@PathVariable long id) {
		return servicoService.retrieveServico(id);

	}

	@DeleteMapping("/servicos/{id}")
	public void deleteServico(@PathVariable long id) {
		servicoService.deleteServico(id);;
	}

	@PostMapping("/servicos")
	public ResponseEntity<Object> createServico(@RequestBody Servico servico) {
		return servicoService.createServico(servico);
	}
		
	@PutMapping("/servicos/{id}")
	public ResponseEntity<Object> updateServico(@RequestBody Servico servico, @PathVariable long id) {
		return servicoService.updateServico(servico, id);
	}
}
