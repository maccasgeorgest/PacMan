package ghost;

import java.util.Random;

import processing.core.PImage;

public class Ghost extends MovableCharacter {

    private boolean frightened = false;
    private boolean scatter = false;
    private final String[] possibleMoves = {"up", "down", "left", "right"};

    public Ghost(PImage sprite, int x, int y) {
        super(sprite, x, y);
    }
    public void tick(App app) {
        if (app.waka.isInvincible()) {
            this.frighten(true);
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
    }

    public void frighten(boolean fright) {
        this.frightened = fright;
    }

    public void switchMode() {
        if (this.scatter) {
            this.scatter = false;
        } else {
            this.scatter = true;
        }
    }
}
