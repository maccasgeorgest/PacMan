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
    public PFont gameFont; 

    public boolean debugMode = false;
    public int restartTime;

    public App() {
        ParseJSON.reader("config.json", this);
    }

    public void setup() {
        frameRate(60);
        gameFont = createFont("PressStart2P-Regular.ttf", 32);
        MapParser mp = new MapParser();
        this.sprites = mp.parse(this, this.map);
        mp.setGameAttributes(this);
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void draw() { 
        if (this.fruitCount == 0) {
            this.gameFinishScreen(true);
        } else if (this.lives == 0) {
            this.gameFinishScreen(false);
        } else {
            background(0, 0, 0);
            for (GameCell cell : this.sprites) {
                cell.tick(this);
                cell.draw(this);
            }
        }
    }

    public void keyPressed() {
        if (key == CODED) {
            this.waka.moveHandler(this, keyCode);
        } else if (key == ' ') {
            this.waka.initiateDebugMode(this);
        }
    }

    public void gameFinishScreen(boolean won) {
        background(0);
        textFont(gameFont);
        if (won) {
            text("YOU WIN", WIDTH/4, HEIGHT/3);
        } else {
            text("GAME OVER", WIDTH/5, HEIGHT/3);
        }
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
