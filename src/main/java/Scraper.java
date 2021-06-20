import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scraper {

    private Map<String,String> exceptionalLinks; // code, url
    private static final String BASE_URL = "https://tikkurila.pl/dla-profesjonalistow/kolory/";

    public Scraper() {
        initExceptionalLinks();
    }

    private void initExceptionalLinks() {
        exceptionalLinks = new HashMap<>();
        try {
            InputStream is = getClass().getResourceAsStream("exceptional_colors.html");
            Document doc = Jsoup.parse(is, "UTF-8", "");
            doc.select("span.field-content a").forEach(element -> {
                String url = element.attr("href");
                String code = url.substring(url.length()-4);
                exceptionalLinks.put(code, url);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUrl(String code) {
        if (exceptionalLinks.containsKey(code))
            return exceptionalLinks.get(code);
        return BASE_URL + code + "-" + code;
    }

    private Color scrap(String code) throws IOException {
        String url = getUrl(code);
        System.out.println(code + ": " + url);
        Document doc = Jsoup.connect(url).get();
        String rgbText = doc.select("p.descriptionItem").get(0).text();
        String[] rgbArray = rgbText.split("[, ]+");
        int r = Integer.parseInt(rgbArray[0]);
        int g = Integer.parseInt(rgbArray[1]);
        int b = Integer.parseInt(rgbArray[2]);
        return new Color(r, g, b);
    }

    private Color tryScrap(String code) {
        try {
            return scrap(code);
        } catch (IOException e) {
            System.out.println("error for code: " + code);
            return null;
        }
    }

    public Map<String,Color> scrapAllColors() {
        Map<String, Color> colorMap = new HashMap<>();
        List<String> letters = Arrays.asList("f", "g", "h", "j", "k", "l", "m", "n", "s", "v", "x", "y");
        for (String letter : letters) {
            for (int codeNum = 300; codeNum <= 502; codeNum++) {
                String code = letter + codeNum;
                Color color = tryScrap(code);
                if (color != null)
                    colorMap.put(code, color);
            }
        }
        return colorMap;
    }

}
