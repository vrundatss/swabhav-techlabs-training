package com.tss.SOLID.DIP.model;

import com.tss.SOLID.DIP.exception.LoggerFailedException;

public interface Logger {
    void addLog(String log) throws LoggerFailedException;

    void displayLogs();

}
