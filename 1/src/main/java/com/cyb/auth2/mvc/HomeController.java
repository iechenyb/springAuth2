package com.cyb.auth2.mvc;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		return "indexDefault";
	}
	@RequestMapping("/self")
	public String self(){
		return "index";
	}
	/*@Autowired
	private AuthenticationManager oauth2AuthenticationManager;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPage(
			@RequestParam(value = "error", required = false) String error,
			String username, String password, Model model,
			HttpServletRequest request) {
		Authentication authentication = oauth2AuthenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username,
						password));
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		System.out.println("登录成功！");
		return "index";
	}
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String exit(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		Authentication auth = securityContextImpl.getAuthentication();
		if (auth != null) {
			System.out.println("exit infor:" + auth.getName());
			new SecurityContextLogoutHandler().logout(request, response, auth);
		} else {
			System.out.println("无授权信息！");
		}
		try {
			// 获得当前用户所拥有的权限
			@SuppressWarnings("unchecked")
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl
					.getAuthentication().getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				System.out.println("Authority"
						+ grantedAuthority.getAuthority());
			}
			WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl
					.getAuthentication().getDetails();
			// 获得访问地址
			System.out.println("RemoteAddress" + details.getRemoteAddress());
			// 获得sessionid
			System.out.println("SessionId" + details.getSessionId());
			// 登录密码，未加密的
			System.out.println("Credentials:"
					+ securityContextImpl.getAuthentication().getCredentials());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "login";
	}*/
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
