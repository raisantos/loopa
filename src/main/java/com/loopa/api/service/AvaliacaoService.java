package com.loopa.api.service;

import java.net.URI;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.loopa.api.exception.ClienteNotFoundException;
import com.loopa.api.exception.ProfissionalNotFoundException;
import com.loopa.api.irepository.IAvaliacaoRepository;
import com.loopa.api.irepository.IClienteRepository;
import com.loopa.api.irepository.IProfissionalRepository;
import com.loopa.api.irepository.IServicoRepository;
import com.loopa.api.iservice.IAvaliacaoService;
import com.loopa.api.iservice.IClienteService;
import com.loopa.api.iservice.IProfissionalService;
import com.loopa.api.iservice.IServicoService;
import com.loopa.api.model.Avaliacao;
import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;
import com.loopa.api.security.UserSS;
import com.loopa.api.service.exception.AuthorizationException;

@Service("avaliacaoService")
public class AvaliacaoService implements IAvaliacaoService{

	private static DecimalFormat df2 = new DecimalFormat("#.#");
	
	@Autowired
	private IAvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IProfissionalService profissionalService;
	
	public List<Avaliacao> retrieveAllAvaliacoes() {
		return avaliacaoRepository.findAll();
	}

	public Avaliacao retrieveAvaliacao(long id) {
		Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

		if (!avaliacao.isPresent())
			throw new ClienteNotFoundException("id-" + id);

		return avaliacao.get();
	}

	public void deleteAvaliacao(long id) {
		avaliacaoRepository.deleteById(id);
	}

	public ResponseEntity<Object> createAvaliacao(long profissional, int nota) {
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Cliente cliente = clienteService.retrieveCliente(user.getId());
		Profissional p = profissionalService.retrieveProfissional(profissional);
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setId(null);
		avaliacao.setComentario(null);
		avaliacao.setCliente(cliente);
		avaliacao.setProfissional(p);
		avaliacao.setNota(nota);
		
		Avaliacao avaliacaoNovo = avaliacaoRepository.save(avaliacao);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(avaliacaoNovo.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	public ResponseEntity<Object> updateAvaliacao(Avaliacao avaliacao, long id, long profissional) {

		System.out.println(avaliacao.getId());
		System.out.println(avaliacao.getNota());
		Optional<Avaliacao> avaliacaoOptional = avaliacaoRepository.findById(id);

		if (!avaliacaoOptional.isPresent())
			return ResponseEntity.notFound().build();

		System.out.println("OK");
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Cliente cliente = clienteService.retrieveCliente(user.getId());
		avaliacao.setId(id);
		avaliacao.setCliente(cliente);
		avaliacao.setProfissional(profissionalService.retrieveProfissional(profissional));
		
		System.out.println("SALVAR");
		avaliacaoRepository.save(avaliacao);
		System.out.println("SALVO");
		return ResponseEntity.noContent().build();
	}
	
	public List<Avaliacao> findByCliente(){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Cliente cliente = clienteService.retrieveCliente(user.getId());
		return avaliacaoRepository.findByCliente(cliente);
	}
	
	public Avaliacao findByClienteAndProfissional(long profissional){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Cliente cliente = clienteService.retrieveCliente(user.getId());
		Profissional prof = profissionalService.retrieveProfissional(profissional);
		return avaliacaoRepository.findByClienteAndProfissional(cliente, prof);
	}
	
	public Double getAverageProfissional(long profissional) {
		//String n = df2.format(this.avaliacaoRepository.getAverageProfissional(profissional));
		//return Double.parseDouble(n.replace(',', '.'));
		return this.avaliacaoRepository.getAverageProfissional(profissional);
	}
}
