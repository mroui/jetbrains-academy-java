package search;

import java.util.ArrayList;
import java.util.List;

import static search.InputReader.readInt;
import static search.InputReader.readLine;

public class PhoneBook {

    private List<Person> list;

    public PhoneBook() {
        list = new ArrayList<>();
    }

    public List<Person> get() {
        return list;
    }

    public void search() {
        System.out.println("\nEnter the number of search queries:");
        for (int i = 0; i < readInt(); i++) {
            System.out.println("\nEnter data to search people:");
            search(readLine());
        }
    }

    private void search(String data) {
        List<Person> foundPeople = new ArrayList<>();
        list.forEach(person -> {
            if (person.hasInCommon(data))
                foundPeople.add(person);
        });
        if (foundPeople.size() > 0) {
            System.out.println("\nFound people:");
            foundPeople.forEach(System.out::println);
        } else System.out.println("No matching people found.");
    }

    public void fill() {
        System.out.println("Enter the number of people:");
        int amount = readInt();
        System.out.println("Enter all people:");
        for (int i = 0; i < amount; i++) {
            String[] personalData = readLine().split("\\s");
            list.add(new Person(personalData[0],
                    personalData.length > 1 ? personalData[1] : "",
                    personalData.length == 3 ? personalData[2] : ""));
        }
    }

}
