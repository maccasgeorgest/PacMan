package ghost;

import processing.core.PApplet;    // xming = export DISPLAY=:0
import java.util.ArrayList;

public class App extends PApplet {

    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;  

    public Waka waka = null; 
    public ArrayList<GameCell> sprites = new ArrayList<GameCell>(); 

    public String map;
    public long lives;
    public long speed;
    public int[] modeLengths;

    public App() {
        //Set up your objects
        ParseJSON.reader("config.json", this);
    }

    public void setWaka() {
        for (GameCell cell : this.sprites) {
            if (cell instanceof Waka) {
                this.waka = (Waka) cell;
                break;
            }
        }
    }

    public void setup() {
        frameRate(60);

        // Load images
        MapParser mp = new MapParser();
        this.sprites = mp.parse(this, this.map);

        this.setWaka(); // ALlows for easier control of the game waka
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void draw() { 
        background(0, 0, 0);
        for (GameCell cell : this.sprites) {
            cell.tick(this);
            cell.draw(this);
        }
    }

    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == UP) {
                this.waka.sprite = this.loadImage("src/main/resources/playerUp.png");
                this.waka.move("up");
            } else if (keyCode == DOWN) {
                this.waka.sprite = this.loadImage("src/main/resources/playerDown.png");
                this.waka.move("down");
            } else if (keyCode == LEFT) {
                this.waka.sprite = this.loadImage("src/main/resources/playerLeft.png");
                this.waka.move("left");
            } else if (keyCode == RIGHT) {
                this.waka.sprite = this.loadImage("src/main/resources/playerRight.png");
                this.waka.move("right");
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
