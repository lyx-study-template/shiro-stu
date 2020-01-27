package com.lyx.controller;

import com.lyx.entiey.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController
{
	/**
	 * 用户名和密码都是：123
	 */
	@PostMapping("/login")
	public String login(Employee employee)
	{
		Subject subject = SecurityUtils.getSubject(); //获得Subject对象

		UsernamePasswordToken token = new UsernamePasswordToken(employee.getEmployeeId(), employee.getPassWord()); //将用户输入的用户名与密码封装到一个UsernamePasswordToken对象中

		//用Subject对象执行登录方法，没有抛出任何异常说明登录成功
		try
		{
			subject.login(token); //login()方法会调用 Realm类中执行认证逻辑的方法，并将这个参数传递给doGetAuthenticationInfo()方法
			return "登录成功";
		}
		catch (UnknownAccountException e) //抛出这个异常说明用户不存在
		{
			return "用户不存在";
		}
		catch (IncorrectCredentialsException e) //抛出这个异常说明密码错误
		{
			return "密码错误";
		}
	}

	@GetMapping("/noLogin")
	public String noLogin()
	{
		return "你还没有登录";
	}

	@GetMapping("/noPre")
	public String noPre()
	{
		return "你没有权限访问这里";
	}
}