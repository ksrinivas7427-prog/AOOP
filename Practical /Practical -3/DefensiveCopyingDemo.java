
import java.util.Date;

public class DefensiveCopyingDemo {

    static class Period {
        private final Date start;
        private final Date end;

        public Period(Date start, Date end) {

            // Copy input objects
            this.start = new Date(start.getTime());
            this.end = new Date(end.getTime());

            // Validate copied objects
            if (this.start.after(this.end)) {
                throw new IllegalArgumentException(
                    "Start date must be before or equal to end date");
            }
        }

        // Return defensive copies
        public Date start() {
            return new Date(start.getTime());
        }

        public Date end() {
            return new Date(end.getTime());
        }
    }

    public static void main(String[] args) {

        Date s = new Date(1000);
        Date e = new Date(5000);

        Period p = new Period(s, e);

        System.out.println("Before modification:");
        System.out.println("Start: " + p.start().getTime());
        System.out.println("End: " + p.end().getTime());

        // Modify original object
        e.setTime(0);

        System.out.println("\nAfter modifying original Date:");
        System.out.println("Start: " + p.start().getTime());
        System.out.println("End: " + p.end().getTime());

        // Modify returned copy
        p.end().setTime(0);

        System.out.println("\nAfter modifying returned copy:");
        System.out.println("End: " + p.end().getTime());
    }
}
