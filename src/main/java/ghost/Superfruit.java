package ghost;

import processing.core.PImage;
/**
 * Represents the Superfruit object. <br>
 * 
 * Upon consumption, Waka becomes invincible and can temporarily "kill" ghosts <br>
 * Ghosts are placed in "frightened" mode and make randomised movement <br>
 * If Waka is later killed, all dead ghosts are resurrected
 * @author Ronen Bhaumik
 */
public class Superfruit extends Fruit {
    /**
     * Initialises a new Superfruit object
     */
    public Superfruit(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Superfruit";
    }
}