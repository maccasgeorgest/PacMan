package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class GameCell {
    protected PImage sprite;  // these attributes are protected so that 
    protected int x;            // the subclasses can directly access them
    protected int y;
    protected boolean impenetrable;
    protected int[] cellCoord;

    public GameCell(PImage sprite, int x, int y, boolean impenetrable) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.impenetrable = impenetrable;
        this.cellCoord = new int[]{this.x, this.y, this.x + 16, this.y + 16, this.x + 8, this.y + 8};
    }

    public abstract void tick(App app); // handles logic

    public void draw(PApplet app) { // handles graphics
        app.image(this.sprite, this.x, this.y);
    }

    public int[] getCellCoord() {
        return this.cellCoord;
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
