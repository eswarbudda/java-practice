package com.lambda.practice.threads;

public class OddNum implements Runnable {
	
	private Print print;
	private Integer range;
	public OddNum(Print print, int range) {
		this.print = print;
		this.range = range;
	}
	
	public void run() {
		for(int i=1; i<range; i+=2) {
			print.printOdd(i);
		}
	}
	

}
