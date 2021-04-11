package org.home.mazi.parallelconcurrentprogramming1.chapter2;

import java.util.concurrent.locks.*;



public class MutualExclusionDemo {

	private static class Shopper extends Thread {

		static int garlicCount = 0;
		static Lock pencil = new ReentrantLock();

		@Override
		public void run() {
			for (int i = 0; i < 10_000_000; i++) {
				pencil.lock();
				garlicCount++;
				pencil.unlock();

				System.out.println(Thread.currentThread().getName() + " is thinking");

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}


		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread barron = new Shopper();
		Thread olivia = new Shopper();
		barron.start();
		olivia.start();
		barron.join();
		olivia.join();
		System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
	}
}
