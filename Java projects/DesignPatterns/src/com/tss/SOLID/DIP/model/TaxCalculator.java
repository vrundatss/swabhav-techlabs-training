package com.tss.SOLID.DIP.model;

import com.tss.SOLID.DIP.exception.LoggerFailedException;

public class TaxCalculator {
    private final Logger logger;

    public TaxCalculator(Logger logger) {
        this.logger = logger;
    }

    public void calculateTax(double amount) {
        try {
            System.out.println("Calculating tax for amount: " + amount);

            if (amount < 0) {
                throw new LoggerFailedException("DBLogger failed, switching to File Logger...\n Invalid amount!!!...");
            }

            double tax = amount * 0.10;
            System.out.println("Calculated Tax : rs." + tax);

            logger.addLog("Tax calculation successful for amount " + amount);


        } catch (LoggerFailedException e) {

        }
    }

//    private void logToAll(String message) {
//        for (Logger logger : loggers) {
//            try {
//                logger.addLog(message);
//            } catch (DBFailedException e) {
//                System.out.println(logger.getClass().getSimpleName() + " failed, switching to File Logger...\n" + e.getMessage());
//            }
//        }
//    }

//    public void displayAllLogs() {
//        System.out.println("\n===== All Logs =====");
//            logger.displayLogs();
//    }
}
