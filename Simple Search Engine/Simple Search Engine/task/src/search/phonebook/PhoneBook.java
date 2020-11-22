package search.phonebook;

import java.util.ArrayList;
import java.util.List;

import static search.utils.InputReader.readInt;
import static search.utils.InputReader.readLine;

public class PhoneBook {

    private List<Person> list;

    public PhoneBook() {
        list = new ArrayList<>();
    }

    public List<Person> get() {
        return list;
    }

    public static PhoneBook create() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.fill();
        return phoneBook;
    }

    public void search() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        search(readLine());
    }

    private void search(String data) {
        List<Person> foundPeople = new ArrayList<>();
        list.forEach(person -> {
            if (person.hasInCommon(data))
                foundPeople.add(person);
        });
        if (foundPeople.size() > 0)
            foundPeople.forEach(System.out::println);
        else System.out.println("No matching people found.");
    }

    public void fill() {
        System.out.println("Enter the number of people:");
        int amount = readInt();
        System.out.println("Enter all people:");
        for (int i = 0; i < amount; i++) {
            String[] personalData = readLine().split("\\s");
            list.add(new Person(personalData[0],
                    personalData.length > 1 ? personalData[1] : null,
                    personalData.length == 3 ? personalData[2] : null));
        }
    }

    public void startService() {
        System.out.println("\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
        switch (readLine().trim()) {
            case "1":
                search();
                break;
            case "2":
                print();
                break;
            case "0":
                System.out.println("\nBye!");
                return;
            default:
                System.out.println("\nIncorrect option! Try again.");
        }
        startService();
    }

    private void print() {
        System.out.println("\n=== List of people ===");
        list.forEach(System.out::println);
    }
}
