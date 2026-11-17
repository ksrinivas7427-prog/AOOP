class Employee {
    int employeeId;
    String name;
    double salary;

    Employee(int id, String n, double s) {
        employeeId = id;
        name = n;
        salary = s;
    }

    void display() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }

    public static void main(String[] args) {
        Employee e1 = new Employee(101, "ravi", 25000);
        e1.display();
    }
}