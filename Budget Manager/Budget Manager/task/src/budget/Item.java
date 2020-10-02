package budget;

public class Item {

    private final String name;
    private final Double value;

    public Item(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public void print() {
        System.out.println(name + " $" + value);
    }
}
