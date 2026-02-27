package FoodOrderingSystem.model.Restaurant;

import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.AbstractMenuItem;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private final String id;
    private final String name;
    private final String address;
    private final String contact;
    private final String cuisineType;
    private final Boolean active;

    private final List<AbstractMenuItem> menuItems;

    private Restaurant(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.address = builder.address;
        this.contact = builder.contact;
        this.cuisineType = builder.cuisineType;
        this.active = builder.active;
        this.menuItems = builder.menuItems;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContact() { return contact; }
    public String getCuisineType() { return cuisineType; }
    public Boolean isActive() { return active; }

    public List<AbstractMenuItem> getMenuItems() { return menuItems; }

    public void addMenuItem(AbstractMenuItem item) {
        menuItems.add(item);
    }

    public void removeMenuItem(String itemId) {
        menuItems.removeIf(i -> i.getId().equals(itemId));
    }

    public void showMenu() {
        System.out.println("\n===== " + name.toUpperCase() + " MENU =====");
        for (AbstractMenuItem item : menuItems) {
            System.out.printf("%-5s %-20s Rs.%.2f%n", item.getId(), item.getName(), item.getPrice());
        }
    }

    public static class Builder {
        private String id;
        private String name;
        private String address;
        private String contact;
        private String cuisineType;
        private Boolean active = true;
        private List<AbstractMenuItem> menuItems = new ArrayList<>();

        public Builder setId(String id) { this.id = id; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setAddress(String address) { this.address = address; return this; }
        public Builder setContact(String contact) { this.contact = contact; return this; }
        public Builder setCuisineType(String cuisineType) { this.cuisineType = cuisineType; return this; }
        public Builder setActive(Boolean active) { this.active = active; return this; }
        public Builder setMenuItems(List<AbstractMenuItem> items) { this.menuItems = items; return this; }

        public Restaurant build() { return new Restaurant(this); }
    }
}
