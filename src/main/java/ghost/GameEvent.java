package ghost;

import processing.core.PFont;
import java.util.ArrayList;

public class GameEvent {
    public App app;
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

    public GameEvent(App app) {
        this.app = app;
    }
    /**
     * Sets game attributes, as well as parsing the config file and the appropriate map to represent the game cells in window
     */
    public void gameSetup(){
        ParseJSON.reader("config.json", this);
        this.gameFont = app.createFont("PressStart2P-Regular.ttf", 32);
        MapParser mp = new MapParser();
        this.cells = mp.parse(this, this.map);
        mp.setGameAttributes(this);
    }
    /**
     * Runs cell logic, as well as starting game finish screens when necessary and resetting the game
     */
    public void gameLogic(App app) {
        if (this.fruitCount == 0) {
            this.gameFinishScreen(app, true, true);
            this.restartTime++;
            this.restart(app);
            
        } else if (this.lives == 0) {
            this.gameFinishScreen(app, true, false);
            this.restartTime++;
            this.restart(app);
        } else {
            app.background(0, 0, 0);
            for (GameCell cell : this.cells) {
                cell.tick(this);
                cell.draw(this);
            }
        }
        if (this.fruitCount > 0 && this.lives > 0) {
            this.ghostList.forEach((ghost) -> ghost.draw(this)); // draw the ghosts last so that their sprites appear over the fruit
        }
    }
    /**
     * Activates game finish screens for when the player either wins or loses
     */
    public void gameFinishScreen(App app, boolean display, boolean won) {
        if (display) {
            app.background(0);
            app.textFont(this.gameFont);
            if (won) {
                app.text("YOU WIN", App.WIDTH/4, App.HEIGHT/3);
            } else {
                app.text("GAME OVER", App.WIDTH/5, App.HEIGHT/3);
            }
        }
    }
    /**
     * Restarts game from initial conditions
     */
    public void restart(App app) {
        if (this.restartTime == 600) {
            this.gameFinishScreen(app, false, false);
            this.restartTime = 0;
            this.fruitCount = 0;
            this.ghostList.clear();
            app.setup();
        }
    }
}