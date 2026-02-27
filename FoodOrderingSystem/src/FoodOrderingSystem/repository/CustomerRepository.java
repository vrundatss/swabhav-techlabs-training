package FoodOrderingSystem.repository;

import com.tss.MiniProject.FoodOrderingSystem.model.Customer.Customer;

public class CustomerRepository extends CsvRepository<Customer> {
    private static final String FILE = "data/customers.csv";
    private static final String HEADER = "id,name,email,password,phone";

    public CustomerRepository() {
        super(FILE, HEADER);
    }

    @Override
    protected Customer fromRow(String[] row) {
        try {
            return new Customer.Builder()
                    .id(row[0])
                    .name(row[1])
                    .email(row[2])
                    .password(row[3])
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected String[] toRow(Customer c) {
        return new String[]{
                String.valueOf(c.getId()),
                c.getUsername(),
                c.getEmail(),
                String.valueOf(c.getPhoneNumber())
        };
    }

    public Customer findByUsername(String username) {
        return getAll().stream()
                .filter(a -> a.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }
}