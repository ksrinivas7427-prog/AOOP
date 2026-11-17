
import java.util.*;

public class CollectionsDemo {

    public static void main(String[] args) {

        // 1. ArrayList
        List<String> names = new ArrayList<>();

        names.add("Srinivas");
        names.add("Rahul");
        names.add("Arjun");

        System.out.println("ArrayList: " + names);

        // 2. LinkedList
        LinkedList<String> subjects = new LinkedList<>();

        subjects.add("Java");
        subjects.add("Python");

        System.out.println("LinkedList: " + subjects);

        // 3. HashSet
        Set<Integer> numbers = new HashSet<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(10);

        System.out.println("HashSet: " + numbers);

        // 4. LinkedHashSet
        Set<String> cities = new LinkedHashSet<>();

        cities.add("Hyderabad");
        cities.add("Warangal");
        cities.add("Karimnagar");

        System.out.println("LinkedHashSet: " + cities);

        // 5. TreeSet
        Set<Integer> marks = new TreeSet<>();

        marks.add(80);
        marks.add(60);
        marks.add(90);

        System.out.println("TreeSet: " + marks);

        // 6. HashMap
        Map<Integer, String> students = new HashMap<>();

        students.put(101, "Srinivas");
        students.put(102, "Rahul");

        System.out.println("HashMap: " + students);

        // 7. ArrayDeque
        Deque<String> queue = new ArrayDeque<>();

        queue.add("First");
        queue.add("Second");

        System.out.println("ArrayDeque: " + queue);

        // 8. PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(30);
        pq.add(10);
        pq.add(20);

        System.out.println("PriorityQueue: " + pq);
        System.out.println("Priority element: " + pq.peek());

        // 9. Immutable List
        List<String> fixed = List.of("Java", "Python");

        System.out.println("Immutable List: " + fixed);

        try {
            fixed.add("C");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable list");
        }
    }
}
