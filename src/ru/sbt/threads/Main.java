package ru.sbt.threads;

public class Main {
    public static void main(String[] args) {
        Thread main = Thread.currentThread();

        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                Thread.currentThread().setName(String.valueOf(System.currentTimeMillis()));
                System.out.println(Thread.currentThread().getName());
            }
        });
        t1.start();
        t1.setDaemon(true);

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            main.interrupt();
            System.out.println("T2");
        });
        t2.start();


        while (!Thread.currentThread().isInterrupted()) {
        }

        try {
           t1.join();
        } catch (InterruptedException e) {
            System.out.println("Interrapted");
        }

        System.out.println("Main");
    }
}
