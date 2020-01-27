package com.lyx.shiro;

import com.lyx.entiey.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.Collection;
import java.util.HashSet;

public class EmployeeRealm extends AuthorizingRealm
{
	/**
	 * 执行授权逻辑
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
	{
		Subject subject = SecurityUtils.getSubject();
		Employee user = (Employee) subject.getPrincipal(); //获得当前登录的用户

		// 获得当前登录用户的角色并处理成一个集合
		Collection<String> roles = new HashSet<>();
		roles.add("admin");

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(); //创建一个这样的对象，它是返回的类型的子类
		info.addRoles(roles); //为当前用户关联角色

		return info;
	}

	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
	{
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; //获得用户输入的用户名,这个对象就是login()传递过来的，将它强转以取出封装的用户名
		String userNameInput = token.getUsername();

		if(!"123".equals(userNameInput)) // 员工id不存在
		{

			return null;
		}

		// 下边就是用户存在的情况，只需要判断密码对不对
		// 判断密码对不对只需要返回这样一个对象，第二个参数是数据库中正确的密码
		return new SimpleAuthenticationInfo(new Employee("123","123"), "123", "");
	}
}