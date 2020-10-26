package budget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
            for (Type genericInterface : getClass().getGenericInterfaces())
                if (genericInterface instanceof ParameterizedType)
                    return gson.fromJson(reader, ((ParameterizedType) genericInterface).getActualTypeArguments()[0]);
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
