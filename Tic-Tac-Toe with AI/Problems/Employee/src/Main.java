class Employee {

    String name;
    int salary;
    String address;
    private static final String UNKNOWN = "unknown";

    public Employee() {
        this(UNKNOWN, 0, UNKNOWN);
    }

    public Employee(String name, int salary) {
        this(name, salary, UNKNOWN);
    }

    public Employee(String name, int salary, String address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }
}
