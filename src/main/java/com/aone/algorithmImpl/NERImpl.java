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

import com.aone.algorithm.NER;
import com.aone.entity.NameEntity;
import com.aone.entity.NamedEntityCollection;
import com.aone.entity.TimeEntity;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import edu.fudan.nlp.cn.ner.TimeNormalizer;
import edu.fudan.nlp.cn.ner.TimeUnit;

/**
 * 命名实体抽取
 * @author libin
 *
 */

public class NERImpl implements NER {
	
	/**
	 * 单例模式
	 */
	private static NERImpl instance;
	
	/**
	 * 分词器
	 */
	private Segment segment;
	
    /**
     * 阻尼系数，一般取值为0.85
     */
    final static float d = 0.85f;
    
    /**
     * 最大迭代次数
     */
    final static int max_iter = 200;
    final static float min_diff = 0.001f;
    
    /**
     * 单例模式
     */
	private NERImpl() {
		segment = HanLP.newSegment().enableNameRecognize(true)
				.enablePartOfSpeechTagging(true)
				.enableOrganizationRecognize(true);
	}
	
	/**
	 * 获取示例
	 * @return
	 */
	public static NERImpl getInstance(){
		if(instance == null) instance = new NERImpl();
		return instance;
	}
	
	/**
	 * 快速实体抽取
	 */
	@Override
	public NamedEntityCollection fastNER(String document) {
		
		NamedEntityCollection nec = new NamedEntityCollection();
		if(document == null) return nec;
		List<Term> list = segment.seg(document);
		if(list != null && !list.isEmpty()){
			for(Term t : list){
				String word = t.word;
				String nature = t.nature.name();
				NameEntity ne = new NameEntity(word, nature);
				putNe2Nec(nec, ne, nature);
			}
		}
		nec.setTimeEntities(timeER(document));
		return nec;
	}
	
	/**
	 * 完整命名实体抽取
	 */
	@Override
	public NamedEntityCollection fullNER(String document) {
		NamedEntityCollection nec = new NamedEntityCollection();
		if(document == null) return nec;
		List<Term> list = segment.seg(document);
		List<com.aone.entity.Keyword> wordRank = getRank(list);
		Collections.sort(wordRank, new Comparator<com.aone.entity.Keyword>() {

			@Override
			public int compare(com.aone.entity.Keyword o1, com.aone.entity.Keyword o2) {
				return o2.getScore().compareTo(o1.getScore());
			}

		});
		for(com.aone.entity.Keyword word : wordRank){
			String name = word.getWord();
			String nature = word.getNature();
			Float score = word.getScore();
			int times = word.getTimes();
			NameEntity ne = new NameEntity(name, nature, score, times);
			putNe2Nec(nec, ne, nature);
		}
		nec.setTimeEntities(timeER(document));
		return nec;
	}
	
	/**
	 * 判断需要将NameEntity放入NamedEntityCollection中的那个列表中
	 * @param nec	com.aone.entity.NamedEntityCollection
	 * @param ne	com.aone.entity.NameEntity
	 * @param nature	String 词性
	 */
	private void putNe2Nec(NamedEntityCollection nec, NameEntity ne, String nature){
		if(nature.startsWith("nr"))
			nec.getNameEntities().add(ne);
		else if(nature.startsWith("ns"))
			nec.getPlaceEntities().add(ne);
		else if(nature.startsWith("nt"))
			nec.getOrginalEntities().add(ne);
		else if(nature.startsWith("nh") || nature.startsWith("nn"))
			nec.getOtherEntities().add(ne);	
	}
	
	/**
	 * 时间实体抽取
	 * @param document	需要进行抽取的文档
	 * @return	List<com.aone.entity.TimeEntity>
	 */
	public List<TimeEntity> timeER(String document){
		List<TimeEntity> list = new LinkedList<TimeEntity>();
		TimeNormalizer normalizer;
		normalizer = new TimeNormalizer("./models/TimeExp.m");
		normalizer.parse(document);
		TimeUnit[] units = normalizer.getTimeUnit();
		if(units == null) return list;
		for(TimeUnit unit : units)
			list.add(new TimeEntity(unit.Time_Expression, unit.Time_Norm, unit.getTime()));
		return list;
	}
	
	/**
	 * 对分词进行计算词频，出现次数统计
	 * @param termList	List<Term>	分词列表
	 * @return	List<com.aone.entity.Keyword>
	 */
	public List<com.aone.entity.Keyword> getRank(List<Term> termList) {
		List<String> wordList = new ArrayList<String>(termList.size());
		Map<String, Nature> w2n = new HashMap<String, Nature>();
		for (Term t : termList) {
			if (com.aone.util.StopwordUtil.shouldInclude(t)) {
				String word = t.word;
				wordList.add(word);
				w2n.put(word, t.nature);

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
		List<com.aone.entity.Keyword> list = new ArrayList<com.aone.entity.Keyword>();
		for (Map.Entry<String, Float> entry : score.entrySet()) {
			String word = entry.getKey();
			float value = entry.getValue();
			list.add(new com.aone.entity.Keyword(word, w2n.get(word).name(), value, times.get(word)));
		}
		return list;
	}

}
