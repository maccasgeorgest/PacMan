package ghost;

import processing.core.PImage;
/**
 * Represents the Wall object. <br>
 * 
 * Walls are impenetrable, and blocks both Waka and Ghosts from moving through <br>
 * Different wall shapes exist in order to make complete mazes
 * @author Ronen Bhaumik
 */
public class Wall extends GameCell {
    /**
     * Initialises a new Wall object
     */
    public Wall(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Wall";
    }

    public void tick(GameEvent gameEvent) {}
}
