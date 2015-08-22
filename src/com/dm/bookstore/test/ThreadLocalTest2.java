package com.dm.bookstore.test;

public class ThreadLocalTest2 implements Runnable {

	int i = 0;

	ThreadLocal<String> threadLocal = new ThreadLocal<>();
	@Override
	public void run() {
		for (; i < 10; i++) {
			threadLocal.set(Thread.currentThread().getName());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "::" + threadLocal.get());

		}
		System.out.println(this);
	}

	public static void main(String[] args) {
		ThreadLocalTest2 tlt = new ThreadLocalTest2();

		new Thread(tlt, "AAA").start();
		new Thread(tlt, "BBB").start();
		
	}
}
