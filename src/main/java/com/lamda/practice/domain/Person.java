package com.lamda.practice.domain;

import com.lambda.practice.Gender;

public class Person {
	
	private String firstname;
	private String lastname;
	private Gender gender;
	private Integer age;
	private Integer salary;

	
	public Person(String firstname, String lastname, Gender gender, int age, int sal) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.age = age;
		this.salary = sal;
	}
}
