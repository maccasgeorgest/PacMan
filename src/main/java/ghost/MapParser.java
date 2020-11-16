package ghost;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import processing.core.PImage;

public class MapParser {

    public MapParser() {}
    /**
     * Parses map and returns an ArrayList of game cell objects, which provide
     * the functionality for the rest of the game
     */
    public ArrayList<GameCell> parse(App app, String filename) {
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
                    } else if (symbol.equals("8")) {
                        PImage superfruit = app.loadImage("src/main/resources/fruit.png");
                        superfruit.resize(32, 32);
                        mapList.add(new Superfruit(superfruit, x, y));
                    } else if (symbol.equals("p")) {
                        mapList.add(new Waka(app.loadImage("src/main/resources/playerLeft.png"), x, y));
                    } else if (symbol.equals("a")) {
                        mapList.add(new Ambusher(app.loadImage("src/main/resources/ambusher.png"), x, y));
                    } else if (symbol.equals("c")) {
                        mapList.add(new Chaser(app.loadImage("src/main/resources/chaser.png"), x, y));
                    } else if (symbol.equals("i")) {
                        mapList.add(new Ignorant(app.loadImage("src/main/resources/ignorant.png"), x, y));
                    } else if (symbol.equals("w")) {
                        mapList.add(new Whim(app.loadImage("src/main/resources/whim.png"), x, y));
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
    /**
     * Associates wall type with specified number
     */
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

    /**
     * Sets the waka for the game, and counts the total number of fruit, as well as walls and spaces 
     * characters can walk through
     */
    public void setGameAttributes(App app) {
        for (GameCell cell : app.cells) {
            if (cell.getName().equals("Chaser")) {
                app.chaser = (Chaser) cell;
            } if (cell.getName().equals("Waka")) {
                app.waka = (Waka) cell;
            } else if (cell.getName().equals("empty")) {
                app.spaceList.add(cell);
            } else if (cell.getName().equals("Fruit") || 
                    cell.getName().equals("Superfruit")) {
                app.fruitCount++;
                app.spaceList.add(cell);
            } else if (cell.getName().equals("Wall")) {
                app.wallList.add((Wall) cell);
            } else if (cell instanceof Ghost) {   // while using instanceof is generally bad codestyle, thought it was necessary here
                app.ghostList.add((Ghost) cell);  // as rather than adding another attribute to every single cell class and then only using 
                                                  // it for ghost seemed a little pointless
            }
        }
    }
}
