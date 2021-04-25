package org.home.mazi.parallelconcurrentprogramming1.chapter4.starvation;

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
		int sushiEaten = 0;

		while (sushiCount > 0) {
			firstChopstick.lock();
			secondChopstick.lock();

			try {
				if (sushiCount > 0) {
					sushiCount--;
					sushiEaten++;
					System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finally {
				secondChopstick.unlock();
				firstChopstick.unlock();
			}
		}

		System.out.println(this.getName() + " took " + sushiEaten);
	}
}

public class StarvationDemo {
	public static void main(String[] args) {
		Lock chopstickA = new ReentrantLock();
		Lock chopstickB = new ReentrantLock();
		Lock chopstickC = new ReentrantLock();
//		new Philosopher("Barron", chopstickA, chopstickB).start();
//		new Philosopher("Olivia", chopstickB, chopstickC).start();
//		new Philosopher("Steve", chopstickA, chopstickC).start();
		new Philosopher("Barron", chopstickA, chopstickB).start();
		new Philosopher("Olivia", chopstickA, chopstickB).start();
		new Philosopher("Steve", chopstickA, chopstickB).start();

	}
}

