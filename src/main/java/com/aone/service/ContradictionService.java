package com.aone.service;

import java.util.List;

import com.aone.entity.Contradiction;

public interface ContradictionService {
	public void findAllContradictions();
	public List<Contradiction> findContradictionsByPerson(int id);

}
