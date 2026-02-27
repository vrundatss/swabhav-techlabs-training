package FoodOrderingSystem.model.DeliveryAgent;

import com.tss.MiniProject.FoodOrderingSystem.model.Order.OrderObserver;
import com.tss.MiniProject.FoodOrderingSystem.model.User.AbstractUser;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAgent extends AbstractUser implements OrderObserver {
//    private final String id;
//    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String vehicleNumber;
    private final String address;
    private Boolean isAvailable;
    private Double earnings;
    private Double totalEarnings;
    private List<String> notifications = new ArrayList<>();

    public DeliveryAgent(Builder builder) {
        super(builder);
//        this.id = builder.id;
//        this.name = builder.name;
        this.phoneNumber =  builder.phoneNumber;
        this.email = builder.email;
        this.vehicleNumber = builder.vehicleNumber;
        this.address = builder.address;
        this.isAvailable = builder.isAvailable;
        this.earnings= builder.earnings;
    }


    @Override
    public String getRole() {
        return "Delivery Agent";
    }

    public static class Builder extends AbstractUser.Builder<Builder> {
//        private String id;
//        private String name;
        private String phoneNumber;
        private String email;
        private String vehicleNumber;
        private String address;
        private Boolean isAvailable;
        private Double earnings;
//
//        public Builder id(String id) { this.id = id; return this; }
//        public Builder name(String name) { this.name = name; return this; }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Builder setEarnings(Double earnings) {
            this.earnings = earnings;
            return this;
        }

        @Override
        public Builder self() { return this; }

        @Override
        public DeliveryAgent build() {
            return new DeliveryAgent(this);
        }
    }

//    public String getName() {
//        return name;
//    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public String getEmail() {
        return email;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getAddress() {
        return address;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public void setTotalEarnings() {
        totalEarnings = totalEarnings + earnings;
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
        return String.format("| %-10s | %-15s | %-12s | %-25s | %-12s | %-10s | %-10.2f | %-10.2f |",
                id,
                name,
                phoneNumber == null ? "-" : phoneNumber,
                email == null ? "-" : email,
                vehicleNumber == null ? "-" : vehicleNumber,
                (isAvailable ? "Available" : "Busy"),
                earnings,
                totalEarnings);
    }
}
