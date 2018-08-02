package com.lxg.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxg.springboot.entity.User;
import com.lxg.springboot.service.UserService;
/**
 * 
 * @author DHUser
 * 无@PreAuthorize的方法
 * 方法均需要合法的access_token
 * 
 * 有@PreAuthorize的方法
 * 需要进一步研究如何根据角色控制访问权限
 * 
 * 答案：check_token时，会将authories属性返回，
 * 如果其中包含了指定的角色，那么带上合法的token即可正常访问。
 */
@Controller
@RequestMapping("api")
public class ApiController {
    @Autowired
    UserService userService;  
    //当前安全框架上下文校验
    @PreAuthorize("hasAnyRole('ROLE_AUTH_TRUSTED_CLIENT','ROLE_DB_TRUSTED_CLIENT')")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public List<User> trustedClent() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    @PreAuthorize("hasAnyRole('ROLE_AUTH_TRUSTED_CLIENT','ROLE_DB_TRUSTED_CLIENT')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public List<User> client() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    
    @PreAuthorize("hasAnyRole('ROLE_AUTH_TRUSTED_CLIENT','ROLE_DB_TRUSTED_CLIENT')")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public List<User> trustedClent1() {
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
    
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public List<User> client1() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    
    //资源服务器保护 有合法的token即可访问
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public List<User> other() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
    
    //资源服务器不保护 有合法的token即可访问
    @RequestMapping(value = "/no", method = RequestMethod.GET)
    @ResponseBody
    public List<User> no() {
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return null;
        }
        return users;
    }
  
}