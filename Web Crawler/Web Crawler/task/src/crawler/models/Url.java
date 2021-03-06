package crawler.models;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static crawler.utils.Constants.*;

public class Url {

    private final String link;
    private final String title;

    public Url(String link, String title) {
        this.link = link;
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public static String extractWebTitle(String html) {
        Pattern titlePattern = Pattern.compile("<title>(.*?)</title>");
        Matcher titleMatcher = titlePattern.matcher(html);
        return titleMatcher.find() ? titleMatcher.group(1) : TITLE_NOT_FOUND;
    }

    public static ArrayList<String> extractHtmlHrefLinks(String html) {
        ArrayList<String> links = new ArrayList<>();
        Pattern linkPattern = Pattern.compile("<a.*href=['\"](.*?)['\"]");
        Matcher linkMatcher = linkPattern.matcher(html);
        while (linkMatcher.find()) {
            links.add(linkMatcher.group(1));
        }
        return links;
    }

    public static ArrayList<String> getAbsoluteUrls(String mainUrl, ArrayList<String> urls) {
        ArrayList<String> absoluteUrls = new ArrayList<>();
        for (String url : urls) {
            if (!url.startsWith("http")) {
                if (url.startsWith("//")) {
                    absoluteUrls.add(mainUrl.substring(0, mainUrl.indexOf(":") + 1) + url);
                } else {
                    absoluteUrls.add(mainUrl.substring(0, mainUrl.lastIndexOf("/") + 1) + url);
                }
            }
        }
        return absoluteUrls;
    }

    public static String extractHtmlFromUrl(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty(USER_AGENT_KEY, USER_AGENT_VALUE);
            if (connection.getContentType().contains(HTML_CONTENT_TYPE)) {
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                byte[] bytes = inputStream.readAllBytes();
                inputStream.close();
                return new String(bytes, StandardCharsets.UTF_8);
            } else {
                return null;
            }
        } catch (Exception exception) {
            return null;
        }
    }

    public static Url[] getUrlsDataFromMain(String mainUrl) {
        String mainHtml = extractHtmlFromUrl(mainUrl);
        ArrayList<String> urls = getAbsoluteUrls(mainUrl, extractHtmlHrefLinks(mainHtml));
        ArrayList<Url> data = new ArrayList<>();
        data.add(new Url(mainUrl, extractWebTitle(mainHtml)));
        for (String url : urls) {
            String html = extractHtmlFromUrl(url);
            if (html != null) {
                data.add(new Url(url, extractWebTitle(html)));
            }
        }
        return data.toArray(new Url[0]);
    }
}
