package org.home.mazi.parallelconcurrentprogramming1.chapter4.abandonedLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {
	private Lock firstChopstick, secondChopstick;
	private static int sushiCount = 500_000;

	public Philosopher(String threadName, Lock firstChopstick, Lock secondChopstick) {
		super(threadName);
		this.firstChopstick = firstChopstick;
		this.secondChopstick = secondChopstick;
	}

	@Override
	public void run() {
		while (sushiCount > 0) {
			firstChopstick.lock();
			secondChopstick.lock();

			try {
				if (sushiCount > 0) {
					sushiCount--;
					System.out.println(this.getName() + " took a piece! Sushi remaining:" + sushiCount);
				}

				if (sushiCount == 10) {
					System.out.println(1 / 0);
				}
			}
			finally {
				secondChopstick.unlock();
				firstChopstick.unlock();
			}
		}
	}
}

public class AbandonedLock {
	public static void main(String[] args) {
		Lock chopstickA = new ReentrantLock();
		Lock chopstickB = new ReentrantLock();
		Lock chopstickC = new ReentrantLock();
		new org.home.mazi.parallelconcurrentprogramming1.chapter4.abandonedLock.Philosopher("Barron", chopstickA, chopstickB).start();
		new org.home.mazi.parallelconcurrentprogramming1.chapter4.abandonedLock.Philosopher("Olivia", chopstickB, chopstickC).start();
		//new Philosopher("Steve", chopstickC, chopstickA).start();
		new org.home.mazi.parallelconcurrentprogramming1.chapter4.abandonedLock.Philosopher("Steve", chopstickA, chopstickC).start();

	}
}
