package budget.item;

import com.google.gson.annotations.Expose;

public class Item {

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
        System.out.println(name + " $" + value);
    }
}
