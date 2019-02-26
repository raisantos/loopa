package com.loopa.controller.profissional;

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
@Service("profissionalResource")
public class ProfissionalResource implements IProfissionalResource{

	@Autowired
	private ProfissionalRepository profissionalRepository;

	//@GetMapping("/profissionais")
	public List<Profissional> retrieveAllProfissionais() {
		return profissionalRepository.findAll();
	}

	//@GetMapping("/profissionais/{id}")
	public Profissional retrieveProfissional(long id) {
		Optional<Profissional> profissional = profissionalRepository.findById(id);

		if (!profissional.isPresent())
			throw new ProfissionalNotFoundException("id-" + id);

		return profissional.get();
	}

	//@DeleteMapping("/profissionais/{id}")
	public void deleteProfissional(long id) {
		profissionalRepository.deleteById(id);
	}

	//@PostMapping("/profissionais")
	public ResponseEntity<Object> createProfissional( Profissional profissional) {
		Profissional savedProfissional = profissionalRepository.save(profissional);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProfissional.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	//@PutMapping("/profissionais/{id}")
	public ResponseEntity<Object> updateProfissional(Profissional profissional, long id) {

		Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);

		if (!profissionalOptional.isPresent())
			return ResponseEntity.notFound().build();

		profissional.setId(id);
		
		profissionalRepository.save(profissional);

		return ResponseEntity.noContent().build();
	}
}
