package ghost;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.json.simple.JSONObject;
// import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class ParseJSON {

    public static void reader(String filename, App app) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject jsonObject = (JSONObject) obj;

            String  map = (String) jsonObject.get("map");
            app.map = map;
            Long  lives = (long) jsonObject.get("lives");
            int livesInt = lives.intValue();
            app.lives = livesInt;
            Long speed = (long) jsonObject.get("speed");
            int speedInt = speed.intValue();
            app.speed = speedInt;
            // DO MODELENGTHS

        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            return;
        } catch (ParseException e) {
            return;
        }
    }
    
}
