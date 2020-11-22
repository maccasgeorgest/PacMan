package ghost;

import processing.core.PImage;
/**
 * Represents the Soda object <br>
 * 
 * Upon consumption by Waka, ghosts are placed in a "frightened" mode <br>
 * Ghosts have randomised movement and maintain a "wavy" effect, however they can still kill Waka upon contact
 * @author Ronen Bhaumik
 */
public class Soda extends Fruit { // although Soda isn't strictly a type of fruit, it uses the same game logic
    /**
     * Initialises a new Soda object
     */
    public Soda(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Soda";
    }
}