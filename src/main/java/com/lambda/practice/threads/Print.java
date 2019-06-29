package com.lambda.practice.threads;

public class Print {
	private static boolean isOdd;
	synchronized void printEven(int number) {
		if( !isOdd ) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("is not odd - caught exception");
			}
		}
		System.out.println(Thread.currentThread()+ " number: " + number);
		isOdd = false;
		notify();
	}
	
	synchronized void printOdd(int number) {
		if( isOdd ) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("is not odd - caught exception");
			}
		}
		System.out.println(Thread.currentThread()+ " number: " + number);
		isOdd = true;
		notify();
	}

}
