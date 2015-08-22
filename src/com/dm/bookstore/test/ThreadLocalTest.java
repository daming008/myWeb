package com.dm.bookstore.test;

public class ThreadLocalTest implements Runnable {

	String name = null;
	int i = 0;
	
	@Override
	public void run() {
		for(;i<10;i++){
			synchronized (this) {
				name = Thread.currentThread().getName();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"::"+name);
			}
			
		}
		System.out.println(this);
	}
	
	public static void main(String[] args) {
		ThreadLocalTest tlt = new ThreadLocalTest();
		
		new Thread(tlt, "AAA").start();
		new Thread(tlt, "BBB").start();
		
	}

}
