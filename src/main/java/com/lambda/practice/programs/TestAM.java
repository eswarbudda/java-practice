package com.lambda.practice.programs;

public class TestAM {

	public static void main(String[] args) {
		
		
		MyThread t = new MyThread(new NewRunnable());
		t.start();
	}

}

class MyThread extends Thread {
	MyThread() {
		
	}
	
	MyThread(Runnable r){
		super(r);
		System.out.println("Mythread(string)..");
	}
	
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("MyThread...");
	}
}

class NewRunnable implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("NewRunnable...");
	}
	
}