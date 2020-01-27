package com.lyx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/testController")
@RestController
public class TestController
{
	@GetMapping("/resourcesA")
	public String resourcesA()
	{
		return "这是资源A，需要登录才能访问到";
	}

	@GetMapping("/resourcesB")
	public String resourcesB()
	{
		return "这是资源A，需要[admin]角色才可以访问到";
	}

	@GetMapping("/resourcesC")
	public String resourcesC()
	{
		return "这是资源A，需要[admin]与[common]角色才可以访问到";
	}
}