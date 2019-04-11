package com.loopa.api.iservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.loopa.api.model.Servico;

public interface IServicoService {

	public List<Servico> retrieveAllServicos();
	public Servico retrieveServico(@PathVariable long id);
	public void deleteServico(@PathVariable long id);
	public ResponseEntity<Object> createServico(@RequestBody Servico servico);
	public ResponseEntity<Object> updateServico(@RequestBody Servico servico, @PathVariable long id);
}
