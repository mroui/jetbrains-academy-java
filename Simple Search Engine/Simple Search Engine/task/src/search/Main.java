package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            searchPeople(readPeople());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
        }
    }

    private static void searchPeople(List<Person> list) {
        List<Person> foundPeople = new ArrayList<>();
        System.out.println("\nEnter the number of search queries:");
        int amount = Integer.parseInt(scanner.nextLine());
        while (amount > 0) {
            System.out.println("\nEnter data to search people:");
            String data = scanner.nextLine();
            list.forEach(person -> {
                if (person.hasInCommon(data))
                    foundPeople.add(person);
            });
            if (foundPeople.size() > 0) {
                System.out.println("\nFound people:");
                foundPeople.forEach(System.out::println);
            } else System.out.println("No matching people found.");
            amount--;
            foundPeople.clear();
        }
    }

    private static List<Person> readPeople() {
        List<Person> people = new ArrayList<>();
        System.out.println("Enter the number of people:");
        int amount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter all people:");
        while (amount > 0) {
            String[] personalData = readWords();
            people.add(new Person(personalData[0], personalData[1], personalData.length == 3 ? personalData[2] : ""));
            amount--;
        }
        return people;
    }

    private static String[] readWords() {
        return scanner.nextLine().split("\\s");
    }
}
