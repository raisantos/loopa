package com.loopa.api.iservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

public interface IBuscaService {

	public ArrayList<Map<String,Object>> contextualSearch(@PathVariable String servico,@PathVariable double latitude, @PathVariable double longitude) throws IOException;
}
