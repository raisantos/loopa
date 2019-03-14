package com.loopa.api.iservice;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.loopa.api.model.Cliente;

public interface IClienteService {

	public List<Cliente> retrieveAllClientes();
	public Cliente retrieveCliente(@PathVariable long id);
	public void deleteCliente(@PathVariable long id);
	public ResponseEntity<Object> createCliente(@RequestBody Cliente cliente);
	public ResponseEntity<Object> updateCliente(@RequestBody Cliente cliente, @PathVariable long id);
}
