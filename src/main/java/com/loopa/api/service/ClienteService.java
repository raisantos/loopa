package com.loopa.api.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.loopa.api.exception.ClienteNotFoundException;
import com.loopa.api.irepository.IClienteRepository;
import com.loopa.api.iservice.IClienteService;
import com.loopa.api.model.Cliente;

@Service("clienteService")
public class ClienteService implements IClienteService{

	@Autowired
	private IClienteRepository clienteRepository;

	public List<Cliente> retrieveAllClientes() {
		return clienteRepository.findAll();
	}

	public Cliente retrieveCliente(long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (!cliente.isPresent())
			throw new ClienteNotFoundException("id-" + id);

		return cliente.get();
	}

	public void deleteCliente(long id) {
		clienteRepository.deleteById(id);
	}

	public ResponseEntity<Object> createCliente(Cliente cliente) {
		Cliente savedCliente = clienteRepository.save(cliente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCliente.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	public ResponseEntity<Object> updateCliente(Cliente cliente, long id) {

		Optional<Cliente> clienteOptional = clienteRepository.findById(id);

		if (!clienteOptional.isPresent())
			return ResponseEntity.notFound().build();

		cliente.setId(id);
		
		clienteRepository.save(cliente);

		return ResponseEntity.noContent().build();
	}
}
