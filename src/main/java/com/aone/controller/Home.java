package com.aone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aone.entity.Test;
import com.aone.service.TestService;

@Controller
public class Home {
	@Autowired
	@Qualifier(value="testService")
	TestService ts;
	
	@RequestMapping(value = { "/index", "", "/" }, method = RequestMethod.GET)
	public void home(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("hello");
	    response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String show(Model model){
		model.addAttribute("an", ts.getAnById(1));
		System.out.println("hello, show");
		List<Test> list = ts.findAll();
		if(! list.isEmpty())
			System.out.println(list.get(0).getId());
		return "show";
	}
}
