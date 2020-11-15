package ghost;

import processing.core.PImage;

public class MovableCharacter extends GameCell {

    protected int yVel;
    protected int xVel;    
    protected int initialX;
    protected int initialY;
    protected String direction = "left";
    protected String moveAttempt = "left";
    protected boolean invincible = false;
    protected boolean skipMovement;

    public MovableCharacter(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.initialX = x;
        this.initialY = y;
    }
    
    public void tick(App app) {}

    // Controls character movement by changing direction of velocity
    public void move(String command, App app) {
        if (command.equals("up")) {
            boolean check = CollisionGauge.turnCheck(app, this, "up");
            if (check) {
                this.direction = "up";
                this.yVel = -1 * (app.speed);
                this.xVel = 0;
            }
        } else if (command.equals("down")) {
            boolean check = CollisionGauge.turnCheck(app, this, "down");
            if (check) {
                this.direction = "down";
                this.yVel = app.speed;
                this.xVel = 0;
            }
        } else if (command.equals("left")) {
            boolean check = CollisionGauge.turnCheck(app, this, "left");
            if (check) {
                this.direction = "left";
                this.xVel = -1 * (app.speed);
                this.yVel = 0;
            }
        } else if (command.equals("right")) {
            boolean check = CollisionGauge.turnCheck(app, this, "right");
            if (check) {
                this.direction = "right";
                this.xVel = app.speed;
                this.yVel = 0;
            }
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

    public void initiateDebugMode(App app) {
        if (app.debugMode) {
            app.debugMode = false;
        } else {
            app.debugMode = true;
        }
    }

    public void reset() {
        this.x = this.initialX;
        this.xVel = 0;
        this.y = this.initialY;
        this.yVel = 0;
        this.moveAttempt = "left";
    }
}
