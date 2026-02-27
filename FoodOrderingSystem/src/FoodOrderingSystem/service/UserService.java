package FoodOrderingSystem.service;

import com.tss.MiniProject.FoodOrderingSystem.model.User.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private  final List<User> userList = new ArrayList<>();

    public void addUser(User user){
        userList.add(user);
        System.out.println("User Added Successfully...");
    }

    public void removeUser(User user){
        userList.remove(user);
        System.out.println("User Removed Successfully...");
    }

    public User findUserById(String id) {
        return userList.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User findUserByUsername(String username) {
        return userList.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void displayAllUsers() {
        System.out.println("\nRegistered Users:");
        userList.forEach(u -> System.out.println("- " + u.getRole() + " : " + u.getUsername()));
    }

}
