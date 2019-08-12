package com.t239.singleton;
/**
 * 懒汉模式
 */
public class Singleton {
	private Singleton(){}
	private static Singleton singleton;
	
	//获取当前类的实例
	public static Singleton getInstance(){
		if(singleton==null){
			singleton=new Singleton();
		}
		return singleton;
	}
}
