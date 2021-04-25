package org.home.mazi.parallelconcurrentprogramming1.chapter4.livelock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {
	private Lock firstChopstick, secondChopstick;
	private static int sushiCount = 500_000;
	private Random rps = new Random();

	public Philosopher(String threadName, Lock firstChopstick, Lock secondChopstick) {
		super(threadName);
		this.firstChopstick = firstChopstick;
		this.secondChopstick = secondChopstick;
	}

	@Override
	public void run() {
		while (sushiCount > 0) {
			firstChopstick.lock();
			if (!secondChopstick.tryLock()) {
				System.out.println(getName() + " releasing their first chopstick");
				firstChopstick.unlock();
				try {
					TimeUnit.MILLISECONDS.sleep(rps.nextInt(3));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {

				try {
					if (sushiCount > 0) {
						sushiCount--;
						System.out.println(this.getName() + " took a piece! Sushi remaining: " + sushiCount);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					secondChopstick.unlock();
					firstChopstick.unlock();
				}


			}
		}
	}
}

public class LiveLockDemo {
	public static void main(String[] args) {
		Lock chopstickA = new ReentrantLock();
		Lock chopstickB = new ReentrantLock();
		Lock chopstickC = new ReentrantLock();
		new Philosopher("Barron", chopstickA, chopstickB).start();
		new Philosopher("Olivia", chopstickB, chopstickC).start();
		new Philosopher("Steve", chopstickC, chopstickA).start();
		//new Philosopher("Steve", chopstickA, chopstickC).start();

	}
}
