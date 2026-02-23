package com.tss.SOLID.DIP.model;

import com.tss.SOLID.DIP.exception.LoggerFailedException;
import java.util.List;

public class FailoverLogger implements Logger {

    private List<Logger> loggers;

    public FailoverLogger(List<Logger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void addLog(String log) throws LoggerFailedException {
        boolean success = false;

        for (Logger logger : loggers) {
            try {
                logger.addLog(log);
                success = true;
                break;
            } catch (LoggerFailedException e) {
                System.out.println( logger.getClass().getSimpleName() + " failed: " + e.getMessage());
            }
        }

        if (!success) {
            throw new LoggerFailedException("All loggers failed to log message: " + log);
        }
    }

    @Override
    public void displayLogs() {
        displayAllLogs();
    }

    public void displayAllLogs() {
        System.out.println("\n===== All Logs =====");
        for (Logger logger : loggers) {
            logger.displayLogs();
        }
    }
}
