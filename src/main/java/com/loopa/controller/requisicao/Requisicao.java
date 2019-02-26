package com.loopa.controller.requisicao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loopa.controller.cliente.Cliente;
import com.loopa.controller.cliente.IClienteResource;
import com.loopa.controller.profissional.IProfissionalResource;
import com.loopa.controller.profissional.Profissional;

@RestController
public class Requisicao {

	@Autowired
	IProfissionalResource profissionalResource;
	
	@Autowired
	IClienteResource clienteResource;
	
	Requisicao(){
	}
	
	@GetMapping("/profissionais")
	public List<Profissional> retrieveAllProfissionais() {
		return profissionalResource.retrieveAllProfissionais();
	}

	@GetMapping("/profissionais/{id}")
	public Profissional retrieveProfissional(@PathVariable long id) {
		return profissionalResource.retrieveProfissional(id);
	}

	@DeleteMapping("/profissionais/{id}")
	public void deleteProfissional(@PathVariable long id) {
		profissionalResource.deleteProfissional(id);;
	}

	@PostMapping("/profissionais")
	public ResponseEntity<Object> createProfissional(@RequestBody Profissional profissional) {
		return profissionalResource.createProfissional(profissional);
	}
	
	@PutMapping("/profissionais/{id}")
	public ResponseEntity<Object> updateProfissional(@RequestBody Profissional profissional, @PathVariable long id) {
		return profissionalResource.updateProfissional(profissional, id);
	}
	
	@GetMapping("/clientes")
	public List<Cliente> retrieveAllClientes() {
		return clienteResource.retrieveAllClientes();
	}

	@GetMapping("/clientes/{id}")
	public Cliente retrieveCliente(@PathVariable long id) {
		return clienteResource.retrieveCliente(id);

	}

	@DeleteMapping("/clientes/{id}")
	public void deleteCliente(@PathVariable long id) {
		clienteResource.deleteCliente(id);;
	}

	@PostMapping("/clientes")
	public ResponseEntity<Object> createCliente(@RequestBody Cliente cliente) {
		return clienteResource.createCliente(cliente);
	}
		
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Object> updateCliente(@RequestBody Cliente cliente, @PathVariable long id) {
		return clienteResource.updateCliente(cliente, id);
	}
}
