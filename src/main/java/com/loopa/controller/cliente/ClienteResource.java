package com.loopa.controller.cliente;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//@RestController
@Service("clienteResource")
public class ClienteResource implements IClienteResource{

	@Autowired
	private ClienteRepository clienteRepository;

	//@GetMapping("/clientes")
	public List<Cliente> retrieveAllClientes() {
		return clienteRepository.findAll();
	}

	//@GetMapping("/clientes/{id}")
	public Cliente retrieveCliente(long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (!cliente.isPresent())
			throw new ClienteNotFoundException("id-" + id);

		return cliente.get();
	}

	//@DeleteMapping("/clientes/{id}")
	public void deleteCliente(long id) {
		clienteRepository.deleteById(id);
	}

	//@PostMapping("/clientes")
	public ResponseEntity<Object> createCliente(Cliente cliente) {
		Cliente savedCliente = clienteRepository.save(cliente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCliente.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	//@PutMapping("/clientes/{id}")
	public ResponseEntity<Object> updateCliente(Cliente cliente, long id) {

		Optional<Cliente> clienteOptional = clienteRepository.findById(id);

		if (!clienteOptional.isPresent())
			return ResponseEntity.notFound().build();

		cliente.setId(id);
		
		clienteRepository.save(cliente);

		return ResponseEntity.noContent().build();
	}
}
