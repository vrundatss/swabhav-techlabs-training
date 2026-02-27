package FoodOrderingSystem.repository;

import com.tss.MiniProject.FoodOrderingSystem.model.MenuItem.*;
import com.tss.MiniProject.FoodOrderingSystem.model.enums.ItemCategoryType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MenuItemRepository extends CsvRepository<MenuItem> {

    private static final String FILE = "data/menu_items.csv";
    private static final String HEADER = "type,id,name,price,category,available,includedItems";

    public MenuItemRepository() {
        super(FILE, HEADER);
    }

    @Override
    protected MenuItem fromRow(String[] row) {
        try {
            String type = row[0];
            String id = row[1];
            String name = row[2];
            double price = Double.parseDouble(row[3]);
            ItemCategoryType category = ItemCategoryType.valueOf(row[4]);
            boolean available = Boolean.parseBoolean(row[5]);

            if (type.equalsIgnoreCase("COMBO")) {
                List<String> included = new ArrayList<>();
                if (row.length > 6 && !row[6].isEmpty()) {
                    included = Arrays.stream(row[6].split(";"))
                            .map(String::trim)
                            .collect(Collectors.toList());
                }
                return new Combo.Builder()
                        .id(id)
                        .name(name)
                        .price(price)
                        .available(available)
                        .includedItemIds(included)
                        .build();
            }

            return new FoodItem.Builder()
                    .id(id)
                    .name(name)
                    .price(price)
                    .category(category)
                    .build();

        } catch (Exception e) {
            System.err.println("Error parsing MenuItem row: " + Arrays.toString(row));
            return null;
        }
    }

    @Override
    protected String[] toRow(MenuItem item) {
        String type = (item instanceof Combo) ? "COMBO" : "FOOD";
        String included = "";
        if (item instanceof Combo combo) {
            included = String.join(";", combo.getIncludedItemIds());
        }

        return new String[]{
                type,
                item.getId(),
                item.getName(),
                String.valueOf(item.getPrice()),
                item.getCategory().name(),
                String.valueOf(item.isAvailable()),
                included
        };
    }

    public MenuItem findById(String id) {
        return getAll().stream()
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public List<MenuItem> findByCategory(ItemCategoryType category) {
        return getAll().stream()
                .filter(i -> i.getCategory() == category)
                .collect(Collectors.toList());
    }

    public List<MenuItem> findAvailableItems() {
        return getAll().stream()
                .filter(MenuItem::isAvailable)
                .collect(Collectors.toList());
    }
}