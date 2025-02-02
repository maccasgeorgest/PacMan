package ghost;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import org.json.simple.parser.JSONParser;
/**
 * Acts as the json.config file parser. <br>
 * 
 * retrieving the game's map, Waka's lives and speed, and Ghost's frightened length and mode length intervals
 * @author Ronen Bhaumik
 */
public class ParseJSON {
    /**
     * Initialises a new ParseJSON object
     */
    public ParseJSON() {}

    /**
     * Takes in a JSON configuration file, and parses all relevant information.
     * This information is then passed to the App
     * @param filename json.config file to be parsed
     * @param gameEvent GameEvent object that is hosting the game
     */
    public static void reader(String filename, GameEvent gameEvent) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filename));
            String  map = (String) jsonObject.get("map");
            gameEvent.map = map;
            Long  lives = (long) jsonObject.get("lives");
            int livesInt = lives.intValue();
            gameEvent.lives = livesInt;
            Long speed = (long) jsonObject.get("speed");
            int speedInt = speed.intValue();
            gameEvent.speed = speedInt;
            Long frightenedLength = (long) jsonObject.get("frightenedLength");
            int frightenedLengthInt = frightenedLength.intValue();
            gameEvent.frightenedLength = frightenedLengthInt;
            JSONArray modeLengths = (JSONArray) jsonObject.get("modeLengths");
            ArrayList<Integer> modeLengthsList = new ArrayList<Integer>();
            for (Object obj : modeLengths) {
                modeLengthsList.add(((Long) obj).intValue());
            }
            gameEvent.modeLengths = modeLengthsList;
            
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            return;
        } catch (ParseException e) {
            return;
        }
    }
    
}
