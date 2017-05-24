package com.cyb.auth2.mvc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyb.auth2.domain.User;
import com.cyb.auth2.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public String home(){
		return "index";
	}
	
	@RequestMapping("/user")
	@ResponseBody
	public List<String> json(){
		return Arrays.asList("111", "222", "333");
	}
	
	@RequestMapping("/admin")
	@ResponseBody
	public List<String> admin(){
		return Arrays.asList("zhangsan", "lisi", "wangwu");
	}
	@RequestMapping("/other")
	@ResponseBody
	public List<User> other(){
		return userService.getAllUsers();
	}
}
