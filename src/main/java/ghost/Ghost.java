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
    }
    public void tick(App app) {
        if (app.waka.isInvincible()) {
            this.frighten(true);
        } else {
            this.frighten(false);
        }

        if (this.frightened) {
            this.sprite = app.loadImage("src/main/resources/frightened.png");
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
        this.targetLine(app, this.targetX, this.targetY);
    }

    public void setTarget(App app) {
        if (this.getName().equals("Chaser")) {
            this.targetX = app.waka.CentreX();
            this.targetY = app.waka.CentreY();
        } else if (this.getName().equals("Ambusher")) {
            if (app.waka.getXVel() > 0) {
                this.targetX = app.waka.CentreX() + 64;
                this.targetY = app.waka.CentreY();
            } else if (app.waka.getXVel() < 0) {
                        this.targetX = app.waka.CentreX() - 64;
                        this.targetY = app.waka.CentreY();
            } else if (app.waka.getYVel() > 0) {
                        this.targetX = app.waka.CentreX();
                        this.targetY = app.waka.CentreY() + 64;
            } else if (app.waka.getYVel() < 0) {
                        this.targetX = app.waka.CentreX();
                        this.targetY = app.waka.CentreY() - 64;
            }
        } else if (this.getName().equals("Ignorant")) {
            double distance = Math.sqrt(Math.pow((this.CentreX() - app.waka.CentreX()), 2) + Math.pow((this.CentreY() - app.waka.CentreY()), 2));
            if (distance < 128) {
                this.targetX = app.waka.CentreX();
                this.targetY = app.waka.CentreY();
            } else {
                this.targetX = 40;
                this.targetY = 500;
            }
        } else if (this.getName().equals("Whim")) {
            this.targetX = 5;
            this.targetY = 5;
        }
    }

    public void die() {
        this.dead = true;
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
