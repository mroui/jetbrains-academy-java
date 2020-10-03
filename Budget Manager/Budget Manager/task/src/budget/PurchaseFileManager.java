package budget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;

public abstract class PurchaseFileManager {

    private static final String FILENAME = "purchases.txt";

    public static void write(Object object) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
            writer.write(gson.toJson(object));
            System.out.println("Purchases were saved!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
