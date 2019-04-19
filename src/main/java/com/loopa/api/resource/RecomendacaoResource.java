package com.loopa.api.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.loopa.api.iservice.IRecomendacaoService;

@RestController
@RequestMapping(value="/recomendacao")
@CrossOrigin
public class RecomendacaoResource {
	
	@Autowired
	IRecomendacaoService recomendacaoService;

	public RecomendacaoResource() {
	}
	
	@RequestMapping(value="/{latitude}/{longitude}", method=RequestMethod.GET)
	public ArrayList<Map<String,Object>> contextualPostFiltering(@PathVariable double latitude, @PathVariable double longitude) throws IOException, TasteException {
		return recomendacaoService.contextualPostFiltering(latitude, longitude);
	}
}
