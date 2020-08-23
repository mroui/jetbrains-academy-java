import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder html = new StringBuilder();
        while (scanner.hasNextLine()) {
            html.append(scanner.nextLine().trim());
        }

        Queue<String> tagsVal = new ArrayDeque<>();
        parseHtmlTagValue(html.toString(), tagsVal);
        while (tagsVal.size() > 0) {
            System.out.println(tagsVal.poll());
        }
    }

    public static void parseHtmlTagValue(String html, Queue<String> tagsVal) {
        Pattern tagPattern = Pattern.compile("<(\\S+?)(.*?)>(.*?)</\\1>");
        Matcher tagMatcher = tagPattern.matcher(html);
        while (tagMatcher.find()) {
            String val = tagMatcher.group(3);
            if (tagPattern.matcher(val).find()) {
                parseHtmlTagValue(val, tagsVal);
            }
            tagsVal.offer(val);
        }
    }
}