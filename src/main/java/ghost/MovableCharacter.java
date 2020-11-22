package ghost;

import processing.core.PImage;
/**
 * Acts as the blueprint for movable characters <br>
 * 
 * Hosts information such as velocity in both axes, as well as direction and attempted move <br>
 * Also contains information such as vulnerability, and the effects of Soda
 * @author Ronen Bhaumik
 */
public abstract class MovableCharacter extends GameCell {
    /** Character's vertical velocity */
    protected int yVel;
    /** Character's horizontal velocity */
    protected int xVel;   
    /** Character's initial x coordinate */ 
    protected int initialX;
    /** Character's initial y coordinate */
    protected int initialY;
    /** Character's direction of trajectory */
    protected String direction = "left";
    /** Character's attempted move */
    protected String moveAttempt = "left";
    /** Character's vulnerability */
    protected boolean invincible;
    /** Character's state due to soda effect */
    protected boolean drankSoda;
    /** Determines whether character should move or not */
    protected boolean skipMovement;
    /**
     * Abstract constructor for classes inheriting from MovableCharacter
     */
    public MovableCharacter(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.initialX = x;
        this.initialY = y;
    }
    
    public abstract void tick(GameEvent gameEvent);

    /**
     * Controls character movement by changing direction of velocity, provided
     * a change in direction would not result in a collision 
     * @param command move attempted to be made
     * @param gameEvent GameEvent object that is hosting the game
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
     * @param gameEvent GameEvent object that is hosting the game
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
     * @param invincible character's vulnerability state
     */
    public void changeVulnerability(boolean invincible) {
        this.invincible = invincible;
    }
    /**
     * @return character XVel
     */
    public int getXVel() {
        return this.xVel;
    }
    /**
     * @return character YVel
     */
    public int getYVel() {
        return this.yVel;
    }

    /**
     * @return character vulnerability
     */
    public boolean isInvincible() {
        return this.invincible;
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
     * @param effect whether the soda effect is on
     */
    public void sodaEffect(boolean effect) {
        this.drankSoda = effect;
    }
    /**
     * @return Waka's drunk condition
     */
    public boolean drunk() {
        return this.drankSoda;
    }
}
