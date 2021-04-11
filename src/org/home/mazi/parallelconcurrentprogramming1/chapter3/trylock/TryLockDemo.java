package org.home.mazi.parallelconcurrentprogramming1.chapter3.trylock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Shopper extends Thread {
	private int itemsToAdd = 0;
	private static int itemsOnNotepad = 0;
	private static Lock pencil = new ReentrantLock();


	public Shopper(String threadName) {
		super(threadName);
	}

	@Override
	public void run() {
		while (itemsOnNotepad <= 20) {
			if (itemsToAdd > 0 && pencil.tryLock()) {
				try {
					//pencil.lock();

					itemsOnNotepad += itemsToAdd;
					System.out.println(this.getName() + " added " + itemsToAdd + " item(s) to notepad");
					itemsToAdd = 0;
					Thread.sleep(300);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					pencil.unlock();
				}
			} else {
				try {
					Thread.sleep(100);
					itemsToAdd++;
					System.out.println(this.getName() + " found something to but");
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}
}

public class TryLockDemo {
	public static void main(String[] args) throws InterruptedException {
		Thread barron = new Shopper("Barron");
		Thread olivia = new Shopper("Olivia");
		long start = System.currentTimeMillis();
		barron.start();
		olivia.start();
		barron.join();
		olivia.join();
		long finish = System.currentTimeMillis();
		System.out.println("Elapsed Time: " + (float)(finish - start)/1_000 + " seconds");
	}
}
