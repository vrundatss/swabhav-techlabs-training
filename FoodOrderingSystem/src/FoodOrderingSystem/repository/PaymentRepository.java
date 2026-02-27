package FoodOrderingSystem.repository;

import com.tss.MiniProject.FoodOrderingSystem.model.Payment.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PaymentRepository extends CsvRepository<Payment> {

    private static final String FILE = "data/payments.csv";
    private static final String HEADER = "orderId,method,status,paidAt";

    public PaymentRepository() {
        super(FILE, HEADER);
    }

    @Override
    protected Payment fromRow(String[] row) {
        try {
            int orderId = Integer.parseInt(row[0]);
            String method = row[1];
            String statusName = row[2];
            LocalDateTime paidAt = (row.length > 3 && !row[3].isBlank())
                    ? LocalDateTime.parse(row[3])
                    : null;

            PaymentStatus status;
            switch (statusName.toUpperCase()) {
                case "COMPLETED" -> status = new CompletedStatus();
                case "FAILED" -> status = new FailedStatus();
                default -> status = new PendingStatus();
            }

            return new Payment(orderId, method, status, paidAt);

        } catch (Exception e) {
            System.err.println("Error parsing Payment row: " + Arrays.toString(row));
            return null;
        }
    }

    @Override
    protected String[] toRow(Payment payment) {
        return new String[]{
                String.valueOf(payment.getOrderId()),
                payment.getMethod(),
                payment.getStatus().getName(),
                payment.getPaidAt() != null ? payment.getPaidAt().toString() : ""
        };
    }

    public Payment findByOrderId(int orderId) {
        return getAll().stream()
                .filter(p -> p.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }

    public List<Payment> findCompleted() {
        return getAll().stream()
                .filter(Payment::isCompleted)
                .toList();
    }

    public List<Payment> findPending() {
        return getAll().stream()
                .filter(Payment::isPending)
                .toList();
    }

    public List<Payment> findFailed() {
        return getAll().stream()
                .filter(Payment::isFailed)
                .toList();
    }
}
