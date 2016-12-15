package com.aone.algorithm;

import com.aone.entity.NamedEntityCollection;


public interface NER {
	/**
	 * 快速命名实体抽取
	 * @param document	String	需要处理的文档
	 * @return	com.aone.entity.NamedEntityCollection
	 */
	public NamedEntityCollection fastNER(String document);
	
	/**
	 * 完整的命名实体抽取
	 * @param doucment	String	需要处理的文档
	 * @return	com.aone.entity.NamedEntityCollection
	 */
	public NamedEntityCollection fullNER(String doucment);
}
