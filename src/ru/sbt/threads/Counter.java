package ru.sbt.threads;

public class Counter {
    static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int j = 0; j < 10_000; j++) {
                inc();
            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();

        Thread t2 = new Thread(runnable);
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }

    private static void inc() {
        synchronized (Counter.class) {
            counter = counter + 1;
        }
    }

    private void inc2() {
        synchronized (this) {
            counter = counter + 1;
        }
    }

    private void inc3() {
        synchronized (this) {
            counter = counter + 1;
        }
    }
}
