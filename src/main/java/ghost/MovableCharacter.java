package ghost;

import processing.core.PImage;

public class MovableCharacter extends GameCell {

    protected int yVel;
    protected int xVel;    
    protected int initialX;
    protected int initialY;
    protected String direction = "left";
    protected String moveAttempt = "left";
    protected boolean invincible;
    protected boolean drankSoda;
    protected boolean skipMovement;

    public MovableCharacter(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.initialX = x;
        this.initialY = y;
    }
    
    public void tick(GameEvent gameEvent) {}

    /**
     * Controls character movement by changing direction of velocity, provided
     * a change in direction would not result in a collision 
     */ 
    public void move(String command, GameEvent gameEvent) {
        boolean check = CollisionGauge.turnCheck(gameEvent, this, command);
        if (command.equals("up")) {
            if (check) {
                this.direction = "up";
                this.yVel = -1 * (gameEvent.speed);
                this.xVel = 0;
            }
        } else if (command.equals("down")) {
            if (check) {
                this.direction = "down";
                this.yVel = gameEvent.speed;
                this.xVel = 0;
            }
        } else if (command.equals("left")) {
            if (check) {
                this.direction = "left";
                this.xVel = -1 * (gameEvent.speed);
                this.yVel = 0;
            }
        } else if (command.equals("right")) {
            if (check) {
                this.direction = "right";
                this.xVel = gameEvent.speed;
                this.yVel = 0;
            }
        }
    }
    /**
     * Allows character movement as long as there is no collision
     */
    public void moveAfterCollision(GameEvent gameEvent) {
        for (Wall wall : gameEvent.wallList) {
            boolean collision = CollisionGauge.collision(this, wall);
            if (collision) {
                break; // this break is used to prevent other walls from returning true
            }
        }
    }
    /**
     * Changes character vulnerability
     */
    public void changeVulnerability(boolean invincible) {
        this.invincible = invincible;
    }
    /**
     * Returns character XVel
     */
    public int getXVel() {
        return this.xVel;
    }
    /**
     * Returns character YVel
     */
    public int getYVel() {
        return this.yVel;
    }

    /**
     * Returns character vulnerability
     */
    public boolean isInvincible() {
        return this.invincible;
    }

    /**
     * The space key activates debug mode
     */
    public void initiateDebugMode(GameEvent gameEvent) {
        if (gameEvent.debugMode) {
            gameEvent.debugMode = false;
        } else {
            gameEvent.debugMode = true;
        }
    }
    /**
     * Resets character settings to initial game condition 
     */
    public void reset() {
        this.x = this.initialX;
        this.xVel = 0;
        this.y = this.initialY;
        this.yVel = 0;
        this.moveAttempt = "left";
    }
    /**
     * Sets Waka's drunk condition
     */
    public void sodaEffect(boolean effect) {
        this.drankSoda = effect;
    }
    /**
     * Returns Waka's drunk condition
     */
    public boolean drunk() {
        return this.drankSoda;
    }
}
