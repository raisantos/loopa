package com.loopa.api.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.loopa.api.exception.ClienteNotFoundException;
import com.loopa.api.exception.ProfissionalNotFoundException;
import com.loopa.api.irepository.IAtendimentoRepository;
import com.loopa.api.irepository.IProfissionalRepository;
import com.loopa.api.irepository.IServicoRepository;
import com.loopa.api.iservice.IAtendimentoService;
import com.loopa.api.iservice.IProfissionalService;
import com.loopa.api.iservice.IServicoService;
import com.loopa.api.model.Atendimento;
import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;

@Service("atendimentoService")
public class AtendimentoService implements IAtendimentoService{

	@Autowired
	private IAtendimentoRepository atendimentoRepository;
	
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

	public ResponseEntity<Object> createAtendimento(Atendimento atendimento) {
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
}
