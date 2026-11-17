
record Money(long minor, String currency) {

    Money {
        if (currency == null || currency.length() != 3) {
            throw new IllegalArgumentException(
                    "Currency must be a 3-letter code");
        }
    }

    Money add(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        return new Money(
                Math.addExact(minor, other.minor), currency);
    }
}

final class Account {

    private final String id;
    private Money balance;

    Account(String id, Money opening) {
        this.id = id;
        this.balance = opening;
    }

    void deposit(Money amount) {
        balance = balance.add(amount);
    }

    Money balance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Account other && other.id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

public class Program4Demo {
    public static void main(String[] args) {

        Money m1 = new Money(500, "INR");
        Money m2 = new Money(500, "INR");

        System.out.println("m1 == m2      : " + (m1 == m2));
        System.out.println("m1.equals(m2) : " + m1.equals(m2));

        Account a1 = new Account("AC-1", m1);
        Account a2 = new Account("AC-1", new Money(9999, "INR"));

        System.out.println("same id, different balance -> equals: "
                + a1.equals(a2));

        a1.deposit(new Money(250, "INR"));

        System.out.println("account balance: " + a1.balance());
        System.out.println("original value : " + m1);
    }
}
