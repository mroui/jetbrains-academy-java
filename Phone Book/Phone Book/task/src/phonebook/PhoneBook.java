package phonebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {

    private final List<Person> records;
    private final Sort<Person> sort;
    private final Search<Person> search;

    public PhoneBook(List<Person> list) {
        records = new ArrayList<>(list);
        sort = new Sort<>();
        search = new Search<>();
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
        long time = linearSearch(list);
        jumpSearch(list, time * 200);
    }

    private long linearSearch(List<Person> list) {
        System.out.println("Start searching (linear search)...");
        long time = System.currentTimeMillis();
        long founded = search.linear(records, list);
        System.out.println("Found " + founded + " / " + list.size() +
                " entries. Time taken: " + getTimeTakenString(time) + '\n');
        return System.currentTimeMillis() - time;
    }

    private void jumpSearch(List<Person> list, long maxTime) {
        System.out.println("Start searching (bubble sort + jump search)...");
        long allTime = System.currentTimeMillis();
        boolean done;
        Future<Boolean> future = Executors.newCachedThreadPool().submit(() -> {
            sort.bubble(records);
            return true;
        });
        try {
            done = future.get(maxTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            done = false;
        }
        String sortingTimeString = getTimeTakenString(allTime);
        long searchingTime = System.currentTimeMillis();
        long founded = done ? search.jump(records, list) : search.linear(records, list);
        String searchingTimeString = getTimeTakenString(searchingTime);
        System.out.println("Found " + founded + " / " + list.size() + " entries. " +
                "Time taken: " + getTimeTakenString(allTime));
        System.out.println("Sorting time: " + sortingTimeString + (done ? "" : " - STOPPED, moved to linear search"));
        System.out.println("Searching time: " + searchingTimeString + '\n');
    }

    private String getTimeTakenString(long startTime) {
        long time = System.currentTimeMillis() - startTime;
        long minutes = time / 60000;
        long seconds = time % 60000 / 1000;
        long milliseconds = (time % 60000) % 1000;
        return minutes + " min. " + seconds + " sec. " + milliseconds + " ms.";
    }
}
