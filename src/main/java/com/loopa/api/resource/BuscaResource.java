package com.loopa.api.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loopa.api.iservice.IBuscaService;

@RestController
@RequestMapping(value="/busca")
public class BuscaResource {

	@Autowired
	IBuscaService buscaService;
	
	BuscaResource(){
	}
	
	@RequestMapping(value="/{servico}/{latitude}/{longitude}", method=RequestMethod.GET)
	public ArrayList<Map<String,Object>> contextualSearch(@PathVariable String servico,@PathVariable double latitude, @PathVariable double longitude) throws IOException {
		return buscaService.contextualSearch(servico, latitude, longitude);
		
	}

}
