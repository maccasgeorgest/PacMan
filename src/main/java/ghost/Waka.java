package ghost;

import processing.core.PImage;

public class Waka extends MovableCharacter {

    private int changeSprite = 0;  
    private PImage lastSprite;
    private PImage livesSprite;

    public Waka(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.yVel = 0;
        this.xVel = 0; 
        this.name = "Waka";
    }

    public void tick(App app) { 
        this.setCellCoord();
        this.moveAfterCollision(app);

        if (!this.skipMovement) {
            this.y += this.yVel;
            this.x += this.xVel;
        }
        
        lastSprite = app.loadImage("src/main/resources/playerLeft.png"); // since Waka starts facing left
        livesSprite = app.loadImage("src/main/resources/playerRight.png"); // this is the sprite used to indicate the amount of remaining waka lives 

        // draw lives 
        int space = 29;
        for (int i = 0; i < app.lives; i ++) {
            app.image(this.livesSprite, i * space + 9, 543);
        }

        this.spriteTransition(app);

        if (this.changeSprite < 16) {
            this.changeSprite++;
        } else {
            this.changeSprite = 0;  
        }
    }

    public void spriteTransition(App app) {
        if (this.changeSprite > 8) {
            this.sprite = app.loadImage("src/main/resources/playerClosed.png");
            return;
        }
        if (this.direction.equals("right")) {
            this.sprite = app.loadImage("src/main/resources/playerRight.png");
            this.lastSprite = app.loadImage("src/main/resources/playerRight.png");
        } else if (this.direction.equals("left")) {
            this.sprite = app.loadImage("src/main/resources/playerLeft.png");
            this.lastSprite = app.loadImage("src/main/resources/playerLeft.png");
        } else if (this.direction.equals("down")) {
            this.sprite = app.loadImage("src/main/resources/playerDown.png");
            this.lastSprite = app.loadImage("src/main/resources/playerDown.png");
        } else if (this.direction.equals("up")) {
            this.sprite = app.loadImage("src/main/resources/playerUp.png");
            this.lastSprite = app.loadImage("src/main/resources/playerUp.png");
        } else if (this.xVel == 0 && this.yVel == 0) {
            this.sprite = this.lastSprite;
        }
    } 

    public void moveHandler(App app, int move) {
            if (move == 38) {
                this.moveAttempt = "up";
                app.waka.move("up", app);
            } else if (move == 40) {
                this.moveAttempt = "down";
                app.waka.move("down", app);
            } else if (move == 37) {
                this.moveAttempt = "left";
                app.waka.move("left", app);
            } else if (move == 39) {
                this.moveAttempt = "right";
                app.waka.move("right", app);
            }
    }
}
