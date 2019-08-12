package com.t239.singleton;
/**
 * 静态内部类
 */
public class Singleton3 {
	private Singleton3(){}
	private static Singleton3 singleton;
	
	//获取当前类的实例
	public static Singleton3 getInstance(){
		singleton=SingletonHelper.INSTANCE;
		return singleton;
	}
	
	//内部类
	public static class SingletonHelper{
		private static final Singleton3 INSTANCE=new Singleton3();
	}
}
