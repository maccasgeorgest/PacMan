package ghost;

import processing.core.PImage;
/**
 * Represents the Chaser ghost object. <br>
 * 
 * Sprite colour: Red <br>
 * Scatter mode target: top left corner <br>
 * Chase mode target: Waka's position <br>
 * @author Ronen Bhaumik
 */
public class Chaser extends Ghost {
    /**
     * Initialises a new Chaser object
     */
    public Chaser(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Chaser";
        this.normalSprite = "src/main/resources/chaser.png";
    }

    @Override
    public void setTarget(GameEvent gameEvent, boolean mode) {
        if (mode) { // Scatter mode = top left corner
            this.targetX = 0;
            this.targetY = 0;
        } else {
            this.targetX = gameEvent.waka.CentreX();
            this.targetY = gameEvent.waka.CentreY();
        }
    }
}
