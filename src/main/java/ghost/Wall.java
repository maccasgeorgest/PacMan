package ghost;

import processing.core.PImage;

public class Wall extends GameCell {

    int value; // Used to determine type of wall
    boolean collision = false;

    public Wall(PImage sprite, int x, int y) {
        super(sprite, x, y, true);
        this.name = "Wall";
    }

    public void tick(App app) {
        
    }
}
