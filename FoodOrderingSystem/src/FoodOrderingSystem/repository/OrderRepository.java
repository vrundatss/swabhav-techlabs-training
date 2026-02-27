package FoodOrderingSystem.repository;//package com.tss.MiniProject.FoodOrderingSystem.repository;
//
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.Order;
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.OrderStatus;
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.PendingStatus;
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.DeliveredStatus;
//import com.tss.MiniProject.FoodOrderingSystem.model.Order.CancelledStatus;
////import com.tss.MiniProject.FoodOrderingSystem.model.Order.CompletedStatus;
//
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class OrderRepository extends CsvRepository<Order> {
//
//    private static final String FILE = "data/orders.csv";
//    private static final String HEADER = "id,customerId,itemMap,total,discount,finalAmount,deliveryAgentId,status,createdAt";
//
//    public OrderRepository() {
//        super(FILE, HEADER);
//    }
//
//    @Override
//    protected Order fromRow(String[] row) {
//        try {
//            int id = Integer.parseInt(row[0]);
//            String customerId = row[1];
//
//            // Parse itemId:qty;itemId2:qty2
//            Map<String, Integer> itemMap = new LinkedHashMap<>();
//            if (row[2] != null && !row[2].isBlank()) {
//                String[] pairs = row[2].split(";");
//                for (String pair : pairs) {
//                    String[] kv = pair.split(":");
//                    if (kv.length == 2) {
//                        itemMap.put(kv[0], Integer.parseInt(kv[1]));
//                    }
//                }
//            }
//
//            double total = Double.parseDouble(row[3]);
//            double discount = Double.parseDouble(row[4]);
//            double finalAmount = Double.parseDouble(row[5]);
//            int deliveryAgentId = Integer.parseInt(row[6]);
//
//            // Map status string to actual state object
//            String statusName = row[7].toUpperCase();
//            OrderStatus status = switch (statusName) {
//                case "DELIVERED" -> new DeliveredStatus();
//                case "CANCELLED" -> new CancelledStatus();
//                default -> new PendingStatus();
//            };
//
//            LocalDateTime createdAt = LocalDateTime.parse(row[8]);
//
//            return new Order(
//                    id,
//                    customerId,
//                    itemMap,
//                    total,
//                    discount,
//                    finalAmount,
//                    deliveryAgentId,
//                    status,
//                    createdAt
//            );
//
//        } catch (Exception e) {
//            System.err.println("Error parsing Order row: " + Arrays.toString(row));
//            return null;
//        }
//    }
//
//    @Override
//    protected String[] toRow(Order o) {
//        String items = o.getItemIdQty().entrySet().stream()
//                .map(e -> e.getKey() + ":" + e.getValue())
//                .collect(Collectors.joining(";"));
//
//        return new String[]{
//                String.valueOf(o.getId()),
//                String.valueOf(o.getCustomerId()),
//                items,
//                String.valueOf(o.getTotalAmount()),
//                String.valueOf(o.getDiscount()),
//                String.valueOf(o.getFinalAmount()),
//                String.valueOf(o.getDeliveryAgentId()),
//                o.getStatus().getName(),
//                o.getCreatedAt().toString()
//        };
//    }
//    public Order findById(int id) {
//        return getAll().stream()
//                .filter(o -> o.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public int nextId() {
//        return getAll().stream()
//                .mapToInt(Order::getId)
//                .max()
//                .orElse(0) + 1;
//    }
//
//    public void update(Order updated) {
//        List<Order> all = getAll();
//        for (int i = 0; i < all.size(); i++) {
//            if (all.get(i).getId() == updated.getId()) {
//                all.set(i, updated);
//                saveAll(all);
//                return;
//            }
//        }
//    }
//
//    public void delete(int id) {
//        List<Order> remaining = getAll().stream()
//                .filter(o -> o.getId() != id)
//                .toList();
//        saveAll(remaining);
//    }
//}