package ghost;

import processing.core.PImage;
/**
 * Represents the Ignorant ghost object. <br>
 * 
 * Sprite colour: Orange <br>
 * Scatter mode target: bottom left corner <br>
 * Chase mode target: Waka's position unless within 8 cells of Waka <br>
 * @author Ronen Bhaumik
 */
public class Ignorant extends Ghost {
    /**
     * Initialises a new Ignorant object
     */
    public Ignorant(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Ignorant";
        this.normalSprite = "src/main/resources/ignorant.png";
    }

    @Override
    public void setTarget(GameEvent gameEvent, boolean mode) {
        if (mode) { // Scatter mode = bottom left corner
            this.targetX = 0;
            this.targetY = 576;
        } else {
            double distance = Math.sqrt(Math.pow((this.CentreX() - gameEvent.waka.CentreX()), 2) + Math.pow((this.CentreY() - gameEvent.waka.CentreY()), 2));
            if (distance > 128) {
                this.targetX = gameEvent.waka.CentreX();
                this.targetY = gameEvent.waka.CentreY();
            } else {
                this.targetX = 0;
                this.targetY = 576;
            }
        }
    }
}
