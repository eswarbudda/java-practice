package com.lambda.practice.threads;

import java.util.concurrent.ConcurrentHashMap;

public class EvenOddNum implements Runnable {
	private int range;
	private boolean isOdd;
	private Print print;
	
	public EvenOddNum(Print print, int range, boolean evenOdd) {
		this.print = print;
		this.range = range;
		isOdd = evenOdd;
	}
	
	public void run() {
		int num = isOdd ? 1 : 2;
		while( num <= range ) {
			if( isOdd ) {
				print.printOdd(num);
			}else {
				print.printEven(num);
			}
			num += 2;
		}
	}

}
