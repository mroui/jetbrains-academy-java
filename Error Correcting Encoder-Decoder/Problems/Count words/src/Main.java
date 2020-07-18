import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().trim().split("[\\s]+", 0);
        System.out.println(str.length == 1 && str[0].isBlank() ? 0 : str.length);
        reader.close();
    }
}