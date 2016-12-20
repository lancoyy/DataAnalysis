package com.aone.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aone.entity.AllNodesandEdges;
import com.aone.entity.Edges;
import com.aone.entity.Group;
import com.aone.entity.KeyPerson;
import com.aone.entity.Nodes;
import com.aone.service.ContradictionService;
import com.aone.service.DetecteCom;
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
	public String searchById(@RequestParam int id, HttpSession session) {
		// 根据id获得重要人员基本信息
		KeyPerson kp = keyPersonService.searchById(id);
		session.setAttribute("keyPersonInfo", kp);

		// 获得该重要人员的社团信息,对社团进行重要节点分析
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
		// 获得该重要人员的事件信息
		session.setAttribute("contradictions", contradictionService.findContradictionsByPerson(id));
		return "personInfo";
	}

	/**
	 * @author Joe
	 * @function 按照积分预警值属性分页显示重点人员的信息
	 * @param page
	 */
	@RequestMapping(value = { "/showPersonByPage" })
	public String showPersonByPage(@RequestParam(defaultValue = "1") int page, HttpSession session) {
		int pagesize = 10; // 每页显示数量
		String propertyName = "fraction";// 列表排序依据的属性
		Integer startRow = (page - 1) * pagesize; // 起始记录
		boolean desc = true; // 是否采用降序排序
		List<KeyPerson> kpList = keyPersonService.showPersonByPage(propertyName, desc, startRow, pagesize);
		session.setAttribute("PersonList", kpList);
		session.setAttribute("page", page);
		// 测试
		// for(KeyPerson kp : kpList){
		// System.out.println(kp.getName()+"---"+kp.getFraction());
		// }

		return "community";
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
		return null;
	}
}
