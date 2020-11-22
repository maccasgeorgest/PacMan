package ghost;

import processing.core.PImage;
/**
 * Acts as the blueprint for all game objects. <br>
 * 
 * Hosts all methods and attributes required by cell types: name, sprite and cartesian coordinates at any given frame
 * @author Ronen Bhaumik
 */
public abstract class GameCell {
    /** Hosts the gamecell's sprite */
    protected PImage sprite;  
    /** Hosts the gamecell's x coordinate */
    protected int x;            
    /** Hosts the gamecell's y coordinate */
    protected int y;
    /** Hosts the gamecell's name */
    protected String name;
    /** Hosts an array of the gamecell's cardinal coordinates */
    protected int[] cellCoord;
    /**
     * Abstract constructor for classes inheriting from GameCell
     */
    public GameCell(PImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.cellCoord = new int[]{this.x, this.y, this.x + 16, this.y + 16, this.x + 8, this.y + 8};
    }
    /**
     * Handles cell logic within the game
     * @param gameEvent GameEvent object that is hosting the game
     */
    public abstract void tick(GameEvent gameEvent);
    /**
     * Handles game graphics by drawing sprites on to the screen. 
     * Certain sprites require offsets to maintain pixel art
     * @param gameEvent GameEvent object that is hosting the game
     */
    public void draw(GameEvent gameEvent) { 
        if (this instanceof Superfruit) {
            gameEvent.app.image(this.sprite, this.x - 8, this.y - 8);
        } else if (this instanceof Ghost) {
            gameEvent.app.image(this.sprite, this.x - 6, this.y - 5);
        } else if (this instanceof Waka) {
            gameEvent.app.image(this.sprite, this.x - 4, this.y - 4);
        } else {
            gameEvent.app.image(this.sprite, this.x, this.y);
        }
    }
    /** 
     * @return game cell object name
     */
    public String getName() {
        return this.name;
    }
    /**
     * Sets game cell object coordinates
     */
    public void setCellCoord() {
        this.cellCoord[0] = this.x;
        this.cellCoord[1] = this.y;
        this.cellCoord[2] = this.x + 16;
        this.cellCoord[3] = this.y + 16;
        this.cellCoord[4] = this.x + 8;
        this.cellCoord[5] = this.y + 8;
    }
    /**
     * @return game cell object left coordinate
     */
    public int Left() {
        return this.cellCoord[0];
    }
    /**
     * @return game cell object top coordinate
     */
    public int Top() {  
        return this.cellCoord[1];
    }
    /**
     * @return game cell object right coordinate
     */
    public int Right() {
        return this.cellCoord[2];
    }

    /**
     * @return game cell object bottom coordinate
     */
    public int Bottom() {
        return this.cellCoord[3];
    }
    /**
     * @return game cell object centre horizontal coordinate
     */
    public int CentreX() {
        return this.cellCoord[4];
    }
    /**
     * @return game cell object centre vertical coordinate
     */
    public int CentreY() {
        return this.cellCoord[5];
    }
}
