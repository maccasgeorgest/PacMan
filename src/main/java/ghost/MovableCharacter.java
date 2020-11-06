package ghost;

import processing.core.PImage;

public class MovableCharacter extends GameCell {

    protected int yVel;
    protected int xVel;    
    protected boolean skipMovement;

    public MovableCharacter(PImage sprite, int x, int y) {
        super(sprite, x, y, false);
    }
    
    public void tick(App app) {

    }

    public int getXVel() {
        return this.xVel;
    }
    
    public int getYVel() {
        return this.yVel;
    }
}
