package com.tss.MiniProject.FoodOrderingSystem.data;

import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.OrderObserver;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.Payment;
import com.tss.MiniProject.FoodOrderingSystem.model.discount.DiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static DataStore instance;

    private final List<Admin> admins = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<DeliveryAgent> agents = new ArrayList<>();
    private final List<MenuItem> menuItems = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<DiscountStrategy> discounts = new ArrayList<>();
    private final List<Payment> payments = new ArrayList<>();

    private List<OrderObserver> customerObservers = new ArrayList<>();
    private List<OrderObserver> agentObservers = new ArrayList<>();
    private List<OrderObserver> adminObservers = new ArrayList<>();


    private DataStore() {} //private constructor for Singleton

    public static synchronized DataStore getInstance() {
        if (instance == null) instance = new DataStore();
        return instance;
    }

    public List<Customer> getCustomers() { return customers; }
    public List<DeliveryAgent> getAgents() { return agents; }
    public List<MenuItem> getMenuItems() { return menuItems; }
    public List<Order> getOrders() { return orders; }
    public List<DiscountStrategy> getDiscounts() { return discounts; }
    public List<Admin> getAdmins() {
        return admins;
    }
    public List<Payment> getPayments() {
        return payments;
    }

    public List<OrderObserver> getCustomerObservers() { return customerObservers; }
    public List<OrderObserver> getAgentObservers() { return agentObservers; }
    public List<OrderObserver> getAdminObservers() { return adminObservers; }


}