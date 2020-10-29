package ghost;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class GameCell {
    public PImage sprite;  // these attributes are public so that 
    public int x;          // the subclasses can directly access them
    public int y;

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
