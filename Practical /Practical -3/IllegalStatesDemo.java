
import java.time.Instant;

public class IllegalStatesDemo {

    record PaymentId(int value) {}

    record Money(double amount) {}

    // Only these three payment types are allowed
    sealed interface Payment
        permits Pending, Paid, Failed {
    }

    record Pending(PaymentId id, Money amount)
        implements Payment {
    }

    record Paid(PaymentId id, Money amount, Instant paidAt)
        implements Payment {
    }

    record Failed(PaymentId id, Money amount, String reason)
        implements Payment {
    }

    static String describe(Payment payment) {

        if (payment instanceof Pending) {
            return "Waiting";
        }

        if (payment instanceof Paid p) {
            return "Paid at " + p.paidAt();
        }

        if (payment instanceof Failed f) {
            return "Failed: " + f.reason();
        }

        throw new IllegalStateException("Unknown payment");
    }

    public static void main(String[] args) {

        PaymentId id = new PaymentId(101);
        Money amount = new Money(500);

        Payment p1 = new Pending(id, amount);

        Payment p2 = new Paid(
            id, amount, Instant.parse("2026-11-16T10:00:00Z"));

        Payment p3 = new Failed(
            id, amount, "Insufficient balance");

        System.out.println("Payment 1: " + describe(p1));
        System.out.println("Payment 2: " + describe(p2));
        System.out.println("Payment 3: " + describe(p3));
    }
}
