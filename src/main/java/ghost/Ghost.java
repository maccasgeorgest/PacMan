package ghost;

import java.util.Random;
import processing.core.PImage;
/**
 * Represents the game's antagonists, the Ghosts <br>
 * 
 * Ghosts are programmed to hunt down Waka in specific patterns depending on type <br>
 * Upon contact they kill Waka, but become vulnerable when Waka has consumed a Superfruit <br>
 * Have two modes: Scatter (target a specific corner of the map) and Chase (target cells based on Waka's position)
 * @author Ronen Bhaumik
 */
public abstract class Ghost extends MovableCharacter {
    /** Ghost's target horizontally */
    protected int targetX;
    /** Ghost's target vertically */
    protected int targetY;
    /** Ghost's distance to target horizontally */
    protected int distanceX;
    /** Ghost's distance to target vertically */
    protected int distanceY;
    /** Ghost's state of death */
    protected boolean dead;
    /** Ghost's normal coloured sprite */
    protected String normalSprite;
    /** Ghost's state of being frightened or not */
    protected boolean frightened;
    /** Ghost's timer to determine when to stop being frightened */
    protected int invisibleCounter = 0;
    /** Ghost's timer to determine when to switch mode */
    protected int modeShiftCounter = 0;
    /** Ghost's timeframe for mode */
    protected int modeInterval = 0;
    /** Ghost's current mode: scatter or chase */
    protected boolean scatter;
    /**
     * Abstract constructor for classes inheriting from Ghost
     */
    public Ghost(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.invincible = true;
    }
    public void tick(GameEvent gameEvent) {
        if (gameEvent.waka.isInvincible()) {              // if waka has eaten superfruit
            this.frighten(true);
            this.changeVulnerability(false);
        } else {                                    // if waka has not eaten superfruit
            this.frighten(false);
            this.changeVulnerability(true);
        }
        if (this.frightened) {
            this.sprite = gameEvent.app.loadImage("src/main/resources/frightened.png");
            gameEvent.frightenedCounter++;
            int GhostMultiplier = gameEvent.ghostList.size(); // this is used to account for the fact that multiple ghosts are adding to any count per tick
            if (gameEvent.frightenedCounter == 60 * gameEvent.frightenedLength * GhostMultiplier) {
                this.frighten(false);
                gameEvent.waka.changeVulnerability(false);
                gameEvent.frightenedCounter = 0;
            } 
        } else {
            this.sprite = gameEvent.app.loadImage(this.normalSprite);
        }

        if (gameEvent.waka.drunk()) {
            this.invisibleCounter++;
            if (this.invisibleCounter % 10 == 0) { // alternating between invisible/normal to make a "wavy" effect
                this.sprite = gameEvent.app.loadImage(this.normalSprite); // ghosts are invisible to visible 9 frames to 1 to maximise the effect
            } else {
                this.sprite = new PImage();
            }
            if (this.invisibleCounter == 300) { // Ghosts invisible for 5 seconds
                gameEvent.waka.sodaEffect(false);     
                gameEvent.ghostList.forEach((ghost) -> ghost.invisibleCounter = 0);
            }
        }

        if (this.isDead()) {
            this.sprite = new PImage();
        }

        // Check collision with Waka
        boolean collision = CollisionGauge.collision(gameEvent.waka, this); 
        if (collision) {
            if (!this.isDead()) {
                if (this.isInvincible()) {
                    gameEvent.waka.reset();
                    gameEvent.lives--;
                    for (Ghost ghost : gameEvent.ghostList) {
                        ghost.reset();
                        ghost.die(false);
                    }
                } else {
                    this.die(true);
                }
            }
        }

        this.setCellCoord();
        this.setTarget(gameEvent, this.scatter);
        if (this.frightened || gameEvent.waka.drunk()) { // when frightened by Superfruit or Soda, Ghost movement should be random
            Random generator = new Random();
            this.targetX = generator.nextInt(449);
            this.targetY = generator.nextInt(577);
        }
        this.distanceX = this.CentreX() - this.targetX;
        this.distanceY = this.CentreY() - this.targetY;
        this.moveAttempt = this.ghostAI(this.distanceX, this.distanceY, gameEvent);
        this.move(this.moveAttempt, gameEvent);
        this.moveAfterCollision(gameEvent);

        if (!this.skipMovement) {
            this.y += this.yVel;
            this.x += this.xVel;
        }

        if (!this.frightened) {
            this.modeShiftCounter++;
        }
        int time = gameEvent.modeLengths.get(this.modeInterval); // time prescribed by config file
        if (this.modeShiftCounter == 60 * time) {
            this.switchMode();
            if (this.modeInterval == gameEvent.modeLengths.size() - 1) {
                this.modeInterval = -1; // if its the last element in the array, reset from beginning
            }
            this.modeInterval++;
            this.modeShiftCounter = 0;
        }
        
        if (gameEvent.debugMode) {
            if (!this.frightened && this.invisibleCounter == 0) {
                if (!this.isDead()) {
                    this.targetLine(gameEvent, this.targetX, this.targetY);
                }
            }
        }
    }
    /**
     * Sets Ghost target coordinates, depending on whether Ghosts are in Scatter or Chase mode
     * @param gameEvent GameEvent object that is hosting the game
     * @param mode scatter or chase mode
     */
    public abstract void setTarget(GameEvent gameEvent, boolean mode);
    /**
     * Logic for ghost movement in differing situations
     * @param distanceX ghost's horizontal distance 
     * @param distanceY ghost's veritcal distance
     * @param gameEvent GameEvent object that is hosting the game
     * @return direction after Ghost AI computes optimal move
     */
    public String ghostAI(int distanceX, int distanceY, GameEvent gameEvent) {
        boolean up = CollisionGauge.intersectionDetector(gameEvent, this, "up");
        boolean down = CollisionGauge.intersectionDetector(gameEvent, this, "down");
        boolean left = CollisionGauge.intersectionDetector(gameEvent, this, "left");
        boolean right = CollisionGauge.intersectionDetector(gameEvent, this, "right");
        if (this.direction.equals("up")) {
            if (left || right) { // if Ghost reaches an intersection
                if (this.distanceY > 0 && up) { // Go up if target is above and it is possible to go up
                    return "up";
                } else if (this.distanceX > 0 && left) { // Otherwise go left if target is to the left and it is possible to go left
                    return "left";
                } else if (right) { // Otherwise go right if possible
                    return "right";
                } else if (left) { // Otherwise go left if possible (even if target is to the right)
                    return "left";
                } else if (up) { // Otherwise continue, even if target is below 
                    return "up";
                }
            } else { // if Ghost isn't at an intersection, forge on!
                return this.direction;
            }
        } else if (this.direction.equals("down")) {
            if (left || right) {
                if (this.distanceY < 0 && down) {
                    return "down";
                } else if (this.distanceX > 0 && left) {
                    return "left";
                } else if (right) {
                    return "right";
                } else if (left) {
                    return "left";
                } else if (down) {
                    return "down";
                }
            } else {
                return this.direction;
            }
        } else if (this.direction.equals("left")) {
            if (up || down) {
                if (this.distanceX > 0 && left) {
                    return "left";
                } else if (this.distanceY > 0 && up) {
                    return "up";
                } else if (down) {
                    return "down";
                } else if (up) {
                    return "up";
                } else if (left) {
                    return "left";
                }
            } else {
                return this.direction;
            }
        } else {
            if (up || down) {
                if (this.distanceX < 0 && right) {
                    return "right";
                } else if (this.distanceY > 0 && up) {
                    return "up";
                } else if (down) {
                    return "down";
                } else if (up) {
                    return "up";
                } else if (right) {
                    return "right";
                }
            } else {
                return this.direction;
            }
        }
        return this.reverse();
    }
    /**
     * Changes ghost alive/death status
     * @param death whether ghost is dead or not
     */
    public void die(boolean death) {
        this.dead = death;
    }
    /**
     * @return ghost death status
     */
    public boolean isDead() {
        return this.dead;
    }
    /**
     * Changes ghost frigtenened/unfrightened status
     * @param fright whether ghost is frigthened or not
     */
    public void frighten(boolean fright) {
        this.frightened = fright;
    }
    /**
     * @return the reverse of Ghost's current direction
     */
    public String reverse() {
        if (this.direction.equals("up")) {
            return "down";
        } else if (this.direction.equals("down")) {
            return "up";
        } else if (this.direction.equals("left")) {
            return "right";
        } else {
            return "left";
        }  
    }

    /**
     * Creates line used for debugging based on ghost target coordinates
     * @param gameEvent GameEvent object that is hosting the game
     * @param targetX ghost's target horizontally
     * @param targetY ghost's target vetically
     */
    public void targetLine(GameEvent gameEvent, int targetX, int targetY) {
        gameEvent.app.stroke(255);
        gameEvent.app.line(this.CentreX(), this.CentreY(), targetX, targetY);
    }
    /**
     * Toggles between scatter and chase mode when gameEventropriate
     */
    public void switchMode() {
        if (this.scatter) {
            this.scatter = false;
        } else {
            this.scatter = true;
        }
    }
}
