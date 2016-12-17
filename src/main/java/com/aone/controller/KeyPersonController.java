package com.aone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aone.entity.KeyPerson;
import com.aone.service.KeyPersonService;


@Controller
public class KeyPersonController {
	
	@Autowired
	@Qualifier(value="keyPersonService")
	KeyPersonService keyPersonService;
	
	
	/**
	 * @author Joe
     * @function 根据id查询重点人员的所有信息
	 * @param id
	 * @return 
	 */
	@RequestMapping(value = {"/searchById"})
	public void searchById(@RequestParam int id){
		
		KeyPerson kp = keyPersonService.searchById(id);
//		System.out.println(kp.getName());
//		System.out.println(kp.getPhoto());
		
	}
	
	/**
	 * @author Joe
     * @function 根据id属性分页显示重点人员的信息
	 * @param page
	 */
	@RequestMapping(value = {"/showPersonByPage"})
	public void showPersonByPage(@RequestParam(defaultValue="0") int page){
		int pagesize = 10; //每页显示数量
		String propertyName = "keyPersonID";//列表排序依据的属性
		Integer startRow = page*pagesize; //起始记录
		boolean desc = false; //是否采用降序排序
		List<KeyPerson> kpList = keyPersonService.showPersonByPage(propertyName, desc, startRow, pagesize);
		//测试
//		for(KeyPerson kp : kpList){
//			System.out.println(kp.getId());
//		}
	}

}
