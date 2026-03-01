
package com.tss.MiniProject.FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.OrderObserver;

public class NotificationService {

    private final DataStore db = DataStore.getInstance();

    public void subscribeCustomer(OrderObserver observer) {
        db.getCustomerObservers().add(observer);
    }

    public void subscribeAgent(OrderObserver observer) {
        db.getAgentObservers().add(observer);
    }

    public void subscribeAdmin(OrderObserver observer) {
        db.getAdminObservers().add(observer);
    }

    public void notifyUser(OrderObserver user, String message) {
        if (user != null) user.update(message);
    }

    public void notifyAgents(String message) {
        for (OrderObserver o : db.getAgentObservers()) o.update(message);
    }

    public void notifyAdmins(String message) {
        for (OrderObserver o : db.getAdminObservers()) o.update(message);
    }

//    public void notifyAll(String status, int orderId) {
//        String msg = " Order #" + orderId + " is now: " + status;
//
//        db.getCustomerObservers().forEach(o -> o.update(msg));
//        db.getAgentObservers().forEach(o -> o.update(msg));
//        db.getAdminObservers().forEach(o -> o.update(msg));
//    }
}
