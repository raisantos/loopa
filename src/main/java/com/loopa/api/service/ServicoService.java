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
import com.loopa.api.irepository.IProfissionalRepository;
import com.loopa.api.irepository.IServicoRepository;
import com.loopa.api.iservice.IProfissionalService;
import com.loopa.api.iservice.IServicoService;
import com.loopa.api.model.Servico;
import com.loopa.api.model.Profissional;

@Service("servicoService")
public class ServicoService implements IServicoService{

	@Autowired
	private IServicoRepository servicoRepository;
	
	public List<Servico> retrieveAllServicos() {
		return servicoRepository.findAll();
	}

	public Servico retrieveServico(long id) {
		Optional<Servico> servico = servicoRepository.findById(id);

		if (!servico.isPresent())
			throw new ClienteNotFoundException("id-" + id);

		return servico.get();
	}

	public void deleteServico(long id) {
		servicoRepository.deleteById(id);
	}

	public ResponseEntity<Object> createServico(Servico servico) {
		Servico servicoNovo = servicoRepository.save(servico);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(servicoNovo.getIdServico()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	public ResponseEntity<Object> updateServico(Servico servico, long id) {

		Optional<Servico> servicoOptional = servicoRepository.findById(id);

		if (!servicoOptional.isPresent())
			return ResponseEntity.notFound().build();

		servico.setIdServico(id);
		
		servicoRepository.save(servico);

		return ResponseEntity.noContent().build();
	}
}
