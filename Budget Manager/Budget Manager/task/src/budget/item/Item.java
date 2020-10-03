package budget.item;

import com.google.gson.annotations.Expose;

public class Item implements Comparable<Item> {

    @Expose
    private final String name;
    @Expose
    private final Double value;

    public Item(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void print() {
        System.out.println(name + " $" + String.format("%.2f", value));
    }

    @Override
    public int compareTo(Item item) {
        return value <= item.value ? 1 : -1;
    }
}
