package ghost;

import processing.core.PImage;
/**
 * Represents empty spaces in the game window. <br>
 * 
 * This serves no functionality other than a potential pathway for Waka <br>
 * It can also act as a placeholder above and below the game map for game information
 * @author Ronen Bhaumik
 */
public class EmptyCell extends GameCell {
    /**
     * Initialises a new EmptyCell object
     */
    public EmptyCell(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Empty";
    }

    public void tick(GameEvent gameEvent) {}
}
