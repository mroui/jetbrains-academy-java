package sorting;

public enum DataType {

    LONG("numbers"), LINE("lines"), WORD("words");

    private String valuesName;

    DataType(String name) {
        valuesName = name;
    }

    public String valuesName() {
        return valuesName;
    }

}
