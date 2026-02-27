package FoodOrderingSystem.repository;

import com.tss.MiniProject.FoodOrderingSystem.model.Admin.Admin;

import java.util.Arrays;

public class AdminRepository extends CsvRepository<Admin> {
    private static final String FILE = "data/admins.csv";
    private static final String HEADER = "id,name,email,password";

    public AdminRepository() {
        super(FILE, HEADER);
    }

    @Override
    protected Admin fromRow(String[] row) {
        try {
            return new Admin.Builder()
                    .id(row[0])
                    .name(row[1])
                    .password(row[2])
                    .email(row[3])
                    .build();
        } catch (Exception e) {
            System.err.println("Error parsing Admin row: " + Arrays.toString(row));
            return null;
        }
    }

    @Override
    protected String[] toRow(Admin a) {
        return new String[]{
                String.valueOf(a.getId()),
                a.getId(),
                a.getUsername(),
                a.getAdminLevel()
        };
    }

    public Admin findByUsername(String username) {
        return getAll().stream()
                .filter(a -> a.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);
    }


}