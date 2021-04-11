package org.home.mazi.parallelconcurrentprogramming1.chapter2;

public class SynchronizedMethodDemo {
	private static class Shopper extends Thread {

		static int garlicCount = 0;

		private static synchronized void addGarlic()
		{
			garlicCount++;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10_000_000; i++) {
				addGarlic();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		SynchronizedMethodDemo.Shopper barron = new SynchronizedMethodDemo.Shopper();
		SynchronizedMethodDemo.Shopper olivia = new SynchronizedMethodDemo.Shopper();
		barron.start();
		olivia.start();
		barron.join();
		olivia.join();
		System.out.println("We should buy " + SynchronizedMethodDemo.Shopper.garlicCount + " garlic.");
	}
}
