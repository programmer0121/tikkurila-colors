import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        // scrapColorsRGB();
        // convertToHSB();
    }

    private static void scrapColorsRGB() throws IOException {
        Scraper scraper = new Scraper();
        Writer writer = new Writer();
        Map<String,Color> colorMap = scraper.scrapAllColors();
        writer.writeRGB(colorMap);
    }

    private static void convertToHSB() throws IOException {
        Reader reader = new Reader();
        Writer writer = new Writer();
        Map<String,Color> colors = reader.read();
        writer.writeHSB(colors);
    }


}
