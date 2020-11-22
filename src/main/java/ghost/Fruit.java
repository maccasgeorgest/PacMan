package ghost;

import processing.core.PImage;
/**
 * Represents the Fruit object <br>
 * 
 * Fruit can be consumed by Waka, upon which, disappears from the Game Map <br>
 * In order to win, Waka must consume all fruit
 * @author Ronen Bhaumik
 */
public class Fruit extends GameCell {
    /** Contains the state of a fruit's consumption */
    protected boolean unconsumed = true;
    /** This boolean prevents the same fruit from lowering the fruitcount multiple times  */
    protected boolean accounted; // 
    /**
     * Initialises a new Fruit object
     */
    public Fruit(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Fruit";
    }

    public void tick(GameEvent gameEvent) {
        this.isConsumed(gameEvent.waka); 
        if (!this.unconsumed) {      // if it is has been eaten, disable it's sprite
            this.sprite = new PImage();
            if (!this.accounted) {
                if (!this.name.equals("Soda")) { // since Waka doesn't have to eat all soda to win the game
                    gameEvent.fruitCount--;
                    this.accounted = true;
                }
            }
        }
    }

    /** Determines if fruit object has been consumed by Waka 
     * @param waka game's waka
     */
    public void isConsumed(Waka waka) {
        if (this.unconsumed) { 
            boolean collision = CollisionGauge.collision(waka, this);
            if (collision) {
                this.unconsumed = false;
            }
        }
    }
}


