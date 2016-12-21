package com.aone.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aone.entity.AllNodesandEdges;
import com.aone.entity.Edges;
import com.aone.entity.Group;
import com.aone.entity.KeyPerson;
import com.aone.entity.Nodes;
import com.aone.entity.Page;
import com.aone.service.ContradictionService;
import com.aone.service.GraphOperator;
import com.aone.service.KeyPersonService;
import com.aone.service.NodeConvertNum;

@Controller
public class KeyPersonController {

	@Autowired
	@Qualifier(value = "keyPersonService")
	KeyPersonService keyPersonService;

	@Autowired
	@Qualifier(value = "nodeConvertNum")
	NodeConvertNum nodeConvertNum;

	@Autowired
	@Qualifier(value = "graphOperator")
	GraphOperator graphOperator;

	@Autowired
	@Qualifier(value = "contradictionService")
	ContradictionService contradictionService;

	
	
	/**
	 * @author Joe
	 * @function 根据id查询重点人员的基本信息;获取社团信息;对社团进行重要节点分析;获得该重要人员的事件信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/searchById" })
	public String searchById(@RequestParam int id, HttpSession session)throws Exception  {
		//1.根据id获得重要人员基本信息
		KeyPerson kp = keyPersonService.searchById(id);
		session.setAttribute("keyPersonInfo", kp);

		//2.获得该重要人员的社团信息,对社团进行重要节点分析
		CommController commController = new CommController(); 
		commController.community(session); //判断session中的社团划分数据是否失效
		AllNodesandEdges allNodesandEdges = (AllNodesandEdges) session.getAttribute("allFNUodesandEdges");
		ArrayList<Nodes> nodeList = allNodesandEdges.getNodeList();
		ArrayList<Group> groupList = allNodesandEdges.getAllGroups();
		for (int i = 0; i < nodeList.size(); i++) {
			Nodes node = nodeList.get(i);
			if (node.getName().equals(kp.getContactInformation())) {
				for (int j = 0; j < groupList.size(); j++) {
					Group group = groupList.get(j);
					if (node.getGroupId().equals(group.getGroupId())) {
						session.setAttribute("groupByPerson", group);
						// 对子团中的节点进行重要节点分析,默认使用pagerank算法
						// num = "3" ： "pagerank";
						// num = "4" ： "closeness";
						// num = "5" ： "betweenness";
						// num = "6" ： "degree";
						// num = "7" ： "hits";
						// num = "8" ： "eigenvector";
						String num = "3";
						ArrayList<Nodes> nodesList = graphOperator
								.GraphOperator2(Integer.parseInt(num), group.getEdgeList()).getNodeList();
						ArrayList<Edges> al = nodeConvertNum.convertToNum(nodesList, group.getEdgeList());
						// 转换成json格式:[{"name":"Majed
						// Moqed","Nodeweight":"0.14706199855470242","group":0}..]
						StringBuilder sb = nodeConvertNum.JsonDataAndWeightConvert(nodesList, (ArrayList<Edges>) al);

						Comparator<Nodes> comparator = new Comparator<Nodes>() {
							public int compare(Nodes n1, Nodes n2) {
								double nw1 = Double.parseDouble(n1.getNoteWeight());
								double nw2 = Double.parseDouble(n2.getNoteWeight());
								if (Math.abs(nw1 - nw2) < 1e-7)
									return 0;
								else if (nw1 > nw2)
									return -1;
								else
									return 1;
							}
						};
						// 排序
						Collections.sort(nodesList, comparator);
						session.setAttribute("Important", nodesList);// 排序后的结果存入session
						break;
					}
				}
				break;
			}
		}

		//3.获得该重要人员的事件信息
		session.setAttribute("contradictions", contradictionService.findContradictionsByPerson(id));
		return "personInfo";
	}

	/**
	 * @author Joe
	 * @function 按照积分预警值属性分页显示重点人员的信息
	 * @param page
	 */
	@RequestMapping(value = { "/showPersonByPage" })
	public ModelAndView showPersonByPage(@RequestParam(defaultValue = "1") int currentpage, HttpSession session) {
		int pagesize = 10; // 每页显示数量
		String propertyName = "fraction";// 列表排序依据的属性
		Integer startRow = (currentpage - 1) * pagesize; // 起始记录
		boolean desc = true; // 是否采用降序排序
		
		Page page = keyPersonService.showPersonByPage(currentpage, propertyName, desc, startRow, pagesize);//获得显示内容
		session.setAttribute("page", page);

		return new ModelAndView("personList");
	}

	/**
	 * @author Joe
	 * @function 重点人员问题属地统计,重点人员问题类别统计,重点人员各级别的人数统计
	 * @param session
	 */
	@RequestMapping(value = { "/keyPersonStatus" })
	public String keyPersonStatus(HttpSession session) {
		ArrayList<HashMap<String, Integer>> list = keyPersonService.keyPersonStatus();
		session.setAttribute("territory", list.get(0));
		session.setAttribute("issueContent", list.get(1));
		session.setAttribute("level", list.get(2));
		return "community";
	}
	
	/**
	 * @author Joe
	 * @function 条件检索：获得指定时间范围内，通信次数大于num的通信信息
	 * @param num 通信次数阈值
	 * @param startDate 通话记录起始时间
	 * @param endDate 通话记录终止时间
	 * @param session
	 * @param targetPerson 目标查询用户的phone number
	 */
	@RequestMapping(value = {"/commSearch"})
	public String commSearch(@RequestParam(defaultValue="1") int num
			, @RequestParam(defaultValue="1000-00-00") String startDate
			, @RequestParam(defaultValue="NULL") String endDate
			, HttpSession session, @RequestParam String targetPerson){
		//若endDate为空，则将当前时间作为endDate
		if(endDate.equals("NULL"))
			endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) +" 23:59:59";
		else
			endDate += " 23:59:59";
		//list中第1个元素是以targetPerson作为发送者，num作为通信次数阈值获得的map，其中键为接收者的number，值为通信次数
		//list中第2个元素是以targetPerson作为接收者，num作为通信次数阈值获得的map，其中键为发送者的number，值为通信次数
		ArrayList<HashMap<String,Integer>> list =keyPersonService.commSearchService(num, startDate, endDate, targetPerson);
		session.setAttribute("commSearch",list);
		
		return null;
	}
}
