package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.Payment;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class InvoiceService {

//    private final MenuItemRepository menuRepo;

    private final DataStore db  =DataStore.getInstance();

//    public InvoiceService(MenuItemRepository menuRepo) {
//        this.menuRepo = menuRepo;
//    }

    public void printInvoice(Order order, Payment payment, DeliveryAgent deliveryPartner) {
        System.out.println("\n=====================================================");
        System.out.println("                CUSTOMER INVOICE                  ");
        System.out.println("=====================================================");

        System.out.println("Order ID        : " + order.getId());
        System.out.println("Customer ID     : " + order.getCustomerId());
        System.out.println("Order Date      : " +
                order.getCreatedAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")));
        System.out.println("-----------------------------------------------------");
        System.out.println("Items Ordered:");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-35s %-10s %-10s%n", "Item Name", "Qty", "Price");

        double subtotal = 0;
        for (Map.Entry<String, Integer> entry : order.getItemIdQty().entrySet()) {
            String itemId = entry.getKey();
            int quantity = entry.getValue();

            MenuItem item = db.getMenuItems().stream()
                    .filter(m -> m.getId().equals(itemId))
                    .findFirst()
                    .orElse(null);

            if (item != null) {
                double itemTotal = item.getPrice() * quantity;
                subtotal += itemTotal;
                System.out.printf("%-35s %-10d ₹%-10.2f%n",
                        item.getName(), quantity, itemTotal);
            }
        }

        double discount = order.getDiscount();
        double totalPayable = order.getFinalAmount();

        System.out.println("-----------------------------------------------------");
        System.out.printf("%-25s ₹%-10.2f%n", "Subtotal", subtotal);
        System.out.printf("%-25s ₹%-10.2f%n", "Discount", discount);
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-25s ₹%-10.2f%n", "Total Payable", totalPayable);
        System.out.println("-----------------------------------------------------");

        if (order.getAppliedDiscountStrategy() != null) {
            System.out.println("Applied Discount: " + order.getAppliedDiscountStrategy());
        }

        System.out.println("=====================================================");
        System.out.println("PAYMENT DETAILS");
        System.out.println("Method    : " + payment.getMethod());
        System.out.println("Status    : " + payment.getStatus().getName());
        System.out.println("Paid At   : " + (payment.getPaidAt() != null ? payment.getPaidAt() : "Not Paid"));
        System.out.println("=====================================================");

        System.out.println("DELIVERY DETAILS");
        if (deliveryPartner != null) {
            System.out.println("Delivery Agent : " + deliveryPartner.getUsername());
            System.out.println("Agent Contact  : " + deliveryPartner.getPhoneNumber());
            System.out.println("Status         : " + (deliveryPartner.getAvailable() ? "Available" : "On Delivery"));
        } else {
            System.out.println("Delivery Agent : Not yet assigned");
        }

        System.out.println("=====================================================");
        System.out.println("Thank you for ordering!");
        System.out.println("=====================================================\n");
    }
}
