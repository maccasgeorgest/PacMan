package ghost;

import processing.core.PApplet; 

public class App extends PApplet {

    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;  
    public GameEvent gameEvent;

    public App() {}
    /** The setup() function is called once when the program starts. It establishes all initial conditions */
    public void setup() {
        frameRate(60);
        this.gameEvent = new GameEvent(this);
        this.gameEvent.gameSetup();
    }
    /** Sets up game window dimensions */
    public void settings() {
        size(WIDTH, HEIGHT);
    }
    /** */
    public void draw() { 
        this.gameEvent.gameLogic(this);
    }
    /** Called whenver a key is pressed - Waka is moved appropriately if UP, DOWN, LEFT, RIGHT is pressed, while space starts debug mode */
    public void keyPressed() {
        if (key == CODED) {
            this.gameEvent.waka.moveHandler(keyCode);
        } else if (key == ' ') {
            this.gameEvent.initiateDebugMode(this.gameEvent);
        }
    }
    /** Main method for entire app */
    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
