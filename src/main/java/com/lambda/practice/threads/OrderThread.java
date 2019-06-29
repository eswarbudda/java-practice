package com.lambda.practice.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class OrderThread implements Runnable {

	ExecutorService exec = Executors.newFixedThreadPool(5);
	List<Runnable> list = new ArrayList<>();
	
	private static ReentrantLock rlock = new ReentrantLock();
	private int count;
	private Object lock;
	private AtomicInteger n;
	private int order = 0;
	
	OrderThread(Object obj, AtomicInteger num, int order){
		this.lock = obj;
		this.order = order;
		this.n = num;
	}
	
	
	//working code. uncomment to test
	//@Override
	public void run(int o) {
		//exec.submit(new MyCallable()); // comment it after checking the submit implementation
		synchronized(lock) {
			for(; n.get()<20;) {
				if( (n.get() % 3) == order) {
					System.out.println(Thread.currentThread() + " : " + n.incrementAndGet());
					//n.getAndIncrement();
					lock.notifyAll();
				}else {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		exec.submit(list.get(0));
	}
	
	@Override
	public void run() {
		//exec.submit(new MyCallable()); // comment it after checking the submit implementation
		for(; n.get()<20;) {
			rlock.lock();
			System.out.println(Thread.currentThread()+" - trying to print: " + (++count));
			if( (n.get() % 3) == order) {
				System.out.println(Thread.currentThread() + ": " + n.incrementAndGet());
			}
			rlock.unlock();
		}
	}

}
