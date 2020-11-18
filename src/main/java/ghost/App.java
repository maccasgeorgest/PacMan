package ghost;

import processing.core.PApplet; 

public class App extends PApplet {

    public static final int WIDTH = 448;
    public static final int HEIGHT = 576;  
    public GameEvent gameEvent;

    public App() {}

    public void setup() {
        frameRate(60);
        this.gameEvent = new GameEvent(this);
        this.gameEvent.gameSetup();
    }

    public void settings() {
        size(WIDTH, HEIGHT);
    }

    public void draw() { 
        this.gameEvent.gameLogic(this);
    }

    public void keyPressed() {
        if (key == CODED) {
            this.gameEvent.waka.moveHandler(keyCode);
        } else if (key == ' ') {
            this.gameEvent.waka.initiateDebugMode(this.gameEvent);
        }
    }

    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }
}
