package com.aone.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aone.service.CommDataLoad;

import com.aone.service.NodeConvertNum;

import com.aone.algorithm.DetecteCom;
import com.aone.entity.AllNodesandEdges;
import com.aone.entity.Edges;
import com.aone.entity.Nodes;



@Controller
public class CommController {
	
	@Autowired
	@Qualifier(value="CommDataLoad")
	CommDataLoad commDataLoad;
	
	@Autowired
	@Qualifier(value="NodeConvertNum")
	NodeConvertNum nodeConvertNum;
	
	/**
	 * @author Joe
     * @function 重点人物画像模块初始界面，进行数据读入、社团划分、重要节点分析
	 * @param request
	 * @param session
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = {"/commIndex"}, method = RequestMethod.GET)
	public String commIndex(HttpServletRequest request, HttpSession session) throws Exception {
		//将通信数据封装后存入session
		ArrayList<Edges> edgeList = commDataLoad.loadAllNodes();
		session.setAttribute("AllData", edgeList);
		
		//进行社团划分，默认使用FastUnfolding算法
		long start = System.currentTimeMillis();
		AllNodesandEdges allFUNodesandEdges = new DetecteCom().DetecteCom(1, edgeList);
		System.out.println(allFUNodesandEdges.getEdgeList());
		long end = 	System.currentTimeMillis();
		System.out.println("time:"+(end - start));
		session.setAttribute("allFNUodesandEdges", allFUNodesandEdges);
		return "community";
	}

	/**
	 * @author Joe
     * @function 将后台数据转换成前台D3需要的形式，将edges拼成相应的json格式字符串
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = {"/getDatas"})
	public String getD3Datas(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		AllNodesandEdges allNodesandEdges = (AllNodesandEdges)session.getAttribute("allFNUodesandEdges");
		ArrayList<Edges> edgeList = allNodesandEdges.getEdgeList();
		ArrayList<Nodes> nodeList = allNodesandEdges.getNodeList();
		ArrayList<Edges> edges = nodeConvertNum.convertToNum(nodeList, edgeList);
		
		StringBuilder sb = nodeConvertNum.JsonDataAndWeightConvert(nodeList, (ArrayList<Edges>)edges);
		response.getWriter().write(sb.toString());
		return null;

	}
	
	@RequestMapping(value="/personInfo")
	public String getPersonInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		return "personInfo";
		
	}
}
