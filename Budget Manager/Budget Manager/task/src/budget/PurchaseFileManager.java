package budget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;

public interface PurchaseFileManager {

    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String FILENAME = "purchases.txt";

    default void write(Object object) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            writer.write(gson.toJson(object));
            System.out.println("Purchases were saved!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
