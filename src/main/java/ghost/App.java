package ghost;

import processing.core.PApplet; // xming = export DISPLAY=:0
import processing.core.PFont;

import java.util.ArrayList;

public class App extends PApplet {

    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;  

    public Waka waka = null; 
    public Chaser chaser = null;
    public ArrayList<GameCell> sprites = new ArrayList<GameCell>(); 
    public ArrayList<Wall> wallList = new ArrayList<Wall>();
    public ArrayList<Ghost> ghostList = new ArrayList<Ghost>();
    public int fruitCount = 0;

    public String map;
    public int lives;
    public int speed;
    public int frightenedLength;
    public ArrayList<Integer> modeLengths = new ArrayList<Integer>();

    public boolean debugMode = false;
    public int restartTime;

    public App() {
        //Set up your objects
        ParseJSON.reader("config.json", this);
    }

    // Sets the waka for the game, and counts the total number of fruit and walls
    public void setGameAttributes() {
        for (GameCell cell : this.sprites) {
            if (cell.getName().equals("Chaser")) {
                this.chaser = (Chaser) cell;
            } if (cell.getName().equals("Waka")) {
                this.waka = (Waka) cell;
            } else if (cell.getName().equals("Fruit") || 
                    cell.getName().equals("Superfruit")) {
                this.fruitCount++;
            } else if (cell.getName().equals("Wall")) {
                this.wallList.add((Wall) cell);
            } else if (cell.getName().equals("Ghost")) {
                this.ghostList.add((Ghost) cell);
            }
        }
    }

    public void setup() {
        frameRate(60);
        // Load images
        MapParser mp = new MapParser();
        this.sprites = mp.parse(this, this.map);
        this.setGameAttributes();
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void draw() { 
        if (this.fruitCount == 0) {
            this.gameWinScreen();
        } else if (this.lives == 0) {
            this.gameOverScreen();
        } else {
            background(0, 0, 0);
            for (GameCell cell : this.sprites) {
                cell.tick(this);
                cell.draw(this);
            }
        }
    }

    public void keyPressed() {
        if (key == ' ') {
            if (this.debugMode) {
                this.debugMode = false;
            } else {
                this.debugMode = true;
            }
        }
        if (key == CODED) {
            if (keyCode == UP) {
                this.waka.move("up", this);
            } else if (keyCode == DOWN) {
                this.waka.move("down", this);
            } else if (keyCode == LEFT) {
                this.waka.move("left", this);
            } else if (keyCode == RIGHT) {
                this.waka.move("right", this);
            }
        }
    }

    public void gameWinScreen() {
        PFont gameFont = createFont("PressStart2P-Regular.ttf", 32);
        background(0);
        textFont(gameFont);
        text("YOU WIN", WIDTH/4, HEIGHT/3);
    }

    public void gameOverScreen() {
        PFont gameFont = createFont("PressStart2P-Regular.ttf", 32);
        background(0);
        textFont(gameFont);
        text("GAME OVER", WIDTH/5, HEIGHT/3);
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
