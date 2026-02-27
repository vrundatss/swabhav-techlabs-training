package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.MenuItem;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

import java.util.List;
import java.util.stream.Collectors;

public class MenuService {

    private final DataStore db  =DataStore.getInstance();

    public void addMenuItem(MenuItem item)
    {
        db.getMenuItems().add(item);
    }

    public void removeMenuItem(MenuItem item)
    {
        db.getMenuItems().remove(item);
    }

    public List<MenuItem> getItemByCategory(ItemCategoryType category){
        return  db.getMenuItems().stream()
                .filter(item-> item.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public void showMenu() {
        System.out.println("\nFull Menu:");
        db.getMenuItems().forEach(MenuItem::display);
    }
}
