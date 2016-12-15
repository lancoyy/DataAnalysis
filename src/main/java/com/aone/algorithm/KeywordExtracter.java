package com.aone.algorithm;

import java.util.List;

import com.aone.entity.Keyword;


public interface KeywordExtracter {
	/**
	 * 抽取完整的分词及其频次，出现次数
	 * @param document	String	需要处理的文档
	 * @return	List<Keyword>
	 */
	public List<Keyword> extractAllWordAndRank(String document);
	
	/**
	 * 从分词列表中获取size个关键词
	 * @param keywords	List<com.aone.entity.Keyword> 
	 * @param size	int
	 * @return	List<com.aone.entity.Keyword>
	 */
	public List<Keyword> extractKeywordAndRank(List<Keyword> keywords, int size);
	
	/**
	 * 将List<Keyword>转化为List<String>
	 * @param list	List<com.aone.entity.Keyword>
	 * @return	List<String>
	 */
	public List<String> keywordList2StringList(List<Keyword> list);
	
	/**
	 * 抽取所有的词及其词频、词出现次数、词性，并按频次降序排列
	 * @param document	String
	 * @return	List<Keyword>
	 */
	public List<Keyword> extractAllWordAndRankDesc(String document);
	
	/**
	 * 从List<Keyword>中抽取出size个关键词
	 * @param keywords	List<com.aone.entity.Keyword>
	 * @param size	int
	 * @return	List<Keyword>
	 */
	public List<Keyword> extractKeywordAndRankFDesc(List<Keyword> keywords, int size);
	
	/**
	 * 合并多个List<Keyword>，并排序，获取前size个
	 * @param lists	List<List<com.aone.entity.Keyword>>
	 * @param size	int
	 * @return	List<String>
	 */
	public List<String> mergeWords(List<List<Keyword>> lists, int size);
}
