package com.aone.algorithmImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.aone.algorithm.KeywordExtracter;
import com.aone.entity.Keyword;
import com.hankcs.hanlp.algoritm.MaxHeap;

@Service(value="keywordExtracter")
public class KeywordExtracterImpl implements KeywordExtracter {
	
	private static KeywordExtracterImpl instance;
	
	private TextRankKeywordExtracter extracter;
	
	private KeywordExtracterImpl(){
		extracter = TextRankKeywordExtracter.getInstance();
	};
	
	
	public static KeywordExtracterImpl getInstance(){
		if(instance == null) instance = new KeywordExtracterImpl();
		return instance;
	}
	
	@Override
	public List<Keyword> extractAllWordAndRank(String document) {
		return extracter.getAllWordAndRank(document);
		
	}

	@Override
	public List<Keyword> extractKeywordAndRank(List<Keyword> keywords, int size) {
		return extracter.getKeywordAndRank(keywords, size);
	}

	@Override
	public List<String> keywordList2StringList(List<Keyword> list) {
		return extracter.getWordFromKeywordList(list);
	}

	@Override
	public List<Keyword> extractAllWordAndRankDesc(String document) {
		return extracter.getAllWordAndRankDesc(document);
	}

	@Override
	public List<Keyword> extractKeywordAndRankFDesc(List<Keyword> keywords,
			int size) {
		return extracter.getKeywordAndRankDesc(keywords, size);
	}


	@Override
	public List<String> mergeWords(List<List<com.aone.entity.Keyword>> lists,
			int size) {
		List<String> list = new ArrayList<String>(size);
		if(lists == null || lists.isEmpty()) return list;
		
		Map<String, Float> map = new HashMap<String, Float>();
		for(List<com.aone.entity.Keyword> l : lists)
			if(l != null && ! l.isEmpty()){
				float sum = 0;
				for(com.aone.entity.Keyword kw : l){
					sum += kw.getScore();
				}
				for(com.aone.entity.Keyword kw : l){
					String word  = kw.getWord();
					float score = kw.getScore()/sum;
					map.put(word, map.containsKey(word)?map.get(word) + score : score);
				}
			}
		maxHeapSort(map, list, size);
		return list;
	}
	
	public void maxHeapSort(Map<String, Float> map, List<String> list, int size){
		if(map == null || map.isEmpty()) return;
		if(list == null || list.isEmpty()) list = new ArrayList<String>(size);
		
		for(Map.Entry<String, Float> entry : new MaxHeap<Map.Entry<String, Float>>(size, new Comparator<Map.Entry<String, Float>>(){

			@Override
			public int compare(Entry<String, Float> o1, Entry<String, Float> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
			
		}).addAll(map.entrySet()).toList()){
			list.add(entry.getKey());
		}
	}
}
