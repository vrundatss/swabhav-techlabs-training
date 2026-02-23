package com.tss.SOLID.OCP.FixDepositSystem.test;

import com.tss.SOLID.OCP.FixDepositSystem.model.DiwaliOffer;
import com.tss.SOLID.OCP.FixDepositSystem.model.FixedDeposit;
import com.tss.SOLID.OCP.FixDepositSystem.model.HoliOffer;
import com.tss.SOLID.OCP.FixDepositSystem.service.FixedDepositService;

public class FixedDepositMain {
    public static void main(String[] args) {

        FixedDeposit fixedDeposit1 = new FixedDeposit(101 , "Vrunda" , 10000.0 , 2 ,new HoliOffer() );
        FixedDeposit fixedDeposit2 = new FixedDeposit(102 , "Abc Xyz" , 20000.0 , 5 ,new DiwaliOffer() );

        FixedDepositService fixedDepositService1 = new FixedDepositService(fixedDeposit1);
        FixedDepositService fixedDepositService2 = new FixedDepositService(fixedDeposit2);

        fixedDepositService1.calculateFD();

        fixedDepositService1.printFDDetails();

        fixedDepositService2.calculateFD();

        fixedDepositService2.printFDDetails();
    }
}
