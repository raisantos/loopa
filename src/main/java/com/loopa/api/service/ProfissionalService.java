package com.loopa.api.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.loopa.api.exception.ProfissionalNotFoundException;
import com.loopa.api.irepository.IProfissionalRepository;
import com.loopa.api.iservice.IProfissionalService;
import com.loopa.api.model.Profissional;

@Service("profissionalService")
public class ProfissionalService implements IProfissionalService{

	@Autowired
	private IProfissionalRepository profissionalRepository;

	public List<Profissional> retrieveAllProfissionais() {
		return profissionalRepository.findAll();
	}

	public Profissional retrieveProfissional(long id) {
		Optional<Profissional> profissional = profissionalRepository.findById(id);

		if (!profissional.isPresent())
			throw new ProfissionalNotFoundException("id-" + id);

		return profissional.get();
	}

	public void deleteProfissional(long id) {
		profissionalRepository.deleteById(id);
	}

	public ResponseEntity<Object> createProfissional( Profissional profissional) {
		Profissional savedProfissional = profissionalRepository.save(profissional);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProfissional.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	public ResponseEntity<Object> updateProfissional(Profissional profissional, long id) {

		Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);

		if (!profissionalOptional.isPresent())
			return ResponseEntity.notFound().build();

		profissional.setId(id);
		
		profissionalRepository.save(profissional);

		return ResponseEntity.noContent().build();
	}
}
