package search;

import search.phonebook.PhoneBook;

public class Main {

    public static void main(String[] args) {
        try {
            PhoneBook phoneBook = args.length == 2 && args[0].equals("--data")
                    ? PhoneBook.create(args[1]) : PhoneBook.create();
            phoneBook.startService();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
        }
    }
}
