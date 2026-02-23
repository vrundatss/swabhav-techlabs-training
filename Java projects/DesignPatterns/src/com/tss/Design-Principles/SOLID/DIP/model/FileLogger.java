package com.tss.SOLID.DIP.model;

import com.tss.SOLID.DIP.exception.LoggerFailedException;

import java.util.ArrayList;
import java.util.List;

public class FileLogger implements Logger{
    List<String> fileLogs = new ArrayList<>();
    private static final int MAX_LOGS_LIMIT = 3;

    @Override
    public void addLog(String log) throws LoggerFailedException {
        if (fileLogs.size() >= MAX_LOGS_LIMIT) {
            throw new LoggerFailedException("File logger limit reached...Max Limit : " + MAX_LOGS_LIMIT  + "\nSwitching to Cloud Logger...");
        }

            fileLogs.add("File log : " + log);

    }

    @Override
    public void displayLogs() {
        System.out.println("-----*** File Logs ***-----");
        for (String log : fileLogs) {
            System.out.println(log);
        }
        System.out.println("------------------------------------");
    }
}
