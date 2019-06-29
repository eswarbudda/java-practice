package com.lambda.practice.threads;

public class SampleThread extends Thread {

	Thread t;
	String name;
	
	public SampleThread(){
		t = new Thread();
		name = Thread.currentThread().getName();
		//t.start();
		System.out.println("samplethread name:" + name);
	}
	
	@Override
	public void run() {
		System.out.println("thread started... name:" + name);
		System.out.println("thread started... name:" + this.getState());
		//t.start();
	}
	
	
	public class NestedClass {
		public void print() {
			System.out.println("nestedclass... name:"+name);
		}
	}

}
