import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Reader {

    private static final String DELIMITER = ";";

    public Map<String, Color> read() throws IOException {
        Map<String,Color> colorMap = new HashMap<>();
        getLines().forEach(line -> {
            String[] fields = line.split(DELIMITER);
            String code = fields[0];
            int red = Integer.parseInt(fields[1]);
            int green = Integer.parseInt(fields[2]);
            int blue = Integer.parseInt(fields[3]);
            colorMap.put(code, new Color(red, green, blue));
        });
        return colorMap;
    }

    private List<String> getLines() throws IOException {
        try (InputStream resource = getClass().getResourceAsStream("colors-rgb.csv")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8));
            return br.lines().skip(1)
                    .filter(line -> !line.isBlank())
                    .collect(Collectors.toList());
        }
    }

}
