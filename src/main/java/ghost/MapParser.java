package ghost;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import processing.core.PImage;
/**
 * Acts as the map parser, to set up game attributes and cells. <br>
 * 
 * Retrieves the supplied ASCII map and converts it into a list of cells <br>
 * Loops through the list and converts it to game information
 * @author Ronen Bhaumik
 */
public class MapParser {
    /**
     * Initialises a new MapParser object
     */
    public MapParser() {}
    /**
     * Parses map and returns an ArrayList of game cell objects, which provide
     * the functionality for the rest of the game
     * @param gameEvent GameEvent object that is hosting the game
     * @param filename ASCII map to be scanned
     * @return list of GameCells to be supplied to the GameEvent
     */
    public ArrayList<GameCell> parse(GameEvent gameEvent, String filename) {
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
                        mapList.add(new Wall(gameEvent.app.loadImage(wallKey(symbol)), x, y));
                    } else if (symbol.equals("7")) {
                        mapList.add(new Fruit(gameEvent.app.loadImage("src/main/resources/fruit.png"), x, y));
                    } else if (symbol.equals("8")) {
                        PImage superfruit = gameEvent.app.loadImage("src/main/resources/fruit.png");
                        superfruit.resize(32, 32);
                        mapList.add(new Superfruit(superfruit, x, y));
                    } else if (symbol.equals("p")) {
                        mapList.add(new EmptyCell(new PImage(), x, y));
                        mapList.add(new Waka(gameEvent.app.loadImage("src/main/resources/playerLeft.png"), x, y));
                    } else if (symbol.equals("a")) {
                        mapList.add(new EmptyCell(new PImage(), x, y));
                        mapList.add(new Ambusher(gameEvent.app.loadImage("src/main/resources/ambusher.png"), x, y));
                    } else if (symbol.equals("c")) {
                        mapList.add(new EmptyCell(new PImage(), x, y));
                        mapList.add(new Chaser(gameEvent.app.loadImage("src/main/resources/chaser.png"), x, y));
                    } else if (symbol.equals("i")) {
                        mapList.add(new EmptyCell(new PImage(), x, y));
                        mapList.add(new Ignorant(gameEvent.app.loadImage("src/main/resources/ignorant.png"), x, y));
                    } else if (symbol.equals("w")) {
                        mapList.add(new EmptyCell(new PImage(), x, y));
                        mapList.add(new Whim(gameEvent.app.loadImage("src/main/resources/whim.png"), x, y));
                    } else if (symbol.equals("s")) {
                        mapList.add(new Soda(gameEvent.app.loadImage("src/main/resources/Soda.png"), x, y));
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
     * @param key Wall's key to determine type of wall
     * @return file path to required wall sprite
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
     * @param gameEvent GameEvent object that is hosting the game
     */
    public void setGameAttributes(GameEvent gameEvent) {
        for (GameCell cell : gameEvent.cells) {
            if (cell.getName().equals("Chaser")) {
                gameEvent.chaser = (Chaser) cell;
            } if (cell.getName().equals("Waka")) {
                gameEvent.waka = (Waka) cell;
            } else if (cell.getName().equals("Empty")) {
                gameEvent.spaceList.add(cell);
            } else if (cell.getName().equals("Fruit") || 
                    cell.getName().equals("Superfruit")) {
                gameEvent.fruitCount++;
                gameEvent.spaceList.add(cell);
            } else if (cell.getName().equals("Soda")) {
                gameEvent.spaceList.add(cell);
            } else if (cell.getName().equals("Wall")) {
                gameEvent.wallList.add((Wall) cell);
            } else if (cell instanceof Ghost) {         // while using instanceof is generally bad code style, thought it was necessary here
                gameEvent.ghostList.add((Ghost) cell);  // as rather than adding another attribute to every single cell class and then only using 
            }                                           // it for ghost seemed a little pointless
        }
    }
}
