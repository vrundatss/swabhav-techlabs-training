package com.tss.CreationalDesignPattern.BuilderPatternPOC.model;

public class UPIPayment implements Payment {
    private final String transactionId;
    private final String upiId;
    private final Double amount;
    private final String currency;
    private final String email;
    private final Integer phoneNo;

    private UPIPayment(UPIPaymentBuilder upiPaymentBuilder) {
        this.transactionId = upiPaymentBuilder.transactionId;
        this.upiId = upiPaymentBuilder.upiId;
        this.amount = upiPaymentBuilder.amount;
        this.currency = upiPaymentBuilder.currency;
        this.email = upiPaymentBuilder.email;
        this.phoneNo = upiPaymentBuilder.phoneNo;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing UPI Payment ==> ");
        System.out.println("-------------------------------------------------");
        System.out.println("Transaction Id  :  " + transactionId);
        System.out.println("UPI Id          : " + upiId);
        System.out.println("Amount          : " + amount + " " + currency);
        System.out.println("Email           : " + email);
        System.out.println("Phone Number    : " + phoneNo);
        System.out.println("Payment Successful!!!...\n");
        System.out.println("-------------------------------------------------");
    }

    public static class UPIPaymentBuilder {
        private String transactionId;
        private String upiId;
        private Double amount;
        private String currency;
        private String email;
        private Integer phoneNo;

        public UPIPaymentBuilder(String transactionId, String upiId, Double amount) {
            this.transactionId = transactionId;
            this.upiId = upiId;
            this.amount = amount;
        }

        public UPIPaymentBuilder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public UPIPaymentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UPIPaymentBuilder setPhoneNo(Integer phoneNo) {
            this.phoneNo = phoneNo;
            return  this;
        }

        public UPIPayment build() {
            return new UPIPayment(this);
        }
    }
}
