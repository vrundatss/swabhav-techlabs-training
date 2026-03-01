package com.tss.MiniProject.FoodOrderingSystem.model.Order;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {
    private int id;
    private String customerId;
    private Map<String,Integer> itemIdQty; // itemId -> qty (menu items & combos use id)
    private double totalAmount;    // subtotal after category pricing and tax
    private double discount;       // total discount applied
    private double finalAmount;    // payable
    private String deliveryAgentId; // assigned partner id or -1
    private OrderStatus status;
    private LocalDateTime createdAt;
    private String appliedDiscountStrategy;
    private String address;
    private String paymentMethod;

    public Order() {}

    public Order(int id, String customerId, Map<String,Integer> itemIdQty, double totalAmount,
                 double discount, double finalAmount, String deliveryAgentId, OrderStatus status,
                 LocalDateTime createdAt, String address , String paymentMethod) {
        this.id = id;
        this.customerId = customerId;
        this.itemIdQty = itemIdQty;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.deliveryAgentId = deliveryAgentId;
        this.status = status;
        this.createdAt = createdAt;
        this.address = address;
        this.paymentMethod = paymentMethod;
    }

    public int getId() { return id; }
    public String  getCustomerId() { return customerId; }
    public Map<String,Integer> getItemIdQty() { return itemIdQty; }
    public double getTotalAmount() { return totalAmount; }
    public double getDiscount() { return discount; }
    public double getFinalAmount() { return finalAmount; }
    public String getDeliveryAgentId() { return deliveryAgentId; }
    public void setDeliveryAgentId(String id) { this.deliveryAgentId = id; }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setItemIdQty(Map<String, Integer> itemIdQty) {
        this.itemIdQty = itemIdQty;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void nextState() {
        status.next(this);
    }

    public void cancelOrder() {
        status.cancel(this);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAppliedDiscountStrategy() { return appliedDiscountStrategy; }
    public void setAppliedDiscountStrategy(String appliedDiscountStrategy) {
        this.appliedDiscountStrategy = appliedDiscountStrategy;
    }

    @Override
    public String toString() {
        return String.format(
                "| %-6d | %-12s | %-12s | %-12.2f | %-10.2f | %-12.2f | %-15s | %-12s | %-20s | %-30s |",
                id,
                customerId == null ? "-" : customerId,
                (deliveryAgentId == null ? "Unassigned" : deliveryAgentId),
                totalAmount,
                discount,
                finalAmount,
                paymentMethod,
                (status == null ? "PLACED" : status.getName()),
                createdAt == null ? "-" : createdAt.toString(),
                address == null ? "-" : address
        );
    }
}

