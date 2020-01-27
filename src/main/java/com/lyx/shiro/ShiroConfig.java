package com.lyx.shiro;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig
{
	/**
	 * 这个方法关联一个安全管理器
	 */
	@Bean("shiroFilterFactoryBean")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager)
	{
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager); //关联安全管理器

		Map<String, String> map = new LinkedHashMap<>();

		// 登录后才可以访问
		map.put("/testController/resourcesA", "authc");

		// 有角色才可以访问
		map.put("/testController/resourcesB", "roles[admin]");
		map.put("/testController/resourcesC", "roles[admin,common]");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		shiroFilterFactoryBean.setLoginUrl("/noLogin"); // 设置未登录却访问了需要登录的资源时发送的URL
		shiroFilterFactoryBean.setUnauthorizedUrl("/noPre"); // 设置获得授权失败时发送的请求

		return  shiroFilterFactoryBean;
	}

	/**
	 * 获得一个安全管理器
	 * 这个方法关联一个realm类
	 */
	@Bean(name = "defaultWebSecurityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("employeeRealm") EmployeeRealm employeeRealm)
	{
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(employeeRealm); //设置realm

		return manager;
	}

	/**
	 * 获得一个realm类
	 */
	@Bean(name = "employeeRealm")
	public EmployeeRealm getRealm()
	{
		return new EmployeeRealm();
	}
}