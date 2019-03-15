package com.loopa.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import com.loopa.api.iservice.IBuscaService;

@Service("buscaService")
public class BuscaService implements IBuscaService{
	
	private RestHighLevelClient restClient;
	private SearchRequest searchRequest;
	private SearchSourceBuilder searchSourceBuilder;
	private SearchResponse searchResponse;
	
	public BuscaService() {
		restClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
		searchSourceBuilder = new SearchSourceBuilder();
	}
	
	public RestHighLevelClient getRestClient() {
		return restClient;
	}

	public void setRestClient(RestHighLevelClient restClient) {
		this.restClient = restClient;
	}

	public SearchRequest getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(SearchRequest searchRequest) {
		this.searchRequest = searchRequest;
	}

	public SearchSourceBuilder getSearchSourceBuilder() {
		return searchSourceBuilder;
	}

	public void setSearchSourceBuilder(SearchSourceBuilder searchSourceBuilder) {
		this.searchSourceBuilder = searchSourceBuilder;
	}

	public SearchResponse getSearchResponse() {
		return searchResponse;
	}

	public void setSearchResponse(SearchResponse searchResponse) {
		this.searchResponse = searchResponse;
	}
	
	public void closeRestClient() throws IOException {
		try {
			getRestClient().close();
		} catch (IOException e) {
			System.out.println("Exception in Close Rest Client: " + e.getMessage());
		}
	}
	
	public void makeSearchRequest(String tipo, String termo) throws IOException {
		
		setSearchRequest(new SearchRequest("profissional"));
		this.searchSourceBuilder.query(QueryBuilders.matchQuery(tipo, termo));
		this.searchRequest.source(searchSourceBuilder);
	}
	
	public ArrayList<Map<String,Object>> contextualSearch(String servico, double latitude, double longitude) throws IOException {
		setSearchRequest(new SearchRequest("profissionais"));
		//double lat =  latitude
		this.searchSourceBuilder.query(QueryBuilders.boolQuery()
				.must(QueryBuilders.matchQuery("servico", servico))
				.must(QueryBuilders.matchQuery("status", "ativo"))
				.must(QueryBuilders.geoDistanceQuery("location").point(latitude, longitude).distance(15, DistanceUnit.KILOMETERS)));
		this.searchRequest.source(searchSourceBuilder);
		setSearchResponse(restClient.search(searchRequest, RequestOptions.DEFAULT));
		SearchHits searchHits = getSearchResponse().getHits();
		ArrayList<Map<String,Object>> a = new ArrayList<Map<String,Object>>();
		for(SearchHit s: searchHits) {
			a.add(s.getSourceAsMap());
		}
		return a;
	}
	
	public void retrieveSearchResponse() throws IOException {
		try {
			setSearchResponse(restClient.search(searchRequest, RequestOptions.DEFAULT));
			getSearchHits();
		} catch (Exception e) {
			System.out.println("IOException in Search Response");
		}
	}
	
	public void getSearchHits() {
		SearchHits searchHits = getSearchResponse().getHits();
		for (SearchHit searchHit : searchHits) {
			Map<String, Object> profissional = searchHit.getSourceAsMap();
			System.out.println("PROFISSIONAL " + searchHit.getId());
			System.out.println("Nome: " + profissional.get("nome"));
			System.out.println("Endereço: " + profissional.get("endereco"));
			System.out.println("Serviço: " + profissional.get("servico"));
			System.out.println("Telefone: " + profissional.get("telefone") + "\n");
		};
	}
}
