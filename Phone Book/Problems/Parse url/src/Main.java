import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Map<String, String> arguments = parseUrl(line);
        for (String key : arguments.keySet()) {
            System.out.println(key + " : " + arguments.get(key));
        }
        if (arguments.get("pass") != null) {
            System.out.println("password : " + arguments.get("pass"));
        }
    }

    public static Map<String, String> parseUrl(String url) {
        Map<String, String> arguments = new LinkedHashMap<>();
        String argsPart = url.substring(url.indexOf("?") + 1);
        String[] args = argsPart.split("&");
        for (String pair : args) {
            String key = pair.substring(0, pair.indexOf("="));
            String value = pair.substring(pair.indexOf("=") + 1);
            arguments.put(key, (value.equals("") ? "not found" : value));
        }
        return arguments;
    }
}