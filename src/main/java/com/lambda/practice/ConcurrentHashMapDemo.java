package com.lambda.practice;

//Java program to demonstrate working of ConcurrentHashMap 
import java.util.concurrent.ConcurrentHashMap; 

class ConcurrentHashMapDemo { 
	public static void main(String[] args) 
	{ 
		ConcurrentHashMap m = new ConcurrentHashMap(10, 0.75f, 16); 
		m.put(100, "Hello"); 
		m.put(101, "Geeks"); 
		m.put(102, "Geeks"); 

		// Here we cant add Hello because 101 key 
		// is already present in ConcurrentHashMap object 
		m.put(101, "world"); 

		// We can remove entry because 101 key 
		// is associated with For value 
		m.remove(101);//, "world"); 

		// Now we can add Hello 
		m.putIfAbsent(103, "Hello"); 

		// We cant replace Hello with For 
		m.replace(101, "Hello", "For"); 
		System.out.println(m); 
	} 
} 

