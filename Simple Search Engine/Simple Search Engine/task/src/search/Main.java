package search;

import search.phonebook.PhoneBook;

public class Main {


    public static void main(String[] args) {
        try {
            PhoneBook phoneBook = new PhoneBook();
            phoneBook.fill();
            phoneBook.search();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.toString());
        }
    }
}
