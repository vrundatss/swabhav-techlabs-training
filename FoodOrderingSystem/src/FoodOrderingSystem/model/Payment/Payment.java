package FoodOrderingSystem.model.Payment;

import java.time.LocalDateTime;

public class Payment {
    private int orderId;
    private String method;               // CREDIT_CARD, UPI, COD, etc.
    private PaymentStatus status;        // State pattern implementation
    private LocalDateTime paidAt;        // Timestamp for payment

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

    // --- Getters & Setters ---
    public int getOrderId() { return orderId; }
    public String getMethod() { return method; }
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }

    public void markCompleted() {
        this.status = new CompletedStatus();
        this.paidAt = LocalDateTime.now();
    }

    public void markFailed() {
        this.status = new FailedStatus();
        this.paidAt = LocalDateTime.now();
    }

    public boolean isCompleted() {
        return status.getName().equalsIgnoreCase("COMPLETED");
    }

    public boolean isPending() {
        return status.getName().equalsIgnoreCase("PENDING");
    }

    public boolean isFailed() {
        return status.getName().equalsIgnoreCase("FAILED");
    }

}