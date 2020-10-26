package budget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public interface FileManager<T> {

    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    default boolean write(T object) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(readFilename()))) {
            writer.write(gson.toJson(object));
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }

    default T read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFilename()))) {
            return gson.fromJson(reader, getClass().getGenericSuperclass());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    private String readFilename() {
        System.out.print("Enter filename: ");
        return new Scanner(System.in).nextLine().trim();
    }
}
