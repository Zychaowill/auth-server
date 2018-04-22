package com.oauth.authserver.controller.publicapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oauth.authserver.bo.Msg;

@Controller
public class LoginApi {
	
	@RequestMapping(value = "/home")
	public String index(Model model) {
		Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
		model.addAttribute("msg", msg);
		return "home";
	}
}
