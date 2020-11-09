package ghost;

import java.util.Random;

import processing.core.PImage;

public class Ghost extends MovableCharacter {

    protected int targetX;
    protected int targetY;
    protected boolean dead = false;
    protected boolean frightened = false;
    protected boolean scatter = false;
    protected final String[] possibleMoves = {"up", "down", "left", "right"};

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
        } if (this.isDead()) {
            this.sprite = new PImage();
        }

        boolean collision = CollisionGauge.collision(app.waka, this);
        if (collision) {
            if (this.isInvincible()) {
                app.waka.reset();
                app.lives--;
                for (Ghost ghost : app.ghostList) {
                    ghost.reset();
                }
            } else {
                this.die(true);
            }
        }

        Random random = new Random(); 
        int randomNumber = random.nextInt(possibleMoves.length);

        this.setCellCoord();
        this.move(possibleMoves[randomNumber], app);
        this.moveAfterCollision(app);
        
        if (!this.skipMovement) {
            this.y += this.yVel;
            this.x += this.xVel;
        }
        this.setTarget(app);
        if (app.debugMode) {
            if (!this.frightened) {
                this.targetLine(app, this.targetX, this.targetY);
            }
        }
    }

    public void setTarget(App app) {}
    
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
