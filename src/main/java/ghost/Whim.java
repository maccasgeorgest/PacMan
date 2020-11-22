package ghost;

import processing.core.PImage;
/**
 * Represents the Whim ghost object. <br>
 * 
 * Sprite colour: Blue <br>
 * Scatter mode target: bottom right corner <br>
 * Chase mode target: double the vector from Chaser to Waka's trajectory plus two cells <br>
 * @author Ronen Bhaumik
 */
public class Whim extends Ghost {
    /**
     * Initialises a new Whim object
     */
    public Whim(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Whim";
        this.normalSprite = "src/main/resources/whim.png";
    }

    @Override
    // the formula here was derived from the midpoint formula where Chaser's position (x1, y1) plus
    // and Whim(x3, y3) were the endpoints of the line on which Waka's position(x2, y2) plus two 
    // cells was the midpoint
    public void setTarget(GameEvent gameEvent, boolean mode) {
        if (mode) { // Scatter mode = bottom right corner
            this.targetX = 448;
            this.targetY = 576;
        } else {
            if (gameEvent.chaser == null || gameEvent.chaser.isDead()) {
                this.targetX = gameEvent.waka.CentreX();
                this.targetY = gameEvent.waka.CentreY();
            } else {
                if (gameEvent.waka.getXVel() > 0) {
                    this.targetX = 2 * gameEvent.waka.CentreX() - gameEvent.chaser.CentreX() + 64;
                    this.targetY = 2 * gameEvent.waka.CentreY() - gameEvent.chaser.CentreY();
                } else if (gameEvent.waka.getXVel() < 0) {
                    this.targetX = 2 * gameEvent.waka.CentreX() - gameEvent.chaser.CentreX() - 64;
                    this.targetY = 2 * gameEvent.waka.CentreY() - gameEvent.chaser.CentreY();
                } else if (gameEvent.waka.getYVel() > 0) {
                    this.targetX = 2 * gameEvent.waka.CentreX() - gameEvent.chaser.CentreX();
                    this.targetY = 2 * gameEvent.waka.CentreY() - gameEvent.chaser.CentreY() + 64;
                } else if (gameEvent.waka.getYVel() < 0) {
                    this.targetX = 2 * gameEvent.waka.CentreX() - gameEvent.chaser.CentreX();
                    this.targetY = 2 * gameEvent.waka.CentreY() - gameEvent.chaser.CentreY() - 64;
                }
            }
        }
    }
}
