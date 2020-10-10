package phonebook;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook(PhoneBook.read("files/directory.txt"));
        phoneBook.search(PhoneBook.read("files/find.txt"));
    }
}
