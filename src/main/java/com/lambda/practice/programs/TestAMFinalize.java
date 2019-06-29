package com.lambda.practice.programs;

public class TestAMFinalize {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			foo();
		}catch(RuntimeException e) {
			System.out.println("RuntimeException...");
		}catch(Exception e) {
			System.out.println("Exception...");
		}
		
	}
	
	static void foo() throws Exception {
		try {
			foo1();
		}finally{
			System.out.println("fianally...");
			foo2();
		}
	}
	
	static void foo1() {
		throw new RuntimeException();
	}
	
	static void foo2() throws Exception {
		throw new Exception();
	}

}
