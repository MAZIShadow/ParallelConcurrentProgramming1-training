package org.home.mazi.parallelconcurrentprogramming1.chapter4.deadlock;

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

			if (sushiCount > 0) {
				sushiCount--;
				System.out.println(this.getName() + " took a piece! Sushi remaining:" + sushiCount);
			}

			secondChopstick.unlock();
			firstChopstick.unlock();
		}
	}
}

public class DeadLockDemo {
	public static void main(String[] args) {
		Lock chopstickA = new ReentrantLock();
		Lock chopstickB = new ReentrantLock();
		Lock chopstickC = new ReentrantLock();
		new Philosopher("Barron", chopstickA, chopstickB).start();
		new Philosopher("Olivia", chopstickB, chopstickC).start();
		//new Philosopher("Steve", chopstickC, chopstickA).start();
		new Philosopher("Steve", chopstickA, chopstickC).start();

	}
}
