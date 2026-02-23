package com.tss.SOLID.DIP.model;

import java.util.ArrayList;
import java.util.List;

public class CloudLogger implements Logger{
    List<String> cloudLogs = new ArrayList<>();

    @Override
    public void addLog(String log) {
        cloudLogs.add("Cloud log : " + log);

    }

    @Override
    public void displayLogs() {
        System.out.println("-----*** Cloud Logs ***-----");
        for (String log : cloudLogs) {
            System.out.println(log);
        }
        System.out.println("------------------------------------");
    }
}
