package com.lambda.practice.threads;

import java.util.concurrent.RecursiveTask;

public class EvenOdd extends RecursiveTask<Integer> {
	
	private int range;
	private boolean isOdd;
	private Print print;
	
	public EvenOdd(Print print, int range, boolean evenOdd) {
		this.print = print;
		this.range = range;
		isOdd = evenOdd;
	}
	
	@Override
	public Integer compute() {
		System.out.println("EvenOdd - Thread name: " + Thread.currentThread());
	int num = isOdd ? 1 : 2;
		while( num <= range ) {
			if( isOdd ) {
				print.printOdd(num);
			}else {
				print.printEven(num);
			}
			num += 2;
		}
		return num;
	}


}
