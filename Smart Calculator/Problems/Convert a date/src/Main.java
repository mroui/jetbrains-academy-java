import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws ParseException {
        System.out.println(new SimpleDateFormat("MM/dd/yyyy")
                .format(new SimpleDateFormat("yyyy-MM-dd").parse(new Scanner(System.in).next())));
    }
}