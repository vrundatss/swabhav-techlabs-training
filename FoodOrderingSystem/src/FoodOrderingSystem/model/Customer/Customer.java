package FoodOrderingSystem.model.Customer;

import com.tss.MiniProject.FoodOrderingSystem.model.Order.OrderObserver;
import com.tss.MiniProject.FoodOrderingSystem.model.User.AbstractUser;
import com.tss.MiniProject.FoodOrderingSystem.model.cart.CartItem;

import java.util.ArrayList;
import java.util.List;

public class Customer extends AbstractUser  implements OrderObserver {
    private String email;
    private String phoneNumber;
    private String address;

    private List<CartItem> cart = new ArrayList<>();
    private List<String> notifications = new ArrayList<>();

    private Customer(Builder builder) {
        super(builder);
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
    }

    @Override
    public String getRole() {
        return "Customer";
    }

    public static class Builder extends AbstractUser.Builder<Builder>{
        private String email;
        private String phoneNumber;
        private String address;

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Customer build() {
            return new Customer(this);
        }
    }
    public List<CartItem> getCart() {
        return cart;
    }

    public void clearCart() {
        this.cart.clear();
    }
    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public List<String> getNotifications() {
        return notifications;
    }

    @Override
    public void update(String message) {
        notifications.add(message);
    }


    @Override
    public String toString() {
        return String.format("| %-10s | %-15s | %-12s | %-20s | %-25s | %-10s |",
                getId(),
                getName(),
                getUsername(),
                getEmail() != null ? getEmail() : "N/A",
                getAddress() != null ? getAddress() : "N/A",
                getPhoneNumber());
    }
}
