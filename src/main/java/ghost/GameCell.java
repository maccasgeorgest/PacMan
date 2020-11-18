package ghost;

import processing.core.PImage;

public abstract class GameCell {
    protected PImage sprite;  // these attributes are protected so that 
    protected int x;            // the subclasses can directly access them
    protected int y;
    protected String name;
    protected int[] cellCoord;

    public GameCell(PImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.cellCoord = new int[]{this.x, this.y, this.x + 16, this.y + 16, this.x + 8, this.y + 8};
    }

    public abstract void tick(GameEvent gameEvent); // handles logic
    /**
     * Handles game graphics by drawing sprites on to the screen. 
     * Certain sprites require offsets to maintain pixel art
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
     * Returns game cell object name
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
     * Returns game cell object left coordinate
     */
    public int Left() {
        return this.cellCoord[0];
    }
    /**
     * Returns game cell object top coordinate
     */
    public int Top() {  
        return this.cellCoord[1];
    }
    /**
     * Returns game cell object right coordinate
     */
    public int Right() {
        return this.cellCoord[2];
    }

    /**
     * Returns game cell object bottom coordinate
     */
    public int Bottom() {
        return this.cellCoord[3];
    }
    /**
     * Returns game cell object centre horizontal coordinate
     */
    public int CentreX() {
        return this.cellCoord[4];
    }
    /**
     * Returns game cell object centre vertical coordinate
     */
    public int CentreY() {
        return this.cellCoord[5];
    }
}
