import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String ip = new Scanner(System.in).next();
        String regex0to255 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        Pattern ipPattern = Pattern.compile("(" + regex0to255 + "\\.){3}" + regex0to255);
        System.out.println(ipPattern.matcher(ip).matches() ? "YES" : "NO");
    }
}