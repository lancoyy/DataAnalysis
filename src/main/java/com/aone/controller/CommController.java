package com.aone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aone.service.CommDataLoad;
import com.aone.service.DetecteCom;
import com.aone.service.GraphOperator;
import com.aone.service.NodeConvertNum;
import com.aone.entity.AllNodesandEdges;
import com.aone.entity.Edges;
import com.aone.entity.Nodes;
import com.aone.entity.Group;



@Controller
public class CommController {
	
	@Autowired
	@Qualifier(value="commDataLoad")
	CommDataLoad commDataLoad;
	
	@Autowired
	@Qualifier(value="nodeConvertNum")
	NodeConvertNum nodeConvertNum;
	
	@Autowired
	@Qualifier(value="detecteCom")
	DetecteCom detecteCom;
	
	@Autowired
	@Qualifier(value="graphOperator")
	GraphOperator graphOperator;
	
	/**
	 * @author Joe
     * @function 重点人物画像模块初始界面，进行数据读入、社团划分
	 * @param request
	 * @param session
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value = {"/community"}, method = RequestMethod.GET)
	public String community(HttpServletRequest request, HttpSession session) throws Exception {
		//从子团返回初始界面时，判断是否已执行过社团划分
		if(session.getAttribute("AllData") == null){
			//将通信数据封装后存入session
			ArrayList<Edges> edgeList = commDataLoad.loadAllNodes();
			session.setAttribute("AllData", edgeList);
			//进行社团划分，默认使用FastUnfolding算法
			long start = System.currentTimeMillis();
			AllNodesandEdges allFUNodesandEdges = detecteCom.DetecteCom(1, edgeList);
			long end = 	System.currentTimeMillis();
			System.out.println("time:"+(end - start));
			session.setAttribute("allFNUodesandEdges", allFUNodesandEdges);
			
			//进行重要节点分析
//			num = "3" ： "pagerank";
//			num = "4" ： "closeness";
//			num = "5" ： "betweenness";
//			num = "6" ： "degree";
//			num = "7" ： "hits";
//			num = "8" ： "eigenvector";
//			String num ="3";
//			String algorithm = "pagerank"; //默认使用pagerank算法
//			ArrayList<Nodes> nodesList = graphOperator.
//					GraphOperator2(Integer.parseInt(num), allFUNodesandEdges.getEdgeList()).getNodeList();
//			ArrayList<Edges> al = nodeConvertNum.convertToNum(nodesList, edgeList);
//			//转换成json格式:[{"name":"Majed Moqed","Nodeweight":"0.14706199855470242","group":0}..]
//			StringBuilder sb = nodeConvertNum.JsonDataAndWeightConvert(nodesList, (ArrayList<Edges>)al);
//			session.setAttribute("importantJson",sb );
//			Comparator<Nodes> comparator = new Comparator<Nodes>(){
//				public int compare(Nodes n1, Nodes n2) {
//					double nw1 = Double.parseDouble(n1.getNoteWeight());
//					double nw2 = Double.parseDouble(n2.getNoteWeight());
//					if(Math.abs(nw1-nw2)<1e-7)return 0;
//					else if(nw1>nw2)return -1;
//					else return 1; 
//				}
//			};
//			Collections.sort(nodesList, comparator);//按照权重对节点进行排序
//			session.setAttribute("importantList", nodesList);//排序后的结果存入session
			//测试
//			for(int i =0;i<nodesList.size();i++){
//				System.out.println(nodesList.get(i).getName()+" : "+nodesList.get(i).getNoteWeight());
//			}
		}
		return "community";
	}

	/**
	 * @author Joe
     * @function 将后台数据转换成前台D3需要的形式，将edges拼成相应的json格式字符串
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = {"/getDatas"})
	public String getD3Datas(HttpServletResponse response, HttpSession session) throws IOException{
		AllNodesandEdges allNodesandEdges = (AllNodesandEdges)session.getAttribute("allFNUodesandEdges");
		ArrayList<Edges> edgeList = allNodesandEdges.getEdgeList();
		ArrayList<Nodes> nodeList = allNodesandEdges.getNodeList();
		ArrayList<Edges> edges = nodeConvertNum.convertToNum(nodeList, edgeList);
		StringBuilder sb = nodeConvertNum.JsonDataAndWeightConvert(nodeList, (ArrayList<Edges>)edges);
		response.getWriter().write(sb.toString());
		return null;
	}
	
	/**
	 * @author Joe
	 * @function 根据节点名获得其所在的子团信息，并分析子团的重要节点
	 * @param nodename
	 * @param session
	 * @return
	 */
	@RequestMapping(value = {"/subCommunity"})
	public String subCommunity(@RequestParam String nodename, HttpSession session){
//		System.out.println(nodename);
		AllNodesandEdges allNodesandEdges = (AllNodesandEdges)session.getAttribute("allFNUodesandEdges");
		ArrayList<Nodes> nodeList = allNodesandEdges.getNodeList();
		ArrayList<Group> groupList = allNodesandEdges.getAllGroups();
		//搜索指定节点所在子团
		for(int i=0;i<nodeList.size();i++){
			Nodes node = nodeList.get(i);
			if(node.getName().equals(nodename)){
				for(int j=0;j<groupList.size();j++){
					Group group = groupList.get(j);
					if(node.getGroupId().equals(group.getGroupId())){
						session.setAttribute("graphGroup", group);
						break;
					}
				}
				break;
			}
		}
		return "childGraph";
	}
	
	/**
	 * @author Joe
     * @function 将子团数据转换成前台D3需要的形式，将edges拼成相应的json格式字符串
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = {"/getSubDatas"})
	public String getD3SubDatas(HttpServletResponse response, HttpSession session) throws IOException{
		Group group = (Group)session.getAttribute("graphGroup");
		ArrayList<Edges> edgeList = group.getEdgeList();
		ArrayList<Nodes> nodeList = group.getNoteList();
		
		ArrayList<Edges> edges = nodeConvertNum.convertToNum(nodeList, edgeList);
		
		StringBuilder sb = nodeConvertNum.JsonDataAndWeightConvert(nodeList, (ArrayList<Edges>)edges);
		response.getWriter().write(sb.toString());
		return null;
	}	
}
