package FoodOrderingSystem.repository;

import com.tss.MiniProject.FoodOrderingSystem.model.DeliveryAgent.DeliveryAgent;

import java.util.Arrays;

public class DeliveryAgentRepository extends CsvRepository<DeliveryAgent> {

    private static final String FILE = "data/delivery_agents.csv";
    private static final String HEADER = "id,name,email,password,phone,isFree,earnings";

    public DeliveryAgentRepository() {
        super(FILE, HEADER);
    }

    @Override
    protected DeliveryAgent fromRow(String[] row) {
        try {
            return new DeliveryAgent.Builder()
                    .id(row[0])
                    .name(row[1])
                    .email(row[3])
//                    .phone(row[4])
//                    .isFree(Boolean.parseBoolean(row[5]))
//                    .earnings(Double.parseDouble(row[6]))
                    .build();
        } catch (Exception e) {
            System.err.println("Error parsing DeliveryAgent row: " + Arrays.toString(row));
            return null;
        }
    }

    @Override
    protected String[] toRow(DeliveryAgent a) {
        return new String[]{
                a.getId(),
                a.getUsername(),
                a.getEmail(),
                a.getPhoneNumber().toString(),
                String.valueOf(a.getAvailable()),
                String.valueOf(a.getEarnings())
        };
    }

    public DeliveryAgent findAvailableAgent() {
        return getAll().stream()
                .filter(DeliveryAgent::getAvailable)
                .findFirst()
                .orElse(null);
    }

    public DeliveryAgent findById(String id) {
        return getAll().stream()
                .filter(a -> a.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public DeliveryAgent findByUsername(String username) {
        return getAll().stream()
                .filter(a -> a.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
}