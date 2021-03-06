package org.home.mazi.parallelconcurrentprogramming1.chapter2;


public class DataRaceDemo {

	private static class Shopper extends Thread {

		static int garlicCount = 0;

		@Override
		public void run() {
			//for (int i = 0; i < 10; i++) { <-- hard to notice
			for (int i = 0; i < 10_000_000; i++) {
				garlicCount++;
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
