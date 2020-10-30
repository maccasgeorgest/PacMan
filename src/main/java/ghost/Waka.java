package ghost;

import processing.core.PImage;

public class Waka extends GameCell {

    private int yVel;
    private int xVel;
    private int changeSprite = 1;  // modulo 0 is undefined

    public Waka(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.yVel = 0;
        this.xVel = 0; 
    }

    public void tick(App app) { 
        this.y += yVel;
        this.x += xVel;

        this.spriteTransition(app);

        if (this.changeSprite < 16) {
            this.changeSprite++;
        } else {
            this.changeSprite = 1;
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
        if (this.changeSprite % 8 == 0) {
            this.sprite = app.loadImage("src/main/resources/playerClosed.png");
            return;
        }
    }
}
