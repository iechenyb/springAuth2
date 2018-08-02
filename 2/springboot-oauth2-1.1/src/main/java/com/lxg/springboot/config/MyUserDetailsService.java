package com.lxg.springboot.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lxg.springboot.entity.Role;
import com.lxg.springboot.entity.User;
import com.lxg.springboot.service.UserRoleService;
import com.lxg.springboot.service.UserService;

/**
 * Created by lxg
 * on 2017/2/20.
 */
public class MyUserDetailsService implements UserDetailsService {
	Log log = LogFactory.getLog(MyUserDetailsService.class);
    @Autowired
    private UserService userService;

    @SuppressWarnings("unused")
	@Autowired
    private UserRoleService userRoleService;
    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if(user == null){
             throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        Iterator<Role> iterator =  user.getList().iterator();
        StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()){
        	Role role = iterator.next();
            collection.add(new SimpleGrantedAuthority(role.getRole_name()));
            sb.append(role.getRole_name()+"\t");
        }
        log.info(user.getUsername()+","+sb.toString());
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),collection);
    }
    
}
