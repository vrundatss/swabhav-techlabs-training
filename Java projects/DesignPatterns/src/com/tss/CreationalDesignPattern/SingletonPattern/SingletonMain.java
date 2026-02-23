package com.tss.CreationalDesignPattern.SingletonPattern;

public class SingletonMain {
    public static void main(String[] args) {

//        DB d1 = DB.InnerDB.getInstance();
//
//        System.out.println(d1);
//
//        DB d2 = DB.InnerDB.getInstance();
//
//        System.out.println(d2);
//
//        System.out.println(d1 == d2);


        Runnable task = () -> {
            DB d1 = DB.InnerDB.getInstance();
            System.out.println(Thread.currentThread().getName() + " -> Hashcode : " + d1.hashCode());
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");
        Thread t4 = new Thread(task, "Thread-4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
