package com.tss.SOLID.OCP.FixDepositSystem.service;

import com.tss.SOLID.OCP.FixDepositSystem.model.FixedDeposit;

public class FixedDepositService {

    FixedDeposit fixedDeposit;

    public FixedDepositService(FixedDeposit fixedDeposit) {
        this.fixedDeposit = fixedDeposit;
    }

    public Double calculateFD(){
        return (fixedDeposit.getAmount() + (fixedDeposit.getAmount() * fixedDeposit.getDuration() * fixedDeposit.getFestivalOffer().rate()/100));
    }

    public void printFDDetails() {
        System.out.println("-------------------------------------");
        System.out.println("           Fixed Deposit Details           ");
        System.out.println("-------------------------------------");
        System.out.println("FD ID             : " + fixedDeposit.getId());
        System.out.println("Holder Name       : " +fixedDeposit.getHolderName());
        System.out.println("Amount            : rs." + fixedDeposit.getAmount());
        System.out.println("Duration          : " + fixedDeposit.getDuration() + " years");
        System.out.println("Festival Type     : " + fixedDeposit.getFestivalOffer().getClass().getSimpleName());
        System.out.println("Rate              : " + fixedDeposit.getFestivalOffer().rate()  + " %");
        System.out.println("Matured Amount    : rs." + calculateFD());
        System.out.println("-------------------------------------");
    }

}
