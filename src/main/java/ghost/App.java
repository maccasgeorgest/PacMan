package ghost;

import processing.core.PApplet;    // xming = export DISPLAY=:0
import java.util.ArrayList;

public class App extends PApplet {

    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;  

    public Waka waka = null; 
    public ArrayList<GameCell> sprites = new ArrayList<GameCell>(); 
    public int fruitCount = 0;

    public String map;
    public int lives;
    public int speed;
    public int[] modeLengths;

    public boolean gameWin = false;

    public App() {
        //Set up your objects
        ParseJSON.reader("config.json", this);
    }

    // Sets the waka for the game, and counts the total number of fruit
    public void setGameAttributes() {
        for (GameCell cell : this.sprites) {
            if (cell instanceof Waka) {
                this.waka = (Waka) cell;
                break;
            } else if (cell instanceof Fruit) {
                this.fruitCount++;
            }
        }
        System.out.println(this.fruitCount);
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
        if (this.gameWin) {
            // CODE FOR WINNING GAME
        }
        background(0, 0, 0);
        for (GameCell cell : this.sprites) {
            cell.tick(this);
            cell.draw(this);
        }

        this.image(this.waka.sprite, this.waka.Left(), this.waka.Top()); // so that waka is redrawn upon all other objects
    }

    public void keyPressed() {
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

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
