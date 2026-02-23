package com.tss.SOLID.ISP.test;

import com.tss.SOLID.ISP.model.Human;
import com.tss.SOLID.ISP.model.Robot;

public class WorkerMain {
    public static void main(String[] args) {
        System.out.println();

//        Human human1 = new Human("Vrunda");

        Human human1 = new Human();
        human1.doWork();
        human1.eat();
        human1.rest();

        System.out.println();

        Robot robot1 = new Robot();
        robot1.doWork();
        robot1.charge();

    }
}
