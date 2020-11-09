package ghost;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class ParseJSON {

    public static void reader(String filename, App app) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filename));
            String  map = (String) jsonObject.get("map");
            app.map = map;
            Long  lives = (long) jsonObject.get("lives");
            int livesInt = lives.intValue();
            app.lives = livesInt;
            Long speed = (long) jsonObject.get("speed");
            int speedInt = speed.intValue();
            app.speed = speedInt;
            Long frightenedLength = (long) jsonObject.get("frightenedLength");
            int frightenedLengthInt = frightenedLength.intValue();
            app.frightenedLength = frightenedLengthInt;
            JSONArray modeLengths = (JSONArray) jsonObject.get("modeLengths");
            ArrayList<Integer> modeLengthsList = new ArrayList<Integer>();
            @SuppressWarnings("unchecked")
            Iterator<Integer> iterator = modeLengths.iterator();
            while (iterator.hasNext()) {
                modeLengthsList.add(iterator.next());
            }
            app.modeLengths = modeLengthsList;
            
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            return;
        } catch (ParseException e) {
            return;
        }
    }
    
}
