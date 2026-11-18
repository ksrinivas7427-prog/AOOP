
import java.util.*;
import java.time.*;
import java.util.regex.Pattern;

// Email Address Class
class EmailAddress {
    private String email;

    public EmailAddress(String email) {
        email = email.trim().toLowerCase();

        if (!Pattern.matches(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            email)) {
            throw new IllegalArgumentException("Invalid Email");
        }

        this.email = email;
    }

    public String toString() {
        return email;
    }
}

// Phone Number Class
class PhoneNumber {
    private String number;

    public PhoneNumber(String number) {
        if (!number.matches("[0-9]{10}")) {
            throw new IllegalArgumentException("Invalid Phone Number");
        }

        this.number = number;
    }

    public String toString() {
        return number;
    }
}

// Postal Address Class
class PostalAddress {
    private String street, city, postalCode, country;

    public PostalAddress(String street, String city,
                         String postalCode, String country) {

        if (street.isBlank() || city.isBlank()
                || postalCode.isBlank() || country.isBlank()) {
            throw new IllegalArgumentException("Invalid Address");
        }

        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String toString() {
        return street + ", " + city + ", "
                + postalCode + ", " + country;
    }
}

// Verification Token Class
class VerificationToken {
    private String token;
    private Instant expiry;

    public VerificationToken() {
        token = UUID.randomUUID().toString();
        expiry = Instant.now().plus(Duration.ofMinutes(15));
    }

    public boolean isValid(String input) {
        return token.equals(input) &&
                Instant.now().isBefore(expiry);
    }

    public String getToken() {
        return token;
    }
}

// User Account Class
class UserAccount {

    enum Status {
        PENDING, ACTIVE, SUSPENDED, DELETED
    }

    private String userId;
    private EmailAddress email;
    private PhoneNumber phone;
    private PostalAddress address;
    private VerificationToken verificationToken;
    private Status status;

    public UserAccount(String userId, EmailAddress email,
                       PhoneNumber phone, PostalAddress address) {

        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.address = address;

        verificationToken = new VerificationToken();
        status = Status.PENDING;
    }

    // Email Verification
    public void verifyEmail(String input) {

        if (status != Status.PENDING)
            throw new IllegalStateException("Account is not pending");

        if (!verificationToken.isValid(input))
            throw new IllegalArgumentException("Invalid or expired token");

        status = Status.ACTIVE;
        verificationToken = null;

        System.out.println("Email Verified Successfully");
    }

    // Get Verification Token
    public String getToken() {
        return verificationToken.getToken();
    }

    // Login
    public void login() {
        if (status != Status.ACTIVE)
            throw new IllegalStateException("Login Denied");

        System.out.println("Login Successful");
    }

    // Suspend Account
    public void suspendAccount() {
        if (status == Status.DELETED)
            throw new IllegalStateException("Account is deleted");

        status = Status.SUSPENDED;
    }

    // Delete Account
    public void deleteAccount() {
        status = Status.DELETED;
    }

    // Display Details
    public void displayDetails() {
        System.out.println("User ID: " + userId);
        System.out.println("Email: " + email);
        System.out.println("Phone: " +
                (phone == null ? "Not Provided" : phone));
        System.out.println("Address: " +
                (address == null ? "Not Provided" : address));
        System.out.println("Status: " + status);
    }
}

// User Repository
class UserRepository {

    private Map<String, UserAccount> users = new HashMap<>();

    public void register(UserAccount user, String email) {

        String key = email.toLowerCase();

        if (users.containsKey(key))
            throw new IllegalArgumentException("Email Already Exists");

        users.put(key, user);
        System.out.println("User Registered Successfully");
    }
}

// Main Class
public class CaseStudy5 {

    public static void main(String[] args) {

        UserRepository repository = new UserRepository();

        EmailAddress email =
                new EmailAddress("student@gmail.com");

        PhoneNumber phone =
                new PhoneNumber("9876543210");

        PostalAddress address =
                new PostalAddress(
                    "Main Road", "Hyderabad",
                    "500055", "India");

        UserAccount user = new UserAccount(
                "U101", email, phone, address);

        // Registration
        repository.register(user, "student@gmail.com");

        // Email Verification
        String token = user.getToken();
        user.verifyEmail(token);

        // Display Details
        user.displayDetails();

        // Login
        user.login();

        // Suspend Account
        user.suspendAccount();

        try {
            user.login();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
