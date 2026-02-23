package com.tss.SOLID.DIP.test;

import com.tss.SOLID.DIP.model.*;

import java.util.List;
import java.util.Scanner;

public class LoggerMain {
    static final int MAX_LIMIT = 10;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DBLogger dbLogger = new DBLogger();
        FileLogger fileLogger = new FileLogger();
        CloudLogger cloudLogger = new CloudLogger();

        FailoverLogger failoverLogger = new FailoverLogger(List.of(dbLogger ,fileLogger , cloudLogger));

        TaxCalculator calculator = new TaxCalculator(failoverLogger);

        int count = 0;

        Double amount;
        while (count < MAX_LIMIT){
            System.out.print("\nEnter Amount : ");
            amount = scanner.nextDouble();

            if (amount < 0) {
                System.out.println("\nStopping input... user entered a negative number.");
                break;
            }

            try {
                calculator.calculateTax(amount);
            } catch (Exception e) {
                System.out.println("Error while calculating tax: " + e.getMessage());
            }

            count++;
        }

        failoverLogger.displayAllLogs();
    }
}
