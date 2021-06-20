import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Writer {

    private static final String DELIMITER = ";";

    private String getLineRGB(String code, Color color) {
        return code + DELIMITER + color.getRed() + DELIMITER + color.getGreen() + DELIMITER + color.getBlue();
    }

    private String getLineHSB(String code, Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return code + DELIMITER + hsb[0] + DELIMITER + hsb[1] + DELIMITER + hsb[2];
    }

    public void writeRGB(Map<String, Color> colorMap) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("colors-rgb.csv"));
        colorMap.forEach((code, color) ->
            writer.println(getLineRGB(code, color))
        );
        writer.close();
    }

    public void writeHSB(Map<String, Color> colorMap) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("colors-hsb.csv"));
        colorMap.forEach((code, color) ->
                writer.println(getLineHSB(code, color))
        );
        writer.close();
    }
}
