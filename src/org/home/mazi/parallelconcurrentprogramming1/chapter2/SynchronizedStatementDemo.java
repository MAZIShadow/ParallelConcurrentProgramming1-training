package org.home.mazi.parallelconcurrentprogramming1.chapter2;

public class SynchronizedStatementDemo {

	private static class Shopper extends Thread {

		static int garlicCount = 0;

		@Override
		public void run() {
			for (int i = 0; i < 10_000_000; i++) {
				synchronized (Shopper.class) {
					garlicCount++;
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Shopper barron = new Shopper();
		Shopper olivia = new Shopper();
		barron.start();
		olivia.start();
		barron.join();
		olivia.join();
		System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
	}
}
