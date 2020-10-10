package phonebook;

import java.util.Objects;

public class Person {

    private final String firstName;
    private final String lastName;
    private final Integer phone;

    public Person(String firstName, String lastName, Integer phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public static Person create(String fullName, Integer phone) {
        String[] names = fullName.trim().split("\\s+");
        return names.length == 1 ? new Person(names[0], "", phone) : new Person(names[0], names[1], phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
