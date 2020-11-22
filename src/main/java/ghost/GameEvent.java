package ghost;

import processing.core.PFont;
import java.util.ArrayList;
/**
 * Acts as the engine for the game. <br>
 * 
 * Hosts all game information, to be supplied to the App window
 * @author Ronen Bhaumik
 */
public class GameEvent {
    /** App object that GameEvent uses as a canvas */
    public App app;
    /** Game's Waka */
    public Waka waka; 
    /** Game's Chaser */
    public Chaser chaser;
    /** Game's map from config file */
    public String map;
    /** Game's Waka's amount of lives from config file */
    public int lives;
    /** Game's character speeds from config file */
    public int speed;
    /** Game's amount of time for a ghost to be frightend from config file */
    public int frightenedLength;
    /** Game's counter for amount of time ghosts are frightened */
    public int frightenedCounter;
    /** List of modelength times from config file */
    public ArrayList<Integer> modeLengths = new ArrayList<Integer>();
    /** List of gamecells to be drawn on to app window */
    public ArrayList<GameCell> cells = new ArrayList<GameCell>(); 
    /** List of gamecells that are walls */
    public ArrayList<Wall> wallList = new ArrayList<Wall>();
    /** List of gamecells that are ghosts */
    public ArrayList<Ghost> ghostList = new ArrayList<Ghost>();
    /** List of gamecells that a character can pass through */
    public ArrayList<GameCell> spaceList = new ArrayList<GameCell>();
    /** Game's counter for amount of fruit unconsumed */
    public int fruitCount = 0;
    /** Game's font for finished game screens */
    public PFont gameFont; 
    /** Game's state of whether debug mode has been activated */
    public boolean debugMode;
    /** Game's counter to restart time after win/loss */
    public int restartTime = 0;
    /**
     * Initialises a new GameEvent object
     */
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
     * @param app game window to be drawn in
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
     * The space key activates debug mode
     */
    public void initiateDebugMode() {
        if (this.debugMode) {
            this.debugMode = false;
        } else {
            this.debugMode = true;
        }
    }
    /**
     * Activates game finish screens for when the player either wins or loses
     * @param app game window to be drawn in 
     * @param display whether to display game finish screen
     * @param won whether the player has won or lost the game
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
     * @param app game window to be drawn in
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