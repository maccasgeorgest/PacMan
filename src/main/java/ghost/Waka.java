package ghost;

import processing.core.PImage;

public class Waka extends GameCell {

    private int yVel;
    private int xVel;
    private int changeSprite = 1;  // modulo 0 is undefined
    private PImage lastSprite;

    public Waka(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.yVel = 0;
        this.xVel = 0; 
    }

    public void tick(App app) { 
        this.setCellCoord();
        this.y += yVel;
        this.x += xVel;
        lastSprite = app.loadImage("src/main/resources/playerLeft.png"); // since Waka starts facing left
        this.spriteTransition(app);

        if (this.changeSprite < 16) {
            this.changeSprite++;
        } else {
            this.changeSprite = 0;
        }
    }

    // Controls Waka movement by changing direction of velocity
    public void move(String command, App app) {
        if (command.equals("up")) {
            yVel = -1 * (app.speed);
            xVel = 0;
        } else if (command.equals("down")) {
            yVel = app.speed;
            xVel = 0;
        } else if (command.equals("left")) {
            xVel = -1 * (app.speed);
            yVel = 0;
        } else if (command.equals("right")) {
            xVel = app.speed;
            yVel = 0;
        }
    }

    public void spriteTransition(App app) {
        if (this.changeSprite > 8) {
            this.sprite = app.loadImage("src/main/resources/playerClosed.png");
            return;
        }
        if (xVel > 0) {
            this.sprite = app.loadImage("src/main/resources/playerRight.png");
            this.lastSprite = app.loadImage("src/main/resources/playerRight.png");
        } else if (xVel < 0) {
            this.sprite = app.loadImage("src/main/resources/playerLeft.png");
            this.lastSprite = app.loadImage("src/main/resources/playerLeft.png");
        } else if (yVel > 0) {
            this.sprite = app.loadImage("src/main/resources/playerDown.png");
            this.lastSprite = app.loadImage("src/main/resources/playerDown.png");
        } else if (yVel < 0) {
            this.sprite = app.loadImage("src/main/resources/playerUp.png");
            this.lastSprite = app.loadImage("src/main/resources/playerUp.png");
        } else if (xVel == 0 && yVel == 0) {
            this.sprite = lastSprite;
        }
    }
}
