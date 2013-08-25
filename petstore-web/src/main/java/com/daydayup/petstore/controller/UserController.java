package com.daydayup.petstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daydayup.petstore.entity.User;
import com.daydayup.petstore.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService service;

	private String value;

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<User> list() {
		return service.findList();
	}
	
	@RequestMapping("/listx")
	public List<User> listx(@RequestParam("x") String x) {
		return service.findListX(x);
	}

	@RequestMapping("/item/{name}")
	public User findUserByName(@RequestParam("name") String name) {
		return service.findUserByName(name);
	}

	@RequestMapping("/add")
	public void addUer(User user) {
		System.out.println();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
