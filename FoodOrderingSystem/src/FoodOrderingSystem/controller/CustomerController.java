package FoodOrderingSystem.controller;

import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.discount.DayBasedDiscountStrategy;
import com.tss.MiniProject.FoodOrderingSystem.model.discount.DiscountStrategy;
import com.tss.MiniProject.FoodOrderingSystem.model.discount.FestivalDiscountStrategy;
import com.tss.MiniProject.FoodOrderingSystem.model.discount.FlatBillDiscountStrategy;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;
import com.tss.MiniProject.FoodOrderingSystem.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerController {
    private final AppFacade facade;
    private final Scanner sc;
    private Customer currentCustomer = null;

    public CustomerController(AppFacade facade, Scanner sc) {
        this.facade = facade;
        this.sc = sc;
    }

    public void authMenu() {
        sc.nextLine();
        while (true) {
            System.out.println("\n--- CUSTOMER ACCESS ---");
            System.out.println("1. Login");
            System.out.println("2. Sign Up (For New Customer)");
            System.out.println("3. Logout");
            System.out.print("Selection: ");

            int choice = ValidationUtil.getValidInt(sc , "choice" , 1);
            if (choice == 3) break;

            switch (choice) {
                case 1 -> handleLogin();
                case 2 -> handleSignup();
                default -> System.out.println("Invalid selection.");
            }

            if (currentCustomer != null) {
                showMainMenu();
//                currentCustomer = null; // for logout
            }
        }
    }

    private void handleLogin() {
        System.out.println("\n--- Customer Login ---");
        System.out.print("Enter Username: ");
        String user = ValidationUtil.getValidName(sc);

        System.out.print("Enter Password: ");
        String pass = ValidationUtil.getValidPassword(sc);

        Customer c = facade.getAuthService().loginCustomer(user, pass);

        if (c != null) {
            this.currentCustomer = c;
            System.out.println("Login Successful! Welcome, " + c.getUsername());
        } else {
            System.out.println("Invalid Username or Password...");
        }
    }

//    private void handleLogin() {
//        System.out.print("Enter Email: "); String email = sc.next();
//        System.out.print("Enter Password: "); String password = sc.next();
//
//        Optional<Customer> customer = facade.getCustomerService().login(email, password);
//        if (customer.isPresent()) {
//            currentCustomer = customer.get();
//            System.out.println("Login Successful! Welcome, " + currentCustomer.getUsername());
//        } else {
//            System.out.println("Error: Invalid email or password.");
//        }
//    }

    private void handleSignup() {
        System.out.println("\n--- Create Customer Account ---");
        System.out.print("Name: ");
        String name = ValidationUtil.getValidName(sc);
        System.out.print("Username: ");
        String user = ValidationUtil.getValidName(sc);
        System.out.print("Password: ");
        String pass = ValidationUtil.getValidPassword(sc);
        System.out.print("Email: ");
        String email = ValidationUtil.getValidEmail(sc);
        System.out.print("Phone Number: ");
        String phone = ValidationUtil.getValidPhone(sc);
        System.out.print("Address: ");
        String address = ValidationUtil.getValidString(sc , "Address");
        try {
            Customer c = facade.getAuthService().registerCustomer(name, user, email, pass,address , phone);
            System.out.println("Registration Successful! Your ID: " + c.getId());
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }

    private void showMainMenu() {
        while (true) {
            System.out.println("\n--- CUSTOMER MAIN MENU ---");
            System.out.println("1. Browse Menu & Add to Cart");
            System.out.println("2. View Cart & Checkout");
            System.out.println("3. View Order History & Track");
            System.out.println("4. View Notifications");
            System.out.println("0. BACK");
            System.out.print("Selection: ");

            int choice = ValidationUtil.getValidInt(sc , "choice"  , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> browsingSubMenu();
                case 2 -> cartSubMenu();
                case 3 -> historySubMenu();
                case 4 -> notificationMenu();
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    // --- SUBMENU 1: BROWSING ---
    private void browsingSubMenu() {
        while (true) {
            System.out.println("\n--- BROWSE MENU ---");
            System.out.println("1. View All Items");
            System.out.println("2. View by Category");
            System.out.println("3. Add Item to Cart");
            System.out.println("0. BACK");
            int choice = ValidationUtil.getValidInt(sc , "choice"  , 0);
            if (choice == 0) break;

            switch (choice) {
                case 1 -> facade.getCustomerService().viewMenu().forEach(MenuItem::display);
                case 2 -> viewByCategory();
                case 3 -> handleAddToCart();
            }
        }
    }

    // --- SUBMENU 2: CART & CHECKOUT ---
    private void cartSubMenu() {
        while (true) {
            Map<String, Integer> cart = facade.getCustomerService().getCart(currentCustomer.getId());
            System.out.println("\n--- YOUR CART ---");
            if (cart.isEmpty()) {
                System.out.println("Your cart is empty.");
                break;
            }

            cart.forEach((id, qty) -> System.out.println("Item ID: " + id + " | Quantity: " + qty));
            double total = facade.getCustomerService().calculateCartTotal(currentCustomer.getId());
            System.out.println("Current Total: " + total + " rs.");

            System.out.println("\n1. Apply Discount & Checkout");
            System.out.println("2. Checkout without Discount");
            System.out.println("3. Clear Cart");
            System.out.println("0. BACK");
            System.out.print("Choice: ");
            int choice = ValidationUtil.getValidInt(sc , "choice"  , 0);
            if (choice == 0) break;

            double finalAmount = total;
            double discountAmount = 0.0;
            String discountName = "No Discount";

            if (choice == 1) {
                DiscountStrategy discountStrategy = selectActiveDiscountStrategy();
                if (discountStrategy != null) {

                    double discountedTotal = discountStrategy.applyDiscount(total);
                    discountAmount = total - discountedTotal;
                    finalAmount = discountedTotal;
                    discountName = discountStrategy.getDescription();
                    System.out.printf("%s Applied! You saved: %.2f rs.%n", discountName, finalAmount);
                    System.out.printf("New Payable Total: %.2f rs.%n", discountAmount);
                } else {
                    System.out.println("No discounts available currently!");
                }
            }

            if (choice == 1 || choice == 2) {
                try {
                    Order order = facade.getCustomerService()
                            .checkout(currentCustomer.getId(), total, discountAmount, finalAmount, discountName);
                    System.out.println("Order Placed Successfully! Order ID: " + order.getId());

                    handlePayment(order);

                    var payment = facade.getPaymentService().findPaymentByOrderId(order.getId());
                    var agent = facade.getAdminService().getDeliveryAgentById(order.getDeliveryAgentId());

                    System.out.println("\nGenerating invoice...");
                    new com.tss.MiniProject.FoodOrderingSystem.service.InvoiceService()
                            .printInvoice(order, payment, agent);
                    break;
                } catch (Exception e) {
                    System.out.println("Exception : " + e.getMessage());
                }
            } else if (choice == 3) {
                facade.getCustomerService().clearCart(currentCustomer.getId());
                System.out.println("Cart cleared...");
                break;
            }
        }
    }

    private DiscountStrategy selectActiveDiscountStrategy() {
        List<DiscountStrategy> activeDiscounts = new ArrayList<>();

        FestivalDiscountStrategy festival = new FestivalDiscountStrategy();
        if (festival.isApplicable()) activeDiscounts.add(festival);

        DayBasedDiscountStrategy day = new DayBasedDiscountStrategy();
        if (day.isApplicable()) activeDiscounts.add(day);

        FlatBillDiscountStrategy bill = new FlatBillDiscountStrategy();
        if (bill.isApplicable()) activeDiscounts.add(bill);

        if (activeDiscounts.isEmpty()) {
            return null;
        }

        System.out.println("\n--- CURRENTLY AVAILABLE DISCOUNTS ---");
        for (int i = 0; i < activeDiscounts.size(); i++) {
            System.out.println((i + 1) + ". " + activeDiscounts.get(i).getDescription());
        }
        System.out.println("0. BACK");
        System.out.print("Choice: ");
        int choice = ValidationUtil.getValidInt(sc , "choice"  , 0);

        if (choice == 0 || choice > activeDiscounts.size()) return null;
        return activeDiscounts.get(choice - 1);
    }

    private void viewCartAndCheckout() {
        // 1. Fetch the map using your getCart method
        Map<String, Integer> cartItems = facade.getCustomerService().getCart(currentCustomer.getId());

        if (cartItems.isEmpty()) {
            System.out.println("\n--- YOUR CART ---");
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n--- YOUR CART ---");
        double grandTotal = 0;

        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            String itemId = entry.getKey();
            int quantity = entry.getValue();

            // Find item details from the menu to show the Name and Price
            MenuItem item = facade.getAdminService().getItemById(itemId);

            if (item != null) {
                double total = item.getPrice() * quantity;
                System.out.printf("- %-20s | Qty: %d | Total: ₹%.2f%n", item.getName(), quantity, total);
                grandTotal += total;
            }
        }
        System.out.println("-------------------------------------------");
        System.out.printf("Grand Total: ₹%.2f%n", grandTotal);

    }

    private void handleAddToCart() {

        facade.getCustomerService().viewMenu().forEach(MenuItem::display);

        System.out.print("Enter Item ID: ");
        String itemId = ValidationUtil.getValidId(sc);

        // 1. Check if item exists in menu
        MenuItem item = facade.getAdminService().getItemById(itemId);

        if (item != null) {
            System.out.print("Enter Quantity: ");
            int qty = ValidationUtil.getValidInt(sc, "Quantity", 1);

            // 2. Validate Stock
            if (qty <= item.getStock()) {
                // 3. Use your specific Map-based logic
                facade.getCustomerService().addToCart(currentCustomer.getId(), itemId, qty);
                System.out.println("Item Successfully added to cart.");
            } else {
                System.out.println("Insufficient stock.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }
//    private void handleAddToCart() {
//        System.out.print("Enter Item ID: ");
//        String itemId = ValidationUtil.getValidId(sc);
//
//        // 1. Find the item
//        MenuItem selectedItem = facade.getAdminService().listAllMenuItems().stream()
//                .filter(i -> i.getId().equalsIgnoreCase(itemId))
//                .findFirst()
//                .orElse(null);
//
//        try {
//            // 2. Validate existence
//            if (selectedItem == null) {
//                throw new InvalidItemException("Exception : Item ID '" + itemId + "' does not exist.");
//            }
//
//            // 3. Ask for Quantity ONLY ONCE
//            System.out.print("Enter Quantity: ");
//            int qty = ValidationUtil.getValidInt(sc, "Quantity", 1);
//
//            // 4. Validate Stock
//            if (qty > selectedItem.getStock()) {
//                throw new InvalidItemException("Sorry... Not enough stock! Only " + selectedItem.getStock() + " available.");
//            }
//
//            // 5. Apply Decorators (selectedItem is updated here)
//            selectedItem = applyCustomizations(selectedItem);
//
//            // 6. ADD TO CART (Ensuring currentCustomer is not null)
//            if (currentCustomer != null) {
//                currentCustomer.getCart().add(new CartItem(selectedItem, qty));
//                System.out.println("\n[SUCCESS] Added to cart: " + selectedItem.getName());
//            } else {
//                System.out.println("[ERROR] No active customer session found. Please login again.");
//            }
//
//        } catch (InvalidItemException e) {
//            System.out.println(e.getMessage());
//            // CRITICAL: Exit method so we don't proceed with null/invalid data
//            return;
//        } catch (Exception e) {
//            System.out.println("An unexpected error occurred: " + e.getMessage());
//            return;
//        }
//    }

//    private MenuItem applyCustomizations(MenuItem baseItem) {
//        MenuItem decoratedItem = baseItem;
//
//        System.out.println("\n--- Customize your " + baseItem.getName() + " ---");
//
//        System.out.print("Add Extra Cheese? (y/n): ");
//        if (sc.next().equalsIgnoreCase("y")) {
//            decoratedItem = new ExtraCheeseDecorator(decoratedItem);
//        }
//
//        System.out.print("Add Extra Toppings? (y/n): ");
//        if (sc.next().equalsIgnoreCase("y")) {
//            decoratedItem = new ExtraToppingsDecorator(decoratedItem);
//        }
//
//        System.out.print("Add Extra Seasoning? (y/n): ");
//        if (sc.next().equalsIgnoreCase("y")) {
//            decoratedItem = new ExtraSeasoningDecorator(decoratedItem);
//        }
//
//        System.out.print("Make it Extra Spicy? (y/n): ");
//        if (sc.next().equalsIgnoreCase("y")) {
//            decoratedItem = new SpicyDecorator(decoratedItem);
//        }
//
//        return decoratedItem;
//    }

    private void notificationMenu() {
        System.out.println("\n--- INBOX ---");
        // Assuming currentAccount (Customer/Admin/Agent) is logged in
        var inbox = currentCustomer.getNotifications();

        if (inbox.isEmpty()) {
            System.out.println("No new notifications.");
        } else {
            inbox.forEach(System.out::println);
            System.out.print("\nClear all notifications? (y/n): ");
            if (sc.next().equalsIgnoreCase("y")) inbox.clear();
        }
    }

//    private void handleAddToCart() {
//        System.out.println("\n=========================== MENU ===========================");
//        facade.getCustomerService().viewMenu().forEach(MenuItem::display);
//        System.out.println();
//
//        try {
//            System.out.print("Enter Item ID: ");
//            String itemId = ValidationUtil.getValidId(sc);
//
//            // 1. Validate Item Existence
//            MenuItem item = facade.getAdminService().listAllMenuItems().stream()
//                    .filter(i -> i.getId().equalsIgnoreCase(itemId))
//                    .findFirst()
//                    .orElse(null);
//
//            if (item == null) {
//                throw new InvalidItemException("Exception : Item ID '" + itemId + "' does not exist.");
//            }
//
//            System.out.print("Enter Quantity: ");
//            int qty = sc.nextInt();
//
//            // to validate quantity of item
//            if (qty <= 0) {
//                throw new InvalidItemException("Exception : Quantity must be at least 1.");
//            }
//
//            // to validate stock availability
//            if (qty > item.getStock()) {
//                throw new InvalidItemException("Sorry... Not enough stock! Only " + item.getStock() + " available.");
//            }
//
//            currentCustomer.getCart().add(new CartItem(item, qty));
//            System.out.println("Success: " + qty + " x " + item.getName() + " added to cart.");
//
//        } catch (InvalidItemException e) {
//            System.err.println(e.getMessage());
//        } catch (Exception e) {
//            System.err.println("Invalid input. Please enter a valid Item ID and numeric quantity.");
//            sc.nextLine();
//        }
//    }


    private void handlePayment(Order order) {
        System.out.println("\n--- PAYMENT GATEWAY ---");
        System.out.println("Total Amount: " + order.getFinalAmount() + " rs.");
        System.out.print("Select Method (UPI/CARD/CASH): ");
        String method = sc.next();
        facade.getPaymentService().processPayment(order.getId(), method, order.getFinalAmount());
    }

    // --- SUBMENU 3: HISTORY & TRACKING ---
    private void historySubMenu() {
        while (true) {
            System.out.println("\n--- ORDER HISTORY ---");
            facade.getAdminService().getUserOrderHistory(currentCustomer.getId()).forEach(o -> {
                System.out.println("Order #" + o.getId() + " | Status: " + o.getStatus().getName() + " | Total:  " + o.getFinalAmount() + " rs.");
            });

            System.out.println("\n1. Track Specific Order");
            System.out.println("0. BACK");
            int choice = sc.nextInt();
            if (choice == 0) break;

            if (choice == 1) {
                System.out.print("Enter Order ID: ");
                int oid = sc.nextInt();

                // ===> Pending....
                // Logic to display details of that specific order status
                System.out.println("Tracking information fetched...");
            }
        }
    }

    private void viewByCategory(){
            System.out.println("\n--- SELECT CATEGORY ---");
            ItemCategoryType[] categories = ItemCategoryType.values();

            for (int i = 0; i < categories.length; i++) {
                System.out.println((i + 1) + ". " + categories[i].name());
            }
            System.out.println("0. BACK");
            System.out.print("Select Category Number: ");

            int catChoice = sc.nextInt();
            if (catChoice > 0 && catChoice <= categories.length) {

                ItemCategoryType selectedEnum = categories[catChoice - 1];
                String catName = selectedEnum.name();

                System.out.println("\n--- Items in " + catName + " ---");
                var items = facade.getCustomerService().viewByCategory(catName);

                if (items.isEmpty()) {
                    System.out.println("No items found in this category.");
                } else {
                    items.forEach(item -> {
                        // show the price including tax/discount
                        double finalPrice = selectedEnum.calculateFinalPrice(item.getPrice());
                        System.out.printf("ID: %s | %s | Base: %.2f | Final (Incl. Tax/Disc): %.2f%n",
                                item.getId(), item.getName(), item.getPrice(), finalPrice);
                    });
                }
            } else if (catChoice != 0) {
                System.out.println("Invalid category selection.");
            }

    }
}