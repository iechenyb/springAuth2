package com.cyb.auth2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by user on 2016/3/24.
 */
public class UserAuthorService implements UserDetailsService {
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	System.out.println("xxxx");
        return null;
    }
}
