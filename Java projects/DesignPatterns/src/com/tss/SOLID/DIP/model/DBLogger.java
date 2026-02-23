package com.tss.SOLID.DIP.model;

import com.tss.SOLID.DIP.exception.LoggerFailedException;

import java.util.ArrayList;
import java.util.List;

public class DBLogger implements Logger{
    private static final int MAX_LOGS_LIMIT = 3;

    List<String> DBLogs = new ArrayList<>();
    @Override
    public void addLog(String log) throws LoggerFailedException {

        if (DBLogs.size() >= MAX_LOGS_LIMIT) {
            throw new LoggerFailedException("DB logger limit reached...Max Limit : " + MAX_LOGS_LIMIT  + "\nSwitching to File Logger...");
        }
//        if (log.contains("FAIL")) {
//            throw new DBFailedException("Database connection failed while logging: " + log);
//        }
        DBLogs.add("DB Log : " + log);
    }

    @Override
    public void displayLogs() {
        System.out.println("-----*** DB Logs ***-----");
        for (String log : DBLogs) {
            System.out.println(log);
        }
        System.out.println("------------------------------------");
    }
}
