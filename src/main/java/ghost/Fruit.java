package ghost;

import processing.core.PImage;

public class Fruit extends GameCell {

    protected boolean uneaten = true;
    private boolean accounted = false;

    public Fruit(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Fruit";
    }

    public void tick(GameEvent gameEvent) {
        this.isEaten(gameEvent.waka); 
        if (!this.uneaten) {      // if it is has been eaten, disable it's sprite
            this.sprite = new PImage();
            if (!this.accounted) {
                if (!this.name.equals("Soda")) { // since Waka doesn't have to eat all soda to win the game
                    gameEvent.fruitCount--;
                    this.accounted = true;
                }
            }
        }
    }

    /** Determines if fruit object has been consumed by Waka */
    public void isEaten(Waka waka) {
        if (this.uneaten) { 
            boolean collision = CollisionGauge.collision(waka, this);
            if (collision) {
                this.uneaten = false;
            }
        }
    }
}


