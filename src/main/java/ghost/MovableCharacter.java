package ghost;

import processing.core.PImage;

public class MovableCharacter extends GameCell {

    protected int yVel;
    protected int xVel;    
    protected int initialX;
    protected int initialY;
    protected boolean invincible = false;
    protected boolean skipMovement;

    public MovableCharacter(PImage sprite, int x, int y) {
        super(sprite, x, y, false);
        this.initialX = x;
        this.initialY = y;
    }
    
    public void tick(App app) {
    }

    // Controls Waka movement by changing direction of velocity
    public void move(String command, App app) {
        if (command.equals("up")) {
            this.yVel = -1 * (app.speed);
            this.xVel = 0;
        } else if (command.equals("down")) {
            this.yVel = app.speed;
            this.xVel = 0;
        } else if (command.equals("left")) {
            this.xVel = -1 * (app.speed);
            this.yVel = 0;
        } else if (command.equals("right")) {
            this.xVel = app.speed;
            this.yVel = 0;
        }
    }

    public void moveAfterCollision(App app) {
        for (Wall wall : app.wallList) {
            boolean collision = CollisionGauge.collision(this, wall);
            if (collision) {
                break;
            }
        }
    }

    public void changeVulnerability(boolean invincible) {
        this.invincible = invincible;
    }

    public int getXVel() {
        return this.xVel;
    }
    public int getYVel() {
        return this.yVel;
    }

    public boolean isInvincible() {
        return this.invincible;
    }

    public void reset() {
        this.x = this.initialX;
        this.xVel = 0;
        this.y = this.initialY;
        this.yVel = 0;
    }
}
