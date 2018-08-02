package com.lxg.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import com.lxg.springboot.entity.User;
import com.lxg.springboot.service.UserService;
/**
 * 
 * @author DHUser
 * 当前类下所有的方法均和access_token无关
 */
@Controller
//@RequestMapping("/server")
public class ServerController {
	@Autowired
	UserService userService;  
    //当前安全框架上下文校验
    @PreAuthorize("hasRole('ROLE_DB_TRUSTED_CLIENT')")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public List<User> trustedClent() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    @PreAuthorize("hasRole('ROLE_DB_TRUSTED_CLIENT')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public List<User> client() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    
    @PreAuthorize("hasRole('ROLE_DB_TRUSTED_CLIENT')")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public List<User> trustedClent1() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List<User> client1() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    
    //一个没有的角色测试
    @PreAuthorize("hasRole('ROLE_CLIENT_ADMIN')")
    @RequestMapping(value = "/clientAdmin", method = RequestMethod.GET)
    @ResponseBody
    public List<User> clientAdmin() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    
    //资源服务器保护
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<User> other() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    //资源服务器不保护
    @RequestMapping(value = "/no", method = RequestMethod.GET)
    @ResponseBody
    public List<User> no() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public ModelAndView login(User user) {
    	 Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
         System.out.println("before:" + request);
         Authentication result = authenticationManager.authenticate(request);
         System.out.println("after:" + result);
         SecurityContextHolder.getContext().setAuthentication(result);
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        ModelAndView a = new ModelAndView();
        a.setViewName("/oauth/confirm_access");
        return a;
    }
  
  
    
  
}