package ghost;

import processing.core.PApplet; 
import processing.core.PFont;
import java.util.ArrayList;

public class App extends PApplet {

    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;  

    public Waka waka; 
    public Chaser chaser;
    public ArrayList<GameCell> cells = new ArrayList<GameCell>(); 
    public ArrayList<Wall> wallList = new ArrayList<Wall>();
    public ArrayList<Ghost> ghostList = new ArrayList<Ghost>();
	public ArrayList<GameCell> spaceList = new ArrayList<GameCell>();
    public int fruitCount = 0;
    public String map;
    public int lives;
    public int speed;
    public int frightenedLength;
    public int frightenedCounter;
    public ArrayList<Integer> modeLengths = new ArrayList<Integer>();
    public PFont gameFont; 
    public boolean debugMode;
    public int restartTime = 0;

    public App() {}

    public void setup() {
        frameRate(60);
        ParseJSON.reader("config.json", this);
        gameFont = createFont("PressStart2P-Regular.ttf", 32);
        MapParser mp = new MapParser();
        this.cells = mp.parse(this, this.map);
        mp.setGameAttributes(this);
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void draw() { 
        GameEvent.gameEvent(this);
    }

    public void keyPressed() {
        if (key == CODED) {
            this.waka.moveHandler(this, keyCode);
        } else if (key == ' ') {
            this.waka.initiateDebugMode(this);
        }
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
