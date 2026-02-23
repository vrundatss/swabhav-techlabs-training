package com.tss.CreationalDesignPattern.BuilderPatternPOC.model;

public class CreditCardPayment implements Payment{
    private final String transactionId;
    private final Integer cardNumber;
    private final String cardHolder;
    private final Double amount;
    private final String currency;
    private final String email;
    private final Integer phoneNo;

    private CreditCardPayment(CreditCardPaymentBuilder creditCardPaymentBuilder){
        this.transactionId = creditCardPaymentBuilder.transactionId;
        this.cardNumber = creditCardPaymentBuilder.cardNumber;
        this.cardHolder = creditCardPaymentBuilder.cardHolder;
        this.amount = creditCardPaymentBuilder.amount;
        this.currency= creditCardPaymentBuilder.currency;
        this.email = creditCardPaymentBuilder.email;
        this.phoneNo = creditCardPaymentBuilder.phoneNo;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing Credit Card Payment ==> ");
        System.out.println("-------------------------------------------------");
        System.out.println("Transaction Id  :  " + transactionId);
        System.out.println("Card Number     : " + cardNumber);
        System.out.println("Holder          : " + cardHolder);
        System.out.println("Amount          : " + amount + " " + currency);
        System.out.println("Email           : " + email);
        System.out.println("Phone Number    :" + phoneNo);
        System.out.println("Payment Successful!!!...\n");
        System.out.println("-------------------------------------------------");
    }

    public static class CreditCardPaymentBuilder{
        private  String transactionId;
        private  Integer cardNumber;
        private  String cardHolder;
        private  Double amount;
        private  String currency;
        private  String email;
        private  Integer phoneNo;

        public CreditCardPaymentBuilder(String transactionId , Integer cardNumber , Double amount){
            this.transactionId = transactionId;
            this.cardNumber = cardNumber;
            this.amount = amount;
        }

        public CreditCardPaymentBuilder setCardHolder(String cardHolder) {
            this.cardHolder = cardHolder;
            return this;
        }

        public CreditCardPaymentBuilder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public CreditCardPaymentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public CreditCardPaymentBuilder setPhoneNo(Integer phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public CreditCardPayment build() {
            return new CreditCardPayment(this);
        }
    }
}
