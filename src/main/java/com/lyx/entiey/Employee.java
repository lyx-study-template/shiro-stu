package com.lyx.entiey;

import lombok.Data;

@Data
public class Employee
{
	private String employeeId;
	private String passWord;

	public Employee()
	{
	}

	public Employee(String employeeId, String passWord)
	{
		this.employeeId = employeeId;
		this.passWord = passWord;
	}
}