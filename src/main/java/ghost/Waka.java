package ghost;

import processing.core.PImage;

public class Waka extends MovableCharacter {

    private int changeSprite = 1;  // modulo 0 is undefined
    private boolean invincible = false;
    private PImage lastSprite;
    private PImage liveSprite;

    public Waka(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.yVel = 0;
        this.xVel = 0; 
        this.name = "Waka";
    }

    public void tick(App app) { 
        this.setCellCoord();

        // // Character heading up
        // if (character.getXVel() == 0 && character.getYVel() < 0) {
        //     if (character.Left() < collideWith.Right() && character.Right() > collideWith.Left()
        //         && character.Top() + character.getYVel() < collideWith.Bottom() && character.Bottom() + character.getYVel() > collideWith.Top()) {
        //             return true;
        //     }
        // // Character heading down
        // } else if (character.getXVel() == 0 && character.getYVel() > 0) {
        //     if (character.Left() < collideWith.Right() && character.Right() > collideWith.Left()
        //         && character.Top() + character.getYVel() < collideWith.Bottom() && character.Bottom() + character.getYVel() > collideWith.Top()) {
        //             return true;
        //     }
        // // Character heading left
        // } else if (character.getXVel() < 0 && character.getYVel() == 0) {
        //     if (character.Left() + character.getXVel() < collideWith.Right() && character.Right() + character.getXVel() > collideWith.Left()
        //         && character.Top() < collideWith.Bottom() && character.Bottom() > collideWith.Top()) {
        //             return true;
        //     }
        // // Character heading right
        // } else if (character.getXVel() > 0 && character.getYVel() == 0) {
        //     if (character.Left() + character.getXVel() < collideWith.Right() && character.Right() + character.getXVel() > collideWith.Left()
        //         && character.Top() < collideWith.Bottom() && character.Bottom() > collideWith.Top()) {
        //             return true;
        //     }
        // }    
        // return false;
        



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

    // Controls Waka movement by changing direction of velocity
    public void move(String command, App app) {
        if (command.equals("up")) {
            this.yVel = -1 * (app.speed);
            this.xVel = 0;
        } else if (command.equals("down")) {
            this.yVel = app.speed;
            this.xVel = 0;
        } else if (command.equals("left")) {
            this.xVel = -1 * (app.speed);
            this.yVel = 0;
        } else if (command.equals("right")) {
            this.xVel = app.speed;
            this.yVel = 0;
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

    public boolean isInvincible() {
        return this.invincible;
    }

    public void setXVel(int xVel) {
        this.xVel = xVel;
    }
    public void setYVel(int yVel) {
        this.yVel = yVel;
    }
}
