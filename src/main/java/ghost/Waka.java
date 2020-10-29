package ghost;

import processing.core.PImage;

public class Waka extends GameCell {

    private int yVel;
    private int xVel;
    private String closedSprite = "src/main/resources/playerClosed.png";

    public Waka(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.yVel = 0;
        this.xVel = 0; 
    }

    public void tick(App app) { 
        this.y += yVel;
        this.x += xVel;
    }

    // Controls Waka movement by changing direction of velocity
    public void move(String command) {
        if (command.equals("up")) {
            yVel = -1;
            xVel = 0;
        } else if (command.equals("down")) {
            yVel = 1;
            xVel = 0;
        } else if (command.equals("left")) {
            xVel = -1;
            yVel = 0;
        } else if (command.equals("right")) {
            xVel = 1;
            yVel = 0;
        }
    }

    public String getClosedSprite() {
        return this.closedSprite;
    }
}
