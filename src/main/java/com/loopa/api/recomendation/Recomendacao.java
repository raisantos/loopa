package com.loopa.api.recomendation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Recomendacao {

	private RestHighLevelClient restClient;
	private SearchRequest searchRequest;
	private SearchSourceBuilder searchSourceBuilder;
	private SearchResponse searchResponse;
	
	Recomendacao(){
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


	@GetMapping("/recommendation/{latitude}/{longitude}")
	public ArrayList<Map<String,Object>> collaborativeFiltering(@PathVariable double latitude, @PathVariable double longitude) throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("/home/raisantos/Documents/dataset.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		}
		return compoundQuerySearch(recommendations, latitude, longitude);
	}
	
	/*public void collaborativeFiltering() throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("/home/raisantos/Documents/dataset.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		}
		//compoundQuerySearch(recommendations, latitude, longitude);
	}*/
	
	public ArrayList<Map<String,Object>> compoundQuerySearch(List<RecommendedItem> recommendations, double latitude, double longitude) throws IOException {
			setSearchRequest(new SearchRequest("profissionais"));
			
			String ids[] = new String[3];
			for (int i = 0; i < recommendations.size(); i++) {
				ids[i] = Long.toString(recommendations.get(i).getItemID());
			}
			this.searchSourceBuilder.query(QueryBuilders.boolQuery()
					.must(QueryBuilders.idsQuery().addIds(ids))
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
}
