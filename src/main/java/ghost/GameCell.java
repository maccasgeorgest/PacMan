package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class GameCell {
    protected PImage sprite;  // these attributes are protected so that 
    protected int x;          // the subclasses can directly access them
    protected int y;
    protected int[] cellCoord = {this.x, this.y, this.x + 16, this.y + 16};

    public GameCell(PImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }

    public abstract void tick(App app); // handles logic

    public void draw(PApplet app) { // handles graphics
        app.image(this.sprite, this.x, this.y);
    }
}
