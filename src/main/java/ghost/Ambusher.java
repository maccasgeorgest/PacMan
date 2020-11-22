package ghost;

import processing.core.PImage;
/**
 * Represents the Ambusher ghost object. <br>
 * 
 * Sprite colour: Pink <br>
 * Scatter mode target: top right corner <br>
 * Chase mode target: 2 cells ahead of Waka's current trajectory
 * @author Ronen Bhaumik
 */
public class Ambusher extends Ghost {
    /**
     * Initialises a new Ambusher object
     */
    public Ambusher(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Ambusher";
        this.normalSprite = "src/main/resources/ambusher.png";
    }

    @Override
    public void setTarget(GameEvent gameEvent, boolean mode) {
        if (mode) { // Scatter mode = top right corner
            this.targetX = 448; 
            this.targetY = 0;
        } else {
            if (gameEvent.waka.getXVel() > 0) { // Targets square two in front of trajectory
                this.targetX = gameEvent.waka.CentreX() + 64;
                this.targetY = gameEvent.waka.CentreY();
            } else if (gameEvent.waka.getXVel() < 0) {
                        this.targetX = gameEvent.waka.CentreX() - 64;
                        this.targetY = gameEvent.waka.CentreY();
            } else if (gameEvent.waka.getYVel() > 0) {
                        this.targetX = gameEvent.waka.CentreX();
                        this.targetY = gameEvent.waka.CentreY() + 64;
            } else if (gameEvent.waka.getYVel() < 0) {
                        this.targetX = gameEvent.waka.CentreX();
                        this.targetY = gameEvent.waka.CentreY() - 64;
            }
        }
    }
}
