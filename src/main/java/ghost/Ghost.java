package ghost;

import processing.core.PImage;

public class Ghost extends MovableCharacter {

    protected int targetX;
    protected int targetY;
    protected int distanceX;
    protected int distanceY;
    protected boolean dead;
    protected String normalSprite;
    protected boolean frightened;
    protected int modeShiftCounter = 0;
    protected int modeInterval = 0;
    protected boolean scatter;

    public Ghost(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.invincible = true;
    }
    public void tick(App app) {
        if (app.waka.isInvincible()) {              // if waka has eaten superfruit
            this.frighten(true);
            this.changeVulnerability(false);
        } else {                                    // if waka has not eaten superfruit
            this.frighten(false);
            this.changeVulnerability(true);
        }
        if (this.frightened) {
            this.sprite = app.loadImage("src/main/resources/frightened.png");
            app.frightenedCounter++;
            int GhostMultiplier = app.ghostList.size(); // this is used to account for the fact that multiple ghosts are adding to any count per tick
            if (app.frightenedCounter == 60 * app.frightenedLength * GhostMultiplier) {
                this.frighten(false);
                app.waka.changeVulnerability(false);
                app.frightenedCounter = 0;
            } 
        } else {
            this.sprite = app.loadImage(this.normalSprite);
        }

        if (this.isDead()) {
            this.sprite = new PImage();
        }

        boolean collision = CollisionGauge.collision(app.waka, this);
        if (collision) {
            if (!this.isDead()) {
                if (this.isInvincible()) {
                    app.waka.reset();
                    app.lives--;
                    for (Ghost ghost : app.ghostList) {
                        ghost.reset();
                        ghost.die(false);
                    }
                } else {
                    this.die(true);
                }
            }
        }

        this.setCellCoord();
        this.setTarget(app, this.scatter);
        this.distanceX = this.CentreX() - this.targetX;
        this.distanceY = this.CentreY() - this.targetY;
        this.move(this.ghostAI(this.distanceX, this.distanceY, app), app);
        this.moveAfterCollision(app);
        
        if (!this.skipMovement) {
            this.y += this.yVel;
            this.x += this.xVel;
        }

        if (!this.frightened) {
            this.modeShiftCounter++;
        }
        int time = app.modeLengths.get(this.modeInterval); // time prescribed by config file
        if (this.modeShiftCounter == 60 * time) {
            this.switchMode();
            if (this.modeInterval == app.modeLengths.size() - 1) {
                this.modeInterval = -1; // if its the last element in the array, reset from beginning
            }
            this.modeInterval++;
            this.modeShiftCounter = 0;
        }
        
        if (app.debugMode) {
            if (!this.frightened) {
                if (!this.isDead()) {
                    this.targetLine(app, this.targetX, this.targetY);
                }
            }
        }
    }

    public void setTarget(App app, boolean mode) {}

    public String ghostAI(int distanceX, int distanceY, App app) {
        boolean up = CollisionGauge.turnCheck(app, this, "up");
        boolean down = CollisionGauge.turnCheck(app, this, "down");
        boolean left = CollisionGauge.turnCheck(app, this, "left");
        boolean right = CollisionGauge.turnCheck(app, this, "right");
        if (Math.abs(distanceX) < Math.abs(distanceY)) { // distance in X direction is closer
            if (distanceX > 0) {  // if target is to the left of ghost
                if (left) {
                    return "left";
                } else if (distanceY > 0 && up) {
                    return "up";
                } else if (distanceY < 0 && down) {
                    return "down";
                } else {
                    return "right";
                }
            } else { // if target is to the right of ghost
                if (right) {
                    return "right";
                } else if (distanceY > 0 && up) {
                    return "up";
                } else if (distanceY < 0 && down) {
                    return "down";
                } else {
                    return "left";
                }
            }
        } else { // distance in Y direction is closer
            if (distanceY > 0) { // if target is above ghost
                if (up) {
                    return "up";
                } else if (distanceX > 0 && left) {
                    return "left";
                } else if (distanceX < 0 && right) {
                    return "right";
                } else {
                    return "down";
                }
            } else { // if target is below ghost
                if (down) {
                    return "down";
                } else if (distanceX > 0 && left) {
                    return "left";
                } else if (distanceX < 0 && right) {
                    return "right";
                } else {
                    return "up";
                }
            }
        }
    }
    
    public void die(boolean death) {
        this.dead = death;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void frighten(boolean fright) {
        this.frightened = fright;
    }

    public void targetLine(App app, int targetX, int targetY) {
        app.stroke(255);
        app.line(this.CentreX(), this.CentreY(), targetX, targetY);
    }

    public void switchMode() {
        if (this.scatter) {
            this.scatter = false;
        } else {
            this.scatter = true;
        }
    }
}
