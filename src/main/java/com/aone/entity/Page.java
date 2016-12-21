package com.aone.entity;

import java.util.List;

public class Page {
	int currentPage;
	int uppage;
	int nextpage;
	int totalpage;
	List<KeyPerson> kpList;


	public List<KeyPerson> getKpList() {
		return kpList;
	}

	public void setKpList(List<KeyPerson> kpList) {
		this.kpList = kpList;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getUppage() {
		return uppage;
	}

	public void setUppage(int uppage) {
		this.uppage = uppage;
	}

	public int getNextpage() {
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public Page(int currentPage, int totalpage, List<KeyPerson> kpList) {
		this.currentPage = currentPage;
		this.uppage = currentPage-1;
		this.nextpage = currentPage+1;
		this.totalpage = totalpage;
		this.kpList = kpList;
	}

	
}
