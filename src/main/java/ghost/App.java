package ghost;

import processing.core.PApplet; // xming = export DISPLAY=:0
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
        if (this.fruitCount == 0) {
            this.gameFinishScreen(true, true);
            this.restartTime++;
            this.restart();
        } else if (this.lives == 0) {
            this.gameFinishScreen(true, false);
            this.restartTime++;
            this.restart();
        } else {
            background(0, 0, 0);
            for (GameCell cell : this.cells) {
                cell.tick(this);
                cell.draw(this);
            }
        }
        if (this.fruitCount > 0 && this.lives > 0) {
            this.ghostList.forEach((ghost) -> ghost.draw(this)); // draw the ghosts last so that their sprites appear over the fruit
        }
    }

    public void keyPressed() {
        if (key == CODED) {
            this.waka.moveHandler(this, keyCode);
        } else if (key == ' ') {
            this.waka.initiateDebugMode(this);
        }
    }

    public void gameFinishScreen(boolean display, boolean won) {
        if (display) {
            background(0);
            textFont(gameFont);
            if (won) {
                text("YOU WIN", WIDTH/4, HEIGHT/3);
            } else {
                text("GAME OVER", WIDTH/5, HEIGHT/3);
            }
        }
    }

    public void restart() {
        if (this.restartTime == 600) {
            this.gameFinishScreen(false, false);
            this.restartTime = 0;
            this.fruitCount = 0;
            this.ghostList.clear();
            this.setup();
        }
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
