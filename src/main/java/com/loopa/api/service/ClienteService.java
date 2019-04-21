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
import com.loopa.api.security.UserSS;
import com.loopa.api.service.exception.AuthorizationException;
import com.loopa.api.service.exception.ObjectNotFoundException;

@Service("clienteService")
public class ClienteService implements IClienteService{

	@Autowired
	private IClienteRepository clienteRepository;

	public List<Cliente> retrieveAllClientes() {
		return clienteRepository.findAll();
	}

	public Cliente retrieveCliente(Long id) {
		UserSS user = UserService.authenticated();
		if(user==null || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		Optional<Cliente> cliente = clienteRepository.findById(id);

		if (!cliente.isPresent())
			throw new ClienteNotFoundException("id-" + id);

		return cliente.get();
	}
	
	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
	
		Cliente obj = clienteRepository.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

	public void deleteCliente(Long id) {
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
