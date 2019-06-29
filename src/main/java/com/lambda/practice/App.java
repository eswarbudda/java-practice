package com.lambda.practice;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.lambda.practice.forkjoinpool.SumOfNNumbers;
import com.lambda.practice.threads.EvenOdd;
import com.lambda.practice.threads.EvenOddNum;
import com.lambda.practice.threads.Print;
import com.lambda.practice.threads.SampleThread;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//Person p = new Person("eswar", "paidi", Gender.MALE, 35, 30000000);
		//System.out.println(p.toString());
		
		Print print = new Print();
	
		Thread evenThread = new Thread(new EvenOddNum(print, 10, false));
		Thread oddThread = new Thread(new EvenOddNum(print, 10, true));
		
		evenThread.start();
		oddThread.start();
		
		evenThread.join(3000);
		oddThread.join(3000);
		
		System.out.println("Print even/odd using forkjoinpool");
		
		List<EvenOdd> tasks = new ArrayList<>();
		tasks.add(new EvenOdd(print, 10, false));
		tasks.add(new EvenOdd(print, 10, true));
		
		List<Integer> sum = new ArrayList<>();
		tasks.forEach(task -> task.fork());
		tasks.forEach(task -> {
			try {
				sum.add(task.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		System.out.println("Sum of all threads:" + sum.stream().reduce(0, Integer::sum));
		
		int number = Integer.parseInt(System.getenv("NaturalNumber"));
		number = 100000000;
		int[] arr = IntStream.range(0,number).toArray();
		SumOfNNumbers sumOfNNumbers = new SumOfNNumbers(arr, 0, number, 100000);
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(50);
		Instant start = Instant.now();
		long result = forkJoinPool.invoke(sumOfNNumbers);
		Instant end = Instant.now();
		System.out.println("Sum of " + number + " natural numbers:" + result 
				+ " Time elasped:" + Duration.between(end, start));
		
		start = Instant.now();
		long total = LongStream.range(0, number).sum();
		end = Instant.now();
		System.out.println("LongStream Sum of " + number + " numbers :" 
				+ total +" elapsed time:" + Duration.between(end, start));
		
		
		start = Instant.now();
		long lTotal = 0;
		for(int i=0; i<number; i++) {
			lTotal += i;
		}
		end = Instant.now();
		System.out.println("loop Sum of " + number + " numbers :"
				+ lTotal +" elapsed time:" + Duration.between(end, start));
		
		
		SampleThread t = new SampleThread();
		t.start();
		
		SampleThread.NestedClass th = t.new NestedClass();
		th.print();
		//completablefuture testing 
		//CompletableFuture<Integer> cFuture = CompletableFuture.supplyAsync(tasks.get(0).compute());
		
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("completable future thread:" + Thread.currentThread().getName());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "eswar";
		}).thenApplyAsync(name -> {
			System.out.println("completable future then apply thread:" + Thread.currentThread().getName());
			return "hello " + name;
		});
	
		System.out.println(cf.get());
		
		CompletableFuture<String> ap = getCF().thenCompose(fn -> getAP());
		System.out.println(ap.get());
	}
	
	private static CompletableFuture<String> getCF() {
		return CompletableFuture.supplyAsync(() ->  "supplyAsync");
	}
	
	private static CompletableFuture<String> getAP() {
		return CompletableFuture.supplyAsync(() ->  "thenApply");
	}
}
