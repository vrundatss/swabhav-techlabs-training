package FoodOrderingSystem.controller;

import com.tss.MiniProject.FoodOrderingSystem.auth.Authenticator.AuthService;
import com.tss.MiniProject.FoodOrderingSystem.service.*;

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
    private final DiscountService discountService;

    private AppFacade() {
        // Initialize all services here
        this.adminService = new AdminService();
        this.customerService = new CustomerService();
        this.authService = new AuthService();
        authService.setFacade(this);
        this.paymentService = new PaymentService();
        this.orderService = new OrderService();
        this.invoiceService = new InvoiceService();
        this.deliveryService = new DeliveryService();
        this.notificationService = new NotificationService();
        this.discountService = new DiscountService();
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

    public DiscountService getDiscountService() {
        return discountService;
    }
}