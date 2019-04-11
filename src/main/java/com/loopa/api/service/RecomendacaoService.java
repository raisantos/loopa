package com.loopa.api.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.http.HttpHost;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.PostgreSQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
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
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.stereotype.Service;

import com.loopa.api.iservice.IRecomendacaoService;


@Service("recomendacaoService")
public class RecomendacaoService implements IRecomendacaoService{

	private RestHighLevelClient restClient;
	private SearchRequest searchRequest;
	private SearchSourceBuilder searchSourceBuilder;
	private SearchResponse searchResponse;
	
	RecomendacaoService(){
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

	public ArrayList<Map<String, Object>> contextualPostFiltering(double latitude, double longitude) throws IOException, TasteException {
		PGSimpleDataSource postgreDataSource = new PGSimpleDataSource();
		postgreDataSource.setServerName("localhost");
		postgreDataSource.setUser("loopa");
		postgreDataSource.setPassword("loopa");
		postgreDataSource.setDatabaseName("loopa");
		
		JDBCDataModel postgreModel = new PostgreSQLJDBCDataModel(postgreDataSource,"teste","id_cliente","id_profissional", "nota", null);
		System.out.println(postgreModel.getNumUsers());
		System.out.println(postgreModel.getNumItems());
		System.out.println(postgreModel.getNumUsersWithPreferenceFor(2));
		System.out.println(postgreModel.getNumUsersWithPreferenceFor(3));
		System.out.println(postgreModel.getNumUsersWithPreferenceFor(7));
		FastByIDMap<PreferenceArray> array = postgreModel.exportWithPrefs();
		for (int i = 1; i <= array.size(); i++ ) {
			System.out.println(array.get(i));
		}
		//DataModel model = new FileDataModel(new File("/home/raisantos/Documents/dataset.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(postgreModel);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0, similarity, postgreModel);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(postgreModel, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		System.out.println(recommendations.size());
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + recommendation);
		}
		System.out.println("#####################################################");
		return compoundQuerySearch(recommendations, latitude, longitude);
	}
	/*public ArrayList<Map<String,Object>> contextualPostFiltering(double latitude, double longitude) throws IOException, TasteException {
		DataModel model = new FileDataModel(new File("/home/raisantos/Documents/dataset.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		}
		return compoundQuerySearch(recommendations, latitude, longitude);
	}*/
	
	public ArrayList<Map<String,Object>> compoundQuerySearch(List<RecommendedItem> recommendations, double latitude, double longitude) throws IOException {
			setSearchRequest(new SearchRequest("profissionais"));
			
			String ids[] = new String[3];
			for (int i = 0; i < recommendations.size(); i++) {
				ids[i] = Long.toString(recommendations.get(i).getItemID());
				System.out.println(Long.toString(recommendations.get(i).getItemID()));
			}
			this.searchSourceBuilder.query(QueryBuilders.boolQuery()
					.must(QueryBuilders.idsQuery().addIds(ids))
					.must(QueryBuilders.matchQuery("status", "ativo"))
					.must(QueryBuilders.geoDistanceQuery("location").point(latitude, longitude).distance(15, DistanceUnit.KILOMETERS)));
			this.searchRequest.source(searchSourceBuilder);
			setSearchResponse(restClient.search(searchRequest, RequestOptions.DEFAULT));
			SearchHits searchHits = getSearchResponse().getHits();
			System.out.println("HITS = " + searchHits.getTotalHits());
			ArrayList<Map<String,Object>> recommendationList = new ArrayList<Map<String,Object>>();
			for(SearchHit s: searchHits) {
				recommendationList.add(s.getSourceAsMap());
			}
			return recommendationList;
		}
}
