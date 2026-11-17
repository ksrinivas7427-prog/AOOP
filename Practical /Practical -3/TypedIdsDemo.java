
import java.util.UUID;

public class TypedIdsDemo {

    // Separate types for IDs
    record UserId(long value) {}

    record OrderId(UUID value) {
        public OrderId {
            if (value == null) {
                throw new IllegalArgumentException("Order ID required");
            }
        }

        public static OrderId random() {
            return new OrderId(UUID.randomUUID());
        }
    }

    // Method accepts specific ID types
    static void findOrder(UserId user, OrderId order) {
        System.out.println("User ID: " + user.value());
        System.out.println("Order ID: " + order.value());
        System.out.println("Order found successfully");
    }

    public static void main(String[] args) {

        UserId user = new UserId(101);
        OrderId order = OrderId.random();

        findOrder(user, order);

        // Wrong argument order will cause a compile-time error:
        // findOrder(order, user);
    }
}
