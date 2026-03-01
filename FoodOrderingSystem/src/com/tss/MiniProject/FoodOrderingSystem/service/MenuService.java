package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.DuplicateCategoryException;
import com.tss.MiniProject.FoodOrderingSystem.exceptions.DuplicateItemException;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuService {

    private final DataStore db  =DataStore.getInstance();

    public boolean isItemExists(String name){

        Optional<MenuItem> existingItem = db.getMenuItems().stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst();

        return existingItem.isPresent();
    }

    public void addMenuItem(MenuItem newItem) throws DuplicateItemException, DuplicateCategoryException {
        // Check if item with same name already exists
        Optional<MenuItem> existingItem = db.getMenuItems().stream()
                .filter(item -> item.getName().equalsIgnoreCase(newItem.getName()))
                .findFirst();

        if (existingItem.isPresent()) {
            MenuItem item = existingItem.get();

            if (item.getName().equalsIgnoreCase(newItem.getName())) {
                throw new DuplicateItemException(newItem.getName() + " already exists...");
            }

            if (item.getCategory() != newItem.getCategory()) {
                throw new DuplicateCategoryException("Conflict ==> '" + newItem.getName() +
                        "' already exists in " + item.getCategory() + ". Can't add to " + newItem.getCategory());
            }
//            item.addStock(newItem.getStock());
        } else {
            db.getMenuItems().add(newItem);
        }
    }

    public MenuItem getItemById(String itemId) {
        for (MenuItem item : db.getMenuItems()) {
            if (item.getId().equalsIgnoreCase(itemId)) {
                return item;
            }
        }
        return null;
    }

    public void toggleItemStatus(String itemId, boolean newStatus) {
        MenuItem item = getItemById(itemId);
        if (item != null) {
            item.setAvailable(newStatus);
        }
    }

    public List<MenuItem> listAllMenuItems() {
        return db.getMenuItems();
    }
}
