package org.home.mazi.parallelconcurrentprogramming1.chapter3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {


	public static void main(String[] args) throws InterruptedException {
		Thread barron  = new Shopper();
		Thread olivia  = new Shopper();
		barron.start();
		olivia.start();
		barron.join();
		olivia.join();
		System.out.println("We should buy " + Shopper.garlicCount + " garlic");
		System.out.println("We should buy " + Shopper.potatoCount + " potatoes");
	}
}

class Shopper extends Thread {
	static int garlicCount, potatoCount = 0;
	//static Lock pencil = new ReentrantLock();
	static ReentrantLock pencil = new ReentrantLock();

	private void addGarlic() {
		pencil.lock();
		System.out.println("Holds count " + pencil.getHoldCount());
		garlicCount++;
		pencil.unlock();
	}

	private void addPotato() {
		pencil.lock();
		potatoCount++;
		addGarlic();
		pencil.unlock();
	}

	@Override
	public void run() {
		for (int i = 0; i < 10_000; i++) {
			addGarlic();
			addPotato();
			
		}
	}
}
