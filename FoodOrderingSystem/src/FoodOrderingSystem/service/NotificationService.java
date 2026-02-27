package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.data.DataStore;
import com.tss.MiniProject.FoodOrderingSystem.model.Order.OrderObserver;

import java.util.List;

public class NotificationService {

    DataStore db = DataStore.getInstance();

    public void subscribe(OrderObserver observer) {
        db.getObservers().add(observer);
    }

    public void unsubscribe(OrderObserver observer) {
        db.getObservers().remove(observer);
    }

    public void notifyUser(OrderObserver user, String message) {
        if (user != null) {
            user.update(message);
        }
    }

    // Send to a group (e.g., all Admins)
    public void notifyGroup(List<? extends OrderObserver> users, String message) {
        for (OrderObserver user : users) {
            user.update(message);
        }
    }
    public void notifyAll(String status, int orderId) {
        String message = "[NOTIFICATION] Order #" + orderId + " is now: " + status;
        for (OrderObserver observer : db.getObservers()) {
            observer.update(message);
        }
    }
}