package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.Payment.Payment;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import static com.tss.MiniProject.FoodOrderingSystem.util.ColorUtils.*;

public class InvoiceService {

    private final DataStore db = DataStore.getInstance();

    public void printInvoice(Order order, Payment payment, DeliveryAgent deliveryPartner) {
        String border = "═".repeat(60);
        String divider = "─".repeat(60);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

        // 1. HEADER
        System.out.println("\n" + CYAN + "╔" + border + "╗" + RESET);
        System.out.println(CYAN + "║" + WHITE_BOLD + String.format(" %-58s ", "OFFICIAL TAX INVOICE") + CYAN + "║" + RESET);
        System.out.println(CYAN + "╠" + border + "╣" + RESET);

        // 2. ORDER INFO
        System.out.printf(CYAN + "║" + RESET + " %-15s : %-41s " + CYAN + "║%n" + RESET, "Order ID", "#" + order.getId());
        System.out.printf(CYAN + "║" + RESET + " %-15s : %-41s " + CYAN + "║%n" + RESET, "Customer", order.getCustomerId());
        System.out.printf(CYAN + "║" + RESET + " %-15s : %-41s " + CYAN + "║%n" + RESET, "Date", order.getCreatedAt().format(dtf));
        System.out.println(CYAN + "╟" + divider + "╢" + RESET);

        // 3. ITEMS TABLE HEADER
        System.out.printf(CYAN + "║" + WHITE_BOLD + " %-30s %-10s %-15s " + CYAN + "║%n" + RESET, "ITEM NAME", "QTY", "SUBTOTAL");
        System.out.println(CYAN + "╟" + divider + "╢" + RESET);

        // 4. ITEMS LOOP
        double calculatedSubtotal = 0;
        for (Map.Entry<String, Integer> entry : order.getItemIdQty().entrySet()) {
            MenuItem item = db.getMenuItems().stream()
                    .filter(m -> m.getId().equals(entry.getKey()))
                    .findFirst().orElse(null);

            if (item != null) {
                double itemTotal = item.getPrice() * entry.getValue();
                calculatedSubtotal += itemTotal;
                System.out.printf(CYAN + "║" + RESET + " %-30s x%-9d ₹%-14.2f " + CYAN + "║%n" + RESET,
                        item.getName(), entry.getValue(), itemTotal);
            }
        }

        // 5. SUMMARY CALCULATIONS
        System.out.println(CYAN + "╟" + divider + "╢" + RESET);
        System.out.printf(CYAN + "║" + RESET + " %-41s ₹%-15.2f " + CYAN + "║%n" + RESET, "Subtotal", calculatedSubtotal);

        if (order.getDiscount() > 0) {
            System.out.printf(CYAN + "║" + YELLOW + " %-41s -₹%-14.2f " + CYAN + "║%n" + RESET,
                    "Discount (" + order.getAppliedDiscountStrategy() + ")", order.getDiscount());
        }

        System.out.printf(CYAN + "║" + GREEN + " %-41s ₹%-14.2f " + CYAN + "║%n" + RESET,
                "TOTAL PAYABLE", order.getFinalAmount());
        System.out.println(CYAN + "╠" + border + "╣" + RESET);

        // 6. PAYMENT & DELIVERY
        System.out.println(CYAN + "║" + WHITE_BOLD + " PAYMENT: " + RESET +
                String.format("%-10s | STATUS: %-25s", payment.getMethod(), "COMPLETED") + CYAN + " ║" + RESET);

        String agentName = (deliveryPartner != null) ? deliveryPartner.getUsername() : "Pending Assignment";
        System.out.println(CYAN + "║" + WHITE_BOLD + " DELIVERY: " + RESET +
                String.format("%-48s", agentName) + CYAN + " ║" + RESET);

        // 7. FOOTER
        System.out.println(CYAN + "╚" + border + "╝" + RESET);
        System.out.println(GREEN + "      Thank you for choosing us! Enjoy your meal! " + RESET + "\n");
    }
}