package com.daydayup.petstore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daydayup.petstore.entity.User;
import com.daydayup.petstore.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<User> list() {
		return service.findList();
	}
}
