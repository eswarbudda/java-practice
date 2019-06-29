package com.lambda.practice;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class PracticeCompletableFuture {
	
	public static void main(String[] args) {
		
		CompletableFuture<Void> future = 
				CompletableFuture.supplyAsync(() -> {
					try {
						System.out.println("fruits - Thread name:" + Thread.currentThread().getName());
						return getFruits();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return new String[] {"empty"};
				})
				//.completeExceptionally((e) -> System.out.println("Caught exception:" + e))
		        .thenCombine(CompletableFuture.supplyAsync(() -> {
					try {
						System.out.println("veggies - Thread name:" + Thread.currentThread().getName());
						return getVeggies();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return new String[] {"empty2"};
				}),
		        (fruits, veggies) -> Stream.concat(Arrays.stream(fruits), Arrays.stream(veggies))
		).thenAccept(items -> items.forEach(System.out::println));
		future.join();
		
	}
	
	
	public static String[] getFruits() throws InterruptedException {
	    Thread.sleep(1500); //simulate network latency
	    return new String[]{"apple", "apricot", "banana"};
	}

	public static String[] getVeggies() throws InterruptedException {
	    Thread.sleep(2000); //simulate network latency
	    return new String[]{"broccoli", "brussels sprout"};
	}

}
