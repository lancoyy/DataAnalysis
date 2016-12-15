package com.aone.service;

import java.util.List;

import org.gephi.partition.api.Partition;

import com.aone.entity.AllNodesandEdges;

public interface DetecteCom {
	
	public AllNodesandEdges DetecteCom(int num,List listAll) throws Exception;
	public void partitionUpdate1(Partition p);
	public void keynodeUpdate(int min, int max, String columnId);
	public void valueChanged(String value);

}
