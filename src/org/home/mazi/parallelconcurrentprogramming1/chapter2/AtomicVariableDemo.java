package org.home.mazi.parallelconcurrentprogramming1.chapter2;

import java.util.concurrent.atomic.*;

public class AtomicVariableDemo {

	private static class Shopper extends Thread {

		static AtomicInteger garlicCount = new AtomicInteger(0);
		@Override
		public void run() {
			for (int i = 0; i < 10_000_000; i++) {
				garlicCount.incrementAndGet();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		AtomicVariableDemo.Shopper barron = new Shopper();
		AtomicVariableDemo.Shopper olivia = new Shopper();
		barron.start();
		olivia.start();
		barron.join();
		olivia.join();
		System.out.println("We should buy " + AtomicVariableDemo.Shopper.garlicCount + " garlic.");
	}
}
