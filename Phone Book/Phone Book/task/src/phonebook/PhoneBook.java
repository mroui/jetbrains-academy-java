package phonebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    private final List<Person> records;

    public PhoneBook(List<Person> list) {
        records = new ArrayList<>(list);
    }

    public static List<Person> read(String filename) {
        List<Person> people = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((line = reader.readLine()) != null && line.length() != 0) {
                Matcher matcherName = Pattern.compile("[A-Za-z]+").matcher(line);
                Matcher matcherPhone = Pattern.compile("[\\d]+").matcher(line);
                StringBuilder name = new StringBuilder();
                Integer phone = null;
                while (matcherName.find()) {
                    name.append(matcherName.group()).append(" ");
                }
                if (matcherPhone.find()) {
                    phone = Integer.parseInt(matcherPhone.group());
                }
                people.add(Person.create(name.toString(), phone));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return people;
    }

    public void search(List<Person> list) {
        System.out.println("Start searching...");
        long time = System.currentTimeMillis();
        long founded = Search.linear(Arrays.asList(records.toArray()), Arrays.asList(list.toArray()));
        time = System.currentTimeMillis() - time;
        long minutes = time / 60000;
        long seconds = time % 60000 / 1000;
        long milliseconds = (time % 60000) % 1000;
        System.out.println("Found " + founded + " / " + list.size() + " entries. Time taken: " + minutes + " min. " +
                seconds + " sec. " + milliseconds + " ms.");
    }
}
