package com.lambda.practice.forkjoinpool;

import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class SumOfNNumbers extends RecursiveTask<Long> {

	private final Integer THRESHOLD;

	private int start;
	private int end;
	private int[] arr;

	public SumOfNNumbers(int[] arr, int start, int end, int threshold) {
		this.start = start;
		this.end = end;
		this.THRESHOLD = threshold;
		this.arr = arr;
	}

	@Override
	protected Long compute() {
		if( (end-start) > THRESHOLD) {
			int mid =(start + end)/2;
			SumOfNNumbers task1 = new SumOfNNumbers(arr, start, mid, THRESHOLD);
			SumOfNNumbers task2 = new SumOfNNumbers(arr, mid, end, THRESHOLD);
			task1.fork();
			task2.fork();
			return task1.join() + task2.join();
		}else {
			//System.out.println("Thread name: " + Thread.currentThread() + " number:" + end);
			/*long sum = 0;
			for(; start < end; start++) {
				sum += arr[start];
			}
			return sum; */
			return LongStream.range(start, end).sum();
		}
	}
}
