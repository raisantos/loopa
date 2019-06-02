package com.loopa.api.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.loopa.api.exception.ProfissionalNotFoundException;
import com.loopa.api.irepository.IProfissionalRepository;
import com.loopa.api.iservice.IProfissionalService;
import com.loopa.api.model.Cliente;
import com.loopa.api.model.Profissional;
import com.loopa.api.model.enums.Perfil;
import com.loopa.api.security.UserSS;
import com.loopa.api.service.exception.AuthorizationException;
import com.loopa.api.service.exception.ObjectNotFoundException;

@Service("profissionalService")
public class ProfissionalService implements IProfissionalService{

	@Autowired
	@Lazy
	BCryptPasswordEncoder pe;
	
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
	
	public Profissional findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
	
		Profissional obj = profissionalRepository.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

	public void deleteProfissional(long id) {
		profissionalRepository.deleteById(id);
	}

	public ResponseEntity<Object> createProfissional( Profissional profissional) {
		profissional.setId(null);
		profissional.setStatus("inativo");
		profissional.setSenha(pe.encode(profissional.getSenha()));
		profissional.addPerfil(Perfil.PROFISSIONAL);
		Profissional savedProfissional = profissionalRepository.save(profissional);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProfissional.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	public ResponseEntity<Object> updateProfissional(Profissional profissional, long id) {

		Optional<Profissional> profissionalOptional = profissionalRepository.findById(id);

		if (!profissionalOptional.isPresent())
			return ResponseEntity.notFound().build();

		Profissional p = profissionalOptional.get();
		p.setEmail(profissional.getEmail());
		p.setNome(profissional.getNome());
		p.setTelefone(profissional.getTelefone());
		p.setEndereco(profissional.getEndereco());
		p.setId(id);
		
		profissionalRepository.save(p);

		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Object> checkIn(double latitude, double longitude){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Profissional profissional = profissionalRepository.getOne(user.getId());
		profissional.setLatitude(latitude);
		profissional.setLongitude(longitude);
		profissional.setStatus("ativo");
		
		profissionalRepository.save(profissional);
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Object> checkOut(){
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		Profissional profissional = profissionalRepository.getOne(user.getId());
		profissional.setStatus("inativo");
		
		profissionalRepository.save(profissional);
		return ResponseEntity.noContent().build();
	}
}
