package com.lambda.practice.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadMain {
	
	public static void main(String[] args) throws InterruptedException {
		/*final Thread t1 = new Thread(new OrderThread()); 
		t1.start();
		t1.join();
		System.out.println("Start new thread..");
		final Thread t2 = new Thread(new OrderThread());
		t2.start();
		t2.join();
		final Thread t3 = new Thread(new OrderThread());
		t3.start();
		t3.join();*/
		Object obj = new Object();
		AtomicInteger num = new AtomicInteger(0);
		Thread t1 = new Thread(new OrderThread(obj, num, 0));
		Thread t2 = new Thread(new OrderThread(obj, num, 1));
		Thread t3 = new Thread(new OrderThread(obj, num, 2));
		//OrderThread or = new OrderThread(3);
		
		t1.start();
		t2.start();
		t3.start();
		t1.join();t2.join(); t3.join();
		Obj a = new Obj();
		a.setA(5);
		a.setB(10);
		System.out.println("a: " + a.getA() + " b:" + a.getB());
		changeObj(a);
		System.out.println("a: " + a.getA() + " b:" + a.getB());
	}
	
	public static void changeObj(Obj o) {
		int t = o.getA();
		o.setA(o.getB());;
		o.setB(t);
	}
	
	public static class Obj {
		private int a;
		private int b;
		
		public int getA() { return a; }
		public int getB() { return b; }
		
		public void setA(int a) {
			this.a = a;
		}
		
		public void setB(int b) {
			this.b = b;
		}
	}

}
