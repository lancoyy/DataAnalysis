package com.aone.algorithmImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.aone.entity.Keyword;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

/**
 * 基于TextRank算法的关键字提取，适用于单文档，基于HanLP
 * @author libin
 *
 */
public class TextRankKeywordExtracter {

	/**
	 * 使用默认分词器
	 */
	Segment defaultSegment = StandardTokenizer.SEGMENT;
	
	/**
	 * 阻尼系数（DampingFac tor），一般取值为0.85
	 */
	final static float d = 0.85f;
	/**
	 * 最大迭代次数
	 */
	final static int max_iter = 200;
	final static float min_diff = 0.001f;

	/*
	 * 单例模式
	 */
	private static TextRankKeywordExtracter textRankKeywordHerb;

	/*
	 * 单例模式
	 */
	private TextRankKeywordExtracter() {
	}

	public static TextRankKeywordExtracter getInstance() {
		if (textRankKeywordHerb == null)
			textRankKeywordHerb = new TextRankKeywordExtracter();
		return textRankKeywordHerb;
	}

	/**
	 * 提取关键词
	 * 
	 * @param document	String
	 *            文档内容
	 * @param size	int
	 *            希望提取几个关键词
	 * @return List<String>
	 */
	public List<String> getKeywordList(String document, int size) {
		TextRankKeywordExtracter textRankKeyword = TextRankKeywordExtracter
				.getInstance();
		return textRankKeyword.getKeywords(document, size);
	}

	/**
	 * 提取所有的分词
	 * @param document	String
	 * @return	List<String>
	 */
	public List<String> getAllWordList(String document) {
		TextRankKeywordExtracter textRankKeyword = TextRankKeywordExtracter
				.getInstance();
		return textRankKeyword.getAllWords(document);
	}

	/**
	 * 从Keyword列表中提取出词
	 * 
	 * @param keywords	List<Keyword>
	 *            Keyword列表
	 * @return List<String>
	 */
	public List<String> getWordFromKeywordList(List<Keyword> keywords) {
		TextRankKeywordExtracter textRankKeyword = TextRankKeywordExtracter
				.getInstance();
		return textRankKeyword.getKeyword(keywords);
	}

	/**
	 * 返回前size个关键词及对应的分数
	 * 
	 * @param document	String
	 *            文档内容
	 * @param size	int
	 *            返回的关键词的个数
	 * @return 列表
	 */
	public List<Keyword> getKeywordAndRank(String document, int size) {
		TextRankKeywordExtracter textRankKeyword = TextRankKeywordExtracter
				.getInstance();
		return textRankKeyword.getTermAndRank(document, size);
	}

	/**
	 * 从Keyword列表中获取前n个关键词及其分数
	 * 
	 * @param keywords	List<Keyword>
	 *            Keyword列表
	 * @param size	int
	 *            获取size个Keyword
	 * @return List<Keyword>
	 */
	public List<Keyword> getKeywordAndRank(List<Keyword> keywords,
			int size) {
		TextRankKeywordExtracter textRankKeyword = TextRankKeywordExtracter
				.getInstance();
		return textRankKeyword.getTermAndRank(keywords, size);
	}

	/**
	 * 从排好序的Keyword列表中获取前n个关键词及其分数
	 * 
	 * @param keywords	List<Keyword>
	 *            Keyword列表
	 * @param size	int
	 *            获取size个Keyword
	 * @return List<Keyword>
	 */
	public List<Keyword> getKeywordAndRankDesc(List<Keyword> keywords,
			int size) {
		TextRankKeywordExtracter textRankKeyword = TextRankKeywordExtracter
				.getInstance();
		return textRankKeyword.getTermAndRankFromSort(keywords, size);
	}

	/**
	 * 返回排好序的所有分词的Keyword列表
	 * 
	 * @param document	String
	 *            文档
	 * @return List<Keyword>
	 */
	public List<Keyword> getAllWordAndRankDesc(String document) {
		TextRankKeywordExtracter textRankKeyword = TextRankKeywordExtracter
				.getInstance();
		return textRankKeyword.getTermAndRankDesc(document);
	}

	/**
	 * 返回所有的分词及对应的分数
	 * 
	 * @param document	String
	 *            文档内容
	 * @return List<Keyword>
	 */
	public List<Keyword> getAllWordAndRank(String document) {
		TextRankKeywordExtracter textRankKeyword = TextRankKeywordExtracter
				.getInstance();
		return textRankKeyword.getTermAndRank(document);
	}

	/**
	 * 从Keyword列表中提取出所有的关键词
	 * 
	 * @param keywords	List<Keyword>
	 *            Keyword列表
	 * @return List<String>
	 */
	public List<String> getKeyword(List<Keyword> keywords) {
		List<String> result = new ArrayList<String>(keywords.size());
		for (Keyword keyword : keywords) {
			result.add(keyword.getWord());
		}
		return result;
	}

	/**
	 * 从content中抽取nKeyword个关键词
	 * @param content	String
	 * @param nKeyword	int
	 * @return	List<String>
	 */
	public List<String> getKeywords(String content, int nKeyword) {
		List<Keyword> keywords = getTermAndRank(content, nKeyword);
		List<String> result = new ArrayList<String>(keywords.size());
		for (Keyword keyword : keywords) {
			result.add(keyword.getWord());
		}
		return result;
	}

	/**
	 * 获取所有的分词
	 * 
	 * @param content	String
	 *            文档内容
	 * @return List<String>
	 */
	public List<String> getAllWords(String content) {
		List<Keyword> keywords = getTermAndRank(content);
		List<String> result = new ArrayList<String>(keywords.size());
		for (Keyword keyword : keywords) {
			result.add(keyword.getWord());
		}
		return result;
	}

	/**
	 * 返回全部分词结果和对应的rank
	 * 
	 * @param content	String
	 *            文档内容
	 * @return List<com.aone.entity.Keyword>
	 */
	public List<Keyword> getTermAndRank(String content) {
		assert content != null;
		List<Term> termList = defaultSegment.seg(content);
		return getRank(termList);
	}

	/**
	 * 返回排好序的分词结果和对应的rank
	 * 
	 * @param content	String
	 *            文档内容
	 * @return List<com.aone.entity.Keyword>
	 */
	public List<Keyword> getTermAndRankDesc(String content) {
		assert content != null;
		List<Term> termList = defaultSegment.seg(content);
		List<Keyword> list = getRank(termList);
		Collections.sort(list, new Comparator<Keyword>() {

			@Override
			public int compare(Keyword o1, Keyword o2) {
				return o2.getScore().compareTo(o1.getScore());
			}

		});
		return list;
	}

	/**
	 * 从Keyword列表中返回前size个关键词
	 * 
	 * @param keywords	List<com.aone.entity.Keyword>
	 *            Keyword列表
	 * @param size
	 *            返回的Keyword个数
	 * @return	List<com.aone.entity.Keyword>	
	 */
	public List<Keyword> getTermAndRank(List<Keyword> keywords, Integer size) {
		if (keywords == null)
			return new ArrayList<Keyword>();
		Collections.sort(keywords, new Comparator<Keyword>() {

			@Override
			public int compare(Keyword o1, Keyword o2) {
				return o2.getScore().compareTo(o1.getScore());
			}

		});
		return keywords.isEmpty() ? keywords : keywords.subList(0,
				keywords.size() > size ? size : keywords.size());
	}

	/**
	 * 从排好序的列表中返回前size个关键词
	 * 
	 * @param keywords List<com.aone.entity.Keyword>
	 *            Keyword列表
	 * @param size
	 *            返回的Keyword个数
	 * @return List<com.aone.entity.Keyword>
	 */
	public List<Keyword> getTermAndRankFromSort(List<Keyword> keywords,
			Integer size) {
		return keywords == null ? new ArrayList<Keyword>()
				: keywords.isEmpty() ? keywords : keywords.subList(0,
						keywords.size() > size ? size : keywords.size());
	}

	/**
	 * 返回分数最高的前size个分词结果和对应的rank
	 * 
	 * @param content	String
	 *            文档内容
	 * @param size	int 
	 *            希望提取的关键词的个数
	 * @return List<com.aone.entity.Keyword>
	 */
	public List<Keyword> getTermAndRank(String content, Integer size) {
		List<Keyword> keywords = getTermAndRank(content);
		Collections.sort(keywords, new Comparator<Keyword>() {

			@Override
			public int compare(Keyword o1, Keyword o2) {
				return o2.getScore().compareTo(o1.getScore());
			}

		});
		return keywords.isEmpty() ? keywords : keywords.subList(0,
				keywords.size() > size ? size : keywords.size());
	}

	/**
	 * 使用已经分好的词来计算rank
	 * 
	 * @param termList	List<com.hankcs.hanlp.seg.common.Term>
	 * @return
	 */
	public List<Keyword> getRank(List<Term> termList) {
		List<String> wordList = new ArrayList<String>(termList.size());
		Map<String, String> w2n = new HashMap<String, String>();
		for (Term t : termList) {
			if (com.aone.util.StopwordUtil.shouldInclude(t)) {
				String word = t.word;
				wordList.add(word);
				w2n.put(word, t.nature.name());

			}
		}
		// System.out.println(wordList);
		Map<String, Set<String>> words = new TreeMap<String, Set<String>>();
		Map<String, Integer> times = new HashMap<String, Integer>();
		Queue<String> que = new LinkedList<String>();
		for (String w : wordList) {
			if (!words.containsKey(w)) {
				words.put(w, new TreeSet<String>());
			}
			if (!times.containsKey(w)) {
				times.put(w, 1);
			} else {
				times.put(w, times.get(w) + 1);
			}
			que.offer(w);
			if (que.size() > 5) {
				que.poll();
			}

			for (String w1 : que) {
				for (String w2 : que) {
					if (w1.equals(w2)) {
						continue;
					}

					words.get(w1).add(w2);
					words.get(w2).add(w1);
				}
			}
		}
		// System.out.println(words);
		Map<String, Float> score = new HashMap<String, Float>();
		for (int i = 0; i < max_iter; ++i) {
			Map<String, Float> m = new HashMap<String, Float>();
			float max_diff = 0;
			for (Map.Entry<String, Set<String>> entry : words.entrySet()) {
				String key = entry.getKey();
				Set<String> value = entry.getValue();
				m.put(key, 1 - d);
				for (String element : value) {
					int size = words.get(element).size();
					if (key.equals(element) || size == 0)
						continue;
					m.put(key,
							m.get(key)
									+ d
									/ size
									* (score.get(element) == null ? 0 : score
											.get(element)));
				}
				max_diff = Math.max(max_diff, Math.abs(m.get(key)
						- (score.get(key) == null ? 0 : score.get(key))));
			}
			score = m;
			if (max_diff <= min_diff)
				break;
		}
		List<Keyword> list = new ArrayList<Keyword>();
		for (Map.Entry<String, Float> entry : score.entrySet()) {
			String word = entry.getKey();
			float value = entry.getValue();
			list.add(new Keyword(word, w2n.get(word), value, times.get(word)));
		}
		return list;
	}
}
