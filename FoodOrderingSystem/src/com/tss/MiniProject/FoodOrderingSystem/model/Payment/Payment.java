package com.tss.MiniProject.FoodOrderingSystem.model.Payment;

import java.time.LocalDateTime;

public class Payment {
    private int orderId;
    private String method;
    private PaymentStatus status;
    private LocalDateTime paidAt;

    public Payment(int orderId, String method) {
        this.orderId = orderId;
        this.method = method;
        this.status = new PendingStatus();
        this.paidAt = null;
    }

    public Payment(int orderId, String method, PaymentStatus status, LocalDateTime paidAt) {
        this.orderId = orderId;
        this.method = method;
        this.status = status;
        this.paidAt = paidAt;
    }

    public int getOrderId() { return orderId; }
    public String getMethod() { return method; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }


    public void markFailed() {
        this.status = new FailedStatus();
        this.paidAt = LocalDateTime.now();
    }

}