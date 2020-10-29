package ghost;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import processing.core.PImage;
import processing.core.PApplet;

public class MapParser {

    public MapParser() {}
    
    public ArrayList<GameCell> parse(PApplet app, String filename) {
        if (!validMap(filename)) {
            return null;
        }

        ArrayList<GameCell> mapList = new ArrayList<GameCell>(); // List to be returned
        
        try {
            File gameMap = new File(filename);
            Scanner fileReader = new Scanner(gameMap);
            ArrayList<String> wallList = new ArrayList<String>(); // Create a list of 1-6 for wall types
            for (int i = 1; i < 7; i++) {
                wallList.add(Integer.toString(i));
            }
            int x = 0;
            int y = 0;
            while (fileReader.hasNextLine()) {
                String[] lineChars = fileReader.nextLine().split(""); // Each line is split into chars
                for (String symbol : lineChars) {
                    if (symbol.equals("0")) {
                        mapList.add(new EmptyCell(new PImage(), x, y));
                    } else if (wallList.contains(symbol)) {
                        mapList.add(new Wall(app.loadImage(wallKey(symbol)), x, y));
                    } else if (symbol.equals("7")) {
                        mapList.add(new Fruit(app.loadImage("src/main/resources/fruit.png"), x, y));
                    } else if (symbol.equals("p")) {
                        mapList.add(new Waka(app.loadImage("src/main/resources/playerLeft.png"), x - 4, y - 4));
                    } else if (symbol.equals("g")) {
                        mapList.add(new Ghost(app.loadImage("src/main/resources/ghost.png"), x - 6, y - 5));
                    }
                    x += 16;
                }
                x = 0;
                y += 16;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            return null;
        }
        return mapList;
    }

    public static String wallKey(String key) {
        HashMap<String,String> charKey = new HashMap<String,String>();
        charKey.put("1", "src/main/resources/horizontal.png");
        charKey.put("2", "src/main/resources/vertical.png");
        charKey.put("3", "src/main/resources/upLeft.png");
        charKey.put("4", "src/main/resources/upRight.png");
        charKey.put("5", "src/main/resources/downLeft.png");
        charKey.put("6", "src/main/resources/downRight.png");
        return charKey.get(key);
    }

    public static boolean validMap(String filename) {
        if (filename == null) {
            return false;
        }
        try {
            File testMap = new File(filename);
            Scanner fileReader = new Scanner(testMap);
            boolean containsWaka = false;
            boolean containsFruit = false;
            while (fileReader.hasNextLine()) {
                String[] line = fileReader.nextLine().split("");
                for (String symbol : line) {
                    if (symbol.equals("p")) {
                        containsWaka = true;
                    }
                    if (symbol.equals("7")) {
                        containsFruit = true;
                    }
                }
            }
            fileReader.close();
            if (!containsFruit || !containsWaka) {
                return false;
            }
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }
}
