package org.home.mazi.parallelconcurrentprogramming1.chapter1;

class VegetableChopper extends Thread {
    public int vegetable_count = 0;
    public static boolean chopping = true;

    public VegetableChopper(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (chopping) {
            System.out.println(this.getName() + " chopped a vegetable");
            vegetable_count++;
        }
    }
}


public class ExecutionSchedulingDemo {
    public static void main(String[] args) throws InterruptedException {
        VegetableChopper barron  = new VegetableChopper("Barron");
        VegetableChopper olivia  = new VegetableChopper("Olivia");

        barron.start();
        olivia.start();
        Thread.sleep(1_000);

        VegetableChopper.chopping = false;

        barron.join();
        olivia.join();

        System.out.format("Barron chopped %d vegetables.\n", barron.vegetable_count);
        System.out.format("Olivia chopped %d vegetables.\n", olivia.vegetable_count);
    }
}

