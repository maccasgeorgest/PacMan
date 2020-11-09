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
        if (app.waka.isInvincible()) {
            this.frighten(true);
            this.changeVulnerability(false);
        } else {
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
                this.die();
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
        
            // the formula here was derived from the midpoint formula where Waka(x1, y1)
            // and Whim(x3, y3) were the endpoints of the line on which Chaser(x2, y2) was 
            // the midpoint
        } else if (this.getName().equals("Whim")) {
            if (app.waka.getXVel() > 0) {
                this.targetX = 2 * app.waka.CentreX() - app.chaser.CentreX() + 64;
                this.targetY = 2 * app.waka.CentreY() - app.chaser.CentreY();
            } else if (app.waka.getXVel() < 0) {
                this.targetX = 2 * app.waka.CentreX() - app.chaser.CentreX() - 64;
                this.targetY = 2 * app.waka.CentreY() - app.chaser.CentreY();
            } else if (app.waka.getYVel() > 0) {
                this.targetX = 2 * app.waka.CentreX() - app.chaser.CentreX();
                this.targetY = 2 * app.waka.CentreY() - app.chaser.CentreY() + 64;
            } else if (app.waka.getYVel() < 0) {
                this.targetX = 2 * app.waka.CentreX() - app.chaser.CentreX();
                this.targetY = 2 * app.waka.CentreY() - app.chaser.CentreY() - 64;
            }
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
