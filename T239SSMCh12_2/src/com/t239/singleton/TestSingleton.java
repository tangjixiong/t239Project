package com.t239.singleton;

public class TestSingleton {
	public static void main(String[] args) {
		/*Singleton s1=new Singleton();
		Singleton s2=new Singleton();
		Singleton s3=new Singleton();
		Singleton s4=new Singleton();
		Singleton s5=new Singleton();*/
		Singleton2 s1=Singleton2.getInstance();
		Singleton2 s2=Singleton2.getInstance();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1==s2);
	}

}
