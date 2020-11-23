package search.phonebook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public static PhoneBook create(String filename) throws IOException {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.fill(filename);
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
        if (foundPeople.size() > 0) {
            System.out.println(foundPeople.size() + " persons found:");
            foundPeople.forEach(System.out::println);
        } else System.out.println("No matching people found.");
    }

    public void fill() {
        System.out.println("Enter the number of people:");
        int amount = readInt();
        System.out.println("Enter all people:");
        for (int i = 0; i < amount; i++)
            addPerson(readLine().split("\\s"));
    }

    private void fill(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null)
            addPerson(line.split("\\s"));
        reader.close();
    }

    private void addPerson(String[] personalData) {
        list.add(new Person(personalData[0],
                personalData.length > 1 ? personalData[1] : null,
                personalData.length == 3 ? personalData[2] : null));
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
