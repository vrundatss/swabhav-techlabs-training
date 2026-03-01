package com.tss.MiniProject.FoodOrderingSystem.controller;

import com.tss.MiniProject.FoodOrderingSystem.auth.Authenticator.AuthService;
import com.tss.MiniProject.FoodOrderingSystem.service.*;

import java.util.Scanner;

public class AppFacade {

    private static AppFacade instance;

    private final AdminService adminService;
    private final CustomerService customerService;
    private final AuthService authService;
    private final PaymentService paymentService;
    private final OrderService orderService;
    private final InvoiceService invoiceService;
    private final DeliveryService deliveryService;
    private final NotificationService notificationService;
    private final CartService cartService;
    private final MenuService menuService;
    private final AnalyticsService analyticsService;

    private AppFacade() {
        // Initialize all services here
        this.adminService = new AdminService();
        this.customerService = new CustomerService();
        this.authService = new AuthService();
        authService.setFacade(this);
        this.paymentService = new PaymentService();
        this.orderService = new OrderService(this);
        this.invoiceService = new InvoiceService();
        this.deliveryService = new DeliveryService(this);
        this.notificationService = new NotificationService();
        this.cartService = new CartService();
        this.menuService = new MenuService();
        this.analyticsService = new AnalyticsService();
    }

    public static synchronized AppFacade getInstance() {
        if (instance == null) {
            instance = new AppFacade();
        }
        return instance;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public CartService getCartService() {
        return cartService;
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public AnalyticsService getAnalyticsService() {
        return analyticsService;
    }
}