package com.lxg.springboot.security.oauth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.header.HeaderWriter;

/**
 * 资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*
		 * http.csrf().disable(). anonymous().disable()
		 * .requestMatchers().anyRequest()
		 * .antMatchers("/user/").and().authorizeRequests().and()
		 * .exceptionHandling() .accessDeniedHandler(new
		 * OAuth2AccessDeniedHandler()).accessDeniedPage("/refuse");
		 */
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().requestMatchers()
		.antMatchers("/api/**")//受保护的连接 不指定角色   java客户端直接访问，不用跳转页面
		.antMatchers("cart/**")
		.antMatchers("order/**")
		.and().authorizeRequests() //需要授权
		.antMatchers("/server/**")//不受保护的资源 不限制角色
		.authenticated();//可以直接访问
		 /*http
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.NEVER)
         .and()
         .requestMatchers().antMatchers("/api/**")
         .and().authorizeRequests()
         .antMatchers(HttpMethod.GET, "/server/**").access("#oauth2.hasScope('read')")
         .antMatchers(HttpMethod.POST, "/user/**").access("#oauth2.hasScope('write')")
         .antMatchers(HttpMethod.PATCH, "/user/**").access("#oauth2.hasScope('write')")
         .antMatchers(HttpMethod.PUT, "/user/**").access("#oauth2.hasScope('write')")
         .antMatchers(HttpMethod.DELETE, "/user/**").access("#oauth2.hasScope('write')")
         .and()
         .headers().addHeaderWriter(new HeaderWriter() {
	     @Override
	     public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
	         response.addHeader("Access-Control-Allow-Origin", "*");
	         if (request.getMethod().equals("OPTIONS")) {
	             response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
	             response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
	         }
	     }
	 });*/
		
		
		
	}

}