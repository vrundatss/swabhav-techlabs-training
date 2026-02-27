package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.PendingStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CustomerService {
    private final DataStore db = DataStore.getInstance();

    // Maps CustomerID ==> (MenuItemID , Quantity)
    private final Map<String, Map<String, Integer>> activeCarts = new HashMap<>();
    public CustomerService() {}


    public void registerCustomer(Customer customer) {
        db.getCustomers().add(customer);
    }

    public Optional<Customer> login(String loginInput, String plainPassword) {
//        String hashedInput = PasswordUtil.hash(plainPassword); // Hash the input first!

        return db.getCustomers().stream()
                .filter(c -> (loginInput.equals(c.getUsername()) || loginInput.equals(c.getEmail()))
                        && plainPassword.equals(c.getPassword()))
                .findFirst();
    }

//    public Optional<Customer> login(String username, String password) {
//        return db.getCustomers().stream()
//                .filter(c -> username.equals(c.getUsername()) &&
//                        PasswordUtil.hash(password).equals(c.getPassword()))
//                .findFirst();
//    }

    public List<MenuItem> viewMenu() {

        System.out.println("\n" + "=".repeat(125));
        System.out.printf("| %-10s | %-18s | %-15s | %-8s | %-11s | %-12s | %-11s |%n",
                "ITEM ID", "ITEM NAME", "CATEGORY", "STOCK", "UNIT PRICE", "FINAL (UNIT)", "GRAND TOTAL");
        System.out.println("=".repeat(125));

        return db.getMenuItems();
    }

    public List<MenuItem> viewByCategory(String categoryName) {
        return db.getMenuItems().stream()
                .filter(item -> item.getCategory().name().equalsIgnoreCase(categoryName))
                .toList();
    }

    // ===> CART MANAGEMENT

    public void addToCart(String customerId, String menuItemId, int quantity) {
        activeCarts.computeIfAbsent(customerId, k -> new HashMap<>())
                .merge(menuItemId, quantity, Integer::sum);
        System.out.println("Item added to cart.");
    }

    public Map<String, Integer> getCart(String customerId) {
        return activeCarts.getOrDefault(customerId, new HashMap<>());
    }

    public void clearCart(String customerId) {
        activeCarts.remove(customerId);
    }

    public double calculateCartTotal(String customerId) {
        Map<String, Integer> cart = activeCarts.get(customerId);
        if (cart == null || cart.isEmpty()) return 0.0;

        return cart.entrySet().stream()
                .mapToDouble(entry -> {
                    MenuItem item = db.findMenuItemById(entry.getKey());
                    return item == null ? 0 : item.getPrice() * entry.getValue();
                })
                .sum();
    }

    // ===> ORDERING FLOW

    public Order checkout(String customerId, double total, double discount, double finalAmount, String discountStrategyName) {
        Map<String, Integer> cart = activeCarts.get(customerId);
        if (cart == null || cart.isEmpty())
            throw new IllegalStateException("Cart is empty!");

        Order order = new Order();
        order.setId(db.getOrders().size() + 1);
        order.setCustomerId(customerId);
        order.setItemIdQty(new HashMap<>(cart));
        order.setTotalAmount(total);
        order.setDiscount(discount);
        order.setFinalAmount(finalAmount);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(new PendingStatus());
        order.setAppliedDiscountStrategy(discountStrategyName);

        db.getOrders().add(order);
        activeCarts.remove(customerId);
        return order;
    }

    private MenuItem findItemInList(String id) {
        return db.getMenuItems().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}