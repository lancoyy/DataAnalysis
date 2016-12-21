package com.aone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aone.service.CommDataLoad;
import com.aone.service.DetecteCom;
import com.aone.service.GraphOperator;
import com.aone.service.KeyPersonService;
import com.aone.service.NodeConvertNum;
import com.aone.entity.AllNodesandEdges;
import com.aone.entity.Edges;
import com.aone.entity.Nodes;
import com.aone.entity.Page;
import com.aone.entity.Group;

@Controller
public class CommController {

	@Autowired
	@Qualifier(value = "commDataLoad")
	CommDataLoad commDataLoad;

	@Autowired
	@Qualifier(value = "nodeConvertNum")
	NodeConvertNum nodeConvertNum;

	@Autowired
	@Qualifier(value = "detecteCom")
	DetecteCom detecteCom;

	@Autowired
	@Qualifier(value = "graphOperator")
	GraphOperator graphOperator;

	@Autowired
	@Qualifier(value = "keyPersonService")
	KeyPersonService keyPersonService;

	/**
	 * @author Joe
	 * @function 重点人物画像模块初始界面，进行数据读入、社团划分、重点人员分页显示、重点人员统计分析
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/commIndex" }, method = RequestMethod.GET)
	public String commIndex(HttpServletRequest request, HttpSession session) throws Exception {
		//1.将通信数据封装后存入session；2.进行社团划分，默认使用FastUnfolding算法
		this.community(session);

		// 3.重点人员分页显示
		int pagesize = 10; // 每页显示数量
		String propertyName = "fraction";// 列表排序依据的属性
		Integer startRow = 0; // 起始记录
		boolean desc = true; // 是否采用降序排序
		Page page = keyPersonService.showPersonByPage(1, propertyName, desc, startRow, pagesize);// 获得显示内容
		session.setAttribute("page", page);

		// 4.重点人员统计分析:包括重点人员问题属地统计,重点人员问题类别统计,重点人员各级别的人数统计
		ArrayList<HashMap<String, Integer>> list = keyPersonService.keyPersonStatus();
		session.setAttribute("territory", list.get(0));
		session.setAttribute("issueContent", list.get(1));
		session.setAttribute("level", list.get(2));
		
		session.setAttribute("commFlag", "getDatas");
		return "commIndex";
	}
	
	//执行社团划分
	@RequestMapping(value = { "/community" }, method = RequestMethod.GET)
	public String community(HttpSession session) throws Exception {
		if (session.getAttribute("AllData") == null) {// 判断是否已执行过社团划分操作
			// 将通信数据封装后存入session
			ArrayList<Edges> edgeList = commDataLoad.loadAllNodes();
			session.setAttribute("AllData", edgeList);
			// 进行社团划分，默认使用FastUnfolding算法
			long start = System.currentTimeMillis();
			AllNodesandEdges allFUNodesandEdges = detecteCom.DetecteCom(1, edgeList);
			long end = System.currentTimeMillis();
			System.out.println("time:" + (end - start));
			session.setAttribute("allFNUodesandEdges", allFUNodesandEdges);
		}
		session.setAttribute("commFlag", "getDatas");
		return "commIndex";
	}

	/**
	 * @author Joe
	 * @function 将后台数据转换成前台D3需要的形式，将edges拼成相应的json格式字符串
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getDatas" })
	public String getD3Datas(HttpServletResponse response, HttpSession session) throws IOException {
		AllNodesandEdges allNodesandEdges = (AllNodesandEdges) session.getAttribute("allFNUodesandEdges");
		ArrayList<Edges> edgeList = allNodesandEdges.getEdgeList();
		ArrayList<Nodes> nodeList = allNodesandEdges.getNodeList();
		ArrayList<Edges> edges = nodeConvertNum.convertToNum(nodeList, edgeList);
		StringBuilder sb = nodeConvertNum.JsonDataAndWeightConvert(nodeList, (ArrayList<Edges>) edges);
		response.getWriter().write(sb.toString());
		return null;
	}

	/**
	 * @author Joe
	 * @function 根据节点名获得其所在的子团信息，并分析子团的重要节点
	 * @param nodename
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = { "/subCommunity" })
	public String subCommunity(@RequestParam String nodename, HttpSession session) throws Exception {
//		System.out.println(nodename);
		this.community(session);//若session失效，则重新执行社团划分
		AllNodesandEdges allNodesandEdges = (AllNodesandEdges) session.getAttribute("allFNUodesandEdges");
		ArrayList<Nodes> nodeList = allNodesandEdges.getNodeList();
		ArrayList<Group> groupList = allNodesandEdges.getAllGroups();
		// 搜索指定节点所在子团
		for (int i = 0; i < nodeList.size(); i++) {
			Nodes node = nodeList.get(i);
			if (node.getName().equals(nodename)) {
				for (int j = 0; j < groupList.size(); j++) {
					Group group = groupList.get(j);
					if (node.getGroupId().equals(group.getGroupId())) {
						session.setAttribute("graphGroup", group);
						break;
					}
				}
				break;
			}
		}
		session.setAttribute("commFlag", "getSubDatas");
		return "commIndex";
	}

	/**
	 * @author Joe
	 * @function 将子团数据转换成前台D3需要的形式，将edges拼成相应的json格式字符串
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = { "/getSubDatas" })
	public String getD3SubDatas(HttpServletResponse response, HttpSession session) throws IOException {
		Group group = (Group) session.getAttribute("graphGroup");
		ArrayList<Edges> edgeList = group.getEdgeList();
		ArrayList<Nodes> nodeList = group.getNoteList();

		ArrayList<Edges> edges = nodeConvertNum.convertToNum(nodeList, edgeList);

		StringBuilder sb = nodeConvertNum.JsonDataAndWeightConvert(nodeList, (ArrayList<Edges>) edges);
		response.getWriter().write(sb.toString());
		return null;
	}
}
