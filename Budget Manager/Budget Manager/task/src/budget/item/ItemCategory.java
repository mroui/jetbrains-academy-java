package budget.item;

import java.util.stream.Stream;

public enum ItemCategory {

    FOOD(1), CLOTHES(2), ENTERTAINMENT(3), OTHER(4);

    private final int value;

    ItemCategory(int value) {
        this.value = value;
    }

    public static ItemCategory get(int value) {
        return Stream.of(values()).filter(i -> i.value == value).findAny().orElse(null);
    }

    public static ItemCategory get(String value) {
        try {
            return get(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
