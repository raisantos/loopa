package com.loopa.api.service;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.loopa.api.exception.ClienteNotFoundException;
import com.loopa.api.exception.ProfissionalNotFoundException;
import com.loopa.api.irepository.IAtendimentoRepository;
import com.loopa.api.irepository.IClienteRepository;
import com.loopa.api.irepository.IProfissionalRepository;
import com.loopa.api.irepository.IServicoRepository;
import com.loopa.api.iservice.IAtendimentoService;
import com.loopa.api.iservice.IProfissionalService;
import com.loopa.api.iservice.IServicoService;
import com.loopa.api.model.Atendimento;
import com.loopa.api.model.Avaliacao;
import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;
import com.loopa.api.security.UserSS;
import com.loopa.api.service.exception.AuthorizationException;
import com.loopa.api.util.RandomString;

import io.netty.util.internal.ThreadLocalRandom;

@Service("atendimentoService")
public class AtendimentoService implements IAtendimentoService{

	@Autowired
	private IAtendimentoRepository atendimentoRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IProfissionalRepository profissionalRepository;
	
	public List<Atendimento> retrieveAllAtendimentos() {
		return atendimentoRepository.findAll();
	}

	public Atendimento retrieveAtendimento(long id) {
		Optional<Atendimento> atendimento = atendimentoRepository.findById(id);

		if (!atendimento.isPresent())
			throw new ClienteNotFoundException("id-" + id);

		return atendimento.get();
	}

	public void deleteAtendimento(long id) {
		atendimentoRepository.deleteById(id);
	}

	public ResponseEntity<Object> createAtendimento(long profissional) {
		RandomString codigo = new RandomString(10, ThreadLocalRandom.current(), "ABCDEFGHIJKLMNOPQRSTWXYZ0123456789");
		
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Cliente cliente = clienteRepository.getOne(user.getId());
		
		Atendimento atendimento = new Atendimento();
		atendimento.setCliente(cliente);
		atendimento.setCodigo(codigo.nextString());
		atendimento.setData(new Date());
		atendimento.setProfissional(profissionalRepository.getOne(profissional));
		
		Atendimento atendimentoNovo = atendimentoRepository.save(atendimento);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(atendimentoNovo.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	public ResponseEntity<Object> updateAtendimento(Atendimento atendimento, long id) {

		Optional<Atendimento> atendimentoOptional = atendimentoRepository.findById(id);

		if (!atendimentoOptional.isPresent())
			return ResponseEntity.notFound().build();

		atendimento.setId(id);
		
		atendimentoRepository.save(atendimento);

		return ResponseEntity.noContent().build();
	}
	
	public List<Atendimento> findByCliente(){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Cliente cliente = clienteRepository.getOne(user.getId());
		return atendimentoRepository.findByCliente(cliente);
	}
}
