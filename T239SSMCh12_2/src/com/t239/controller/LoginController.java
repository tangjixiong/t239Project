package com.t239.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.t239.entity.User;
import com.t239.service.UserService;
@Controller
public class LoginController {
	@Resource(name="userService")
	private UserService userService;

	@RequestMapping("/login")
	public String login(String userCode,String userPassword,Model model,HttpSession session,HttpServletRequest request){
		User user=userService.login(userCode, userPassword);
		if(user!=null){
			session.setAttribute("userSession", user);
			return "redirect:/sys/user/frame.html";
		}else{
			/*model.addAttribute("error", "用户名或密码错误");
			return "login";*/
			throw new RuntimeException("用户名或密码错误");
		}
	}
	@RequestMapping("/loginout")
	public String loginOut(HttpSession session){
		session.invalidate();
		return "login";
	}
}
