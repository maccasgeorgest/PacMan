package ghost;

import processing.core.PImage;
/**
 * Represents the game's protagonist, Waka. <br>
 * 
 * Waka is user-controlled via keyboard input, who must guide Waka safely through the maze to consume all fruit and avoid Ghosts <br>
 * The player wins if Waka can consume all fruit, and loses if Waka doesn't have remaining lives
 * @author Ronen Bhaumik
 */
public class Waka extends MovableCharacter {
    /** Counter to determine whe Waka should alternate between open and closed sprite  */
    private int changeSprite = 0; 
    /** Hosts Waka's last sprite */ 
    private PImage lastSprite;
    /**
     * Initialises a new Waka object
     */
    public Waka(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.yVel = 0;
        this.xVel = 0; 
        this.name = "Waka";
    }

    public void tick(GameEvent gameEvent) { 
        this.setCellCoord();
        boolean possible = CollisionGauge.intersectionDetector(gameEvent, this, this.moveAttempt);
            if (possible) {
                gameEvent.waka.move(this.moveAttempt, gameEvent);
            } 
        this.moveAfterCollision(gameEvent);

        if (!this.skipMovement) {
            this.y += this.yVel;
            this.x += this.xVel;
        }

        PImage livesSprite = gameEvent.app.loadImage("src/main/resources/playerRight.png"); // this is the sprite used to indicate the amount of remaining waka lives 

        // draw lives 
        int space = 29;
        for (int i = 0; i < gameEvent.lives; i ++) {
            gameEvent.app.image(livesSprite, i * space + 9, 543);
        }
        // change sprite every 8 frames
        this.spriteTransition(gameEvent);
        if (this.changeSprite < 16) {
            this.changeSprite++;
        } else {
            this.changeSprite = 0;  
        }
    }
    /**
     * Changes Waka sprite depending on Waka conditions. Every 8 frames, Waka oscillates between
     * his closed form and a direction form. Waka's direction form conforms to his game direction. 
     * If Waka is stationary, his last used sprite is used.
     * @param gameEvent GameEvent object that is hosting the game
     */
    public void spriteTransition(GameEvent gameEvent) {
        if (this.changeSprite > 8) {
            this.sprite = gameEvent.app.loadImage("src/main/resources/playerClosed.png");
            return;
        }
        if (this.direction.equals("right")) {
            this.sprite = gameEvent.app.loadImage("src/main/resources/playerRight.png");
        } else if (this.direction.equals("left")) {
            this.sprite = gameEvent.app.loadImage("src/main/resources/playerLeft.png");
        } else if (this.direction.equals("down")) {
            this.sprite = gameEvent.app.loadImage("src/main/resources/playerDown.png");
        } else if (this.direction.equals("up")) {
            this.sprite = gameEvent.app.loadImage("src/main/resources/playerUp.png");
        } else if (this.xVel == 0 && this.yVel == 0) {
            this.sprite = this.lastSprite;
        }
        this.lastSprite = this.sprite;
    } 
    /**
     * User keyboard input is interpreted to move Waka
     * @param move move from keyboard input
     */
    public void moveHandler(int move) {
            if (move == 38) {
                this.moveAttempt = "up";
            } else if (move == 40) {
                this.moveAttempt = "down";
            } else if (move == 37) {
                this.moveAttempt = "left";
            } else if (move == 39) {
                this.moveAttempt = "right";
            }
    }
}
