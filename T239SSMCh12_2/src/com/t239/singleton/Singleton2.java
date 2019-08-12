package com.t239.singleton;
/**
 * 饿汉模式
 */
public class Singleton2 {
	private Singleton2(){}
	private static Singleton2 singleton=new Singleton2();
	
	//获取当前类的实例
	public static Singleton2 getInstance(){
		return singleton;
	}
}
