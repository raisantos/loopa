package com.loopa.api.iservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

public interface IRecomendacaoService {

	public ArrayList<Map<String,Object>> contextualPostFiltering(double latitude, double longitude) throws IOException, TasteException;
	public ArrayList<Map<String,Object>> compoundQuerySearch(List<RecommendedItem> recommendations, double latitude, double longitude) throws IOException;
}
