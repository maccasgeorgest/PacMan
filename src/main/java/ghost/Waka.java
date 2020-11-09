package ghost;

import processing.core.PImage;

public class Waka extends MovableCharacter {

    private int changeSprite = 1;  // modulo 0 is undefined
    private PImage lastSprite;
    private PImage liveSprite;
    private String lastKeyPressed;

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
        liveSprite = app.loadImage("src/main/resources/playerRight.png");  

        // draw lives 
        int space = 29;
        for (int i = 0; i < app.lives; i ++) {
            app.image(this.liveSprite, i * space + 9, 543);
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
        if (this.xVel > 0) {
            this.sprite = app.loadImage("src/main/resources/playerRight.png");
            this.lastSprite = app.loadImage("src/main/resources/playerRight.png");
        } else if (this.xVel < 0) {
            this.sprite = app.loadImage("src/main/resources/playerLeft.png");
            this.lastSprite = app.loadImage("src/main/resources/playerLeft.png");
        } else if (this.yVel > 0) {
            this.sprite = app.loadImage("src/main/resources/playerDown.png");
            this.lastSprite = app.loadImage("src/main/resources/playerDown.png");
        } else if (this.yVel < 0) {
            this.sprite = app.loadImage("src/main/resources/playerUp.png");
            this.lastSprite = app.loadImage("src/main/resources/playerUp.png");
        } else if (this.xVel == 0 && this.yVel == 0) {
            this.sprite = this.lastSprite;
        }
    } 
}
