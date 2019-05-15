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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loopa.api.iservice.IClienteService;
import com.loopa.api.iservice.IProfissionalService;
import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;

@RestController
@CrossOrigin
public class Requisicao {

	@Autowired
	IProfissionalService profissionalService;
	
	@Autowired
	IClienteService clienteService;
	
	Requisicao(){
	}
	
	@GetMapping("/profissionais")
	public List<Profissional> retrieveAllProfissionais() {
		return profissionalService.retrieveAllProfissionais();
	}

	@GetMapping("/profissionais/{id}")
	public Profissional retrieveProfissional(@PathVariable long id) {
		return profissionalService.retrieveProfissional(id);
	}
	
	@RequestMapping(value="/profissionais/email", method=RequestMethod.GET)
	public ResponseEntity<Profissional> findProfissional(@RequestParam(value="value") String email) {
		Profissional obj = profissionalService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/profissionais/{id}")
	public void deleteProfissional(@PathVariable long id) {
		profissionalService.deleteProfissional(id);;
	}

	@PostMapping("/profissionais")
	public ResponseEntity<Object> createProfissional(@RequestBody Profissional profissional) {
		return profissionalService.createProfissional(profissional);
	}
	
	@PutMapping("/profissionais/{id}")
	public ResponseEntity<Object> updateProfissional(@RequestBody Profissional profissional, @PathVariable long id) {
		return profissionalService.updateProfissional(profissional, id);
	}
	
	@PutMapping("/profissionais/checkin/{latitude}/{longitude}")
	public ResponseEntity<Object> checkIn(@PathVariable double latitude, @PathVariable double longitude) {
		return profissionalService.checkIn(latitude, longitude);
	}
	
	@PutMapping("/profissionais/checkout")
	public ResponseEntity<Object> checkOut() {
		return profissionalService.checkOut();
	}
	
	@GetMapping("/clientes")
	public List<Cliente> retrieveAllClientes() {
		return clienteService.retrieveAllClientes();
	}

	@GetMapping("/clientes/{id}")
	public Cliente retrieveCliente(@PathVariable long id) {
		return clienteService.retrieveCliente(id);

	}

	@DeleteMapping("/clientes/{id}")
	public void deleteCliente(@PathVariable long id) {
		clienteService.deleteCliente(id);;
	}

	@PostMapping("/clientes")
	public ResponseEntity<Object> createCliente(@RequestBody Cliente cliente) {
		return clienteService.createCliente(cliente);
	}
		
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Object> updateCliente(@RequestBody Cliente cliente, @PathVariable long id) {
		return clienteService.updateCliente(cliente, id);
	}
	
	@RequestMapping(value="/clientes/email", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@RequestParam(value="value") String email) {
		Cliente obj = clienteService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
}
