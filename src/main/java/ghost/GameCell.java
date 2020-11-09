package ghost;

import processing.core.PApplet;
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

    public abstract void tick(App app); // handles logic

    public void draw(PApplet app) { // handles graphics
        //app.rect(this.x, this.y, 16, 16);
        if (this instanceof Superfruit) {
            app.image(this.sprite, this.x - 8, this.y - 8);
        } else if (this instanceof Ghost) {
            app.image(this.sprite, this.x - 6, this.y - 5);
        } else if (this instanceof Waka) {
            app.image(this.sprite, this.x - 4, this.y - 4);
        } else {
            app.image(this.sprite, this.x, this.y);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setCellCoord() {
        this.cellCoord[0] = this.x;
        this.cellCoord[1] = this.y;
        this.cellCoord[2] = this.x + 16;
        this.cellCoord[3] = this.y + 16;
        this.cellCoord[4] = this.x + 8;
        this.cellCoord[5] = this.y + 8;
    }

    public int Left() {
        return this.cellCoord[0];
    }

    public int Top() {  
        return this.cellCoord[1];
    }

    public int Right() {
        return this.cellCoord[2];
    }

    public int Bottom() {
        return this.cellCoord[3];
    }

    public int CentreX() {
        return this.cellCoord[4];
    }

    public int CentreY() {
        return this.cellCoord[5];
    }
}
