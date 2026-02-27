package FoodOrderingSystem.data;

import com.tss.MiniProject.FoodOrderingSystem.controller.AppFacade;
import com.tss.MiniProject.FoodOrderingSystem.factory.UserFactory;
import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;
import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserInitializer {

    public static void loadDefaultUsers() {
        DataStore db = DataStore.getInstance();
        AppFacade facade = AppFacade.getInstance();

        if (db.getCustomers().isEmpty()) {

            Map<String, String> c1 = new HashMap<>();
            c1.put("id", "C-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
            c1.put("name", "Vrunda Chavda");
            c1.put("username", "vrunda");
            c1.put("email", "vrunda@gmail.com");
            c1.put("password", "vrunda123");
            c1.put("phone", "1234567898");
            c1.put("address", "Nana Mava Circle, Rajkot");

            Map<String, String> c2 = new HashMap<>();
            c2.put("id", "C-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase());
            c2.put("name", "Tulsi");
            c2.put("username", "tulsi");
            c2.put("email", "tulsi@gmail.com");
            c2.put("password", "tulsi123");
            c1.put("phone", "1234567898");
            c2.put("address", "Indira Circle, Rajkot");

            Customer customer1 = (Customer) UserFactory.createUser("customer", c1);
            Customer customer2 = (Customer) UserFactory.createUser("customer", c2);

            facade.getAuthService().registerCustomer(
                    customer1.getUsername(), customer1.getUsername(),
                    customer1.getEmail(), customer1.getPassword() , customer1.getAddress() , customer1.getPhoneNumber()
            );

            facade.getAuthService().registerCustomer(
                    customer2.getUsername(), customer2.getUsername(),
                    customer2.getEmail(), customer2.getPassword() ,
                    customer2.getAddress() , customer1.getPhoneNumber()
            );

            System.out.println("Default Customers Registered: " + db.getCustomers().size());
        }

        if (db.getAgents().isEmpty()) {
            var authService = facade.getAuthService();

            authService.registerDeliveryAgent(
                    "DA1",
                    "d1@example.com",
                    "Nana Mava Circle, Rajkot",
                    "GJ01-AB-1234",
                    "agent123"
            );

            authService.registerDeliveryAgent(
                    "DA2",
                    "d2@example.com",
                    "K.K.V Hall, Rajkot",
                    "GJ02-CD-5678",
                    "agent123"
            );

            System.out.println("Default Delivery Agents Registered: " + db.getAgents().size());
        }

        if (db.getAdmins().isEmpty()) {

            Map<String, String> adminData = new HashMap<>();
            adminData.put("id", "ADM-001");
            adminData.put("name", "System Admin");
            adminData.put("username", "a");
            adminData.put("password", "a");

            Admin admin = (Admin) UserFactory.createUser("admin", adminData);

            facade.getAdminService().addAdmin(admin);
            System.out.println("Default Admin Registered");
        }

        System.out.println("\n=== Default User Initialization Completed ===\n");
    }
}