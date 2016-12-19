package com.aone.dao;

import java.util.List;

import com.aone.entity.Contradiction;

public interface ContradictionDao extends SuperDao<Contradiction> {
	public List<Contradiction> findAllDao();
	public List<Contradiction> findContradictionsDao(String target);

}
