package ghost;

import processing.core.PImage;

public class Fruit extends GameCell {

    protected boolean uneaten = true;
    private boolean accounted = false;

    public Fruit(PImage sprite, int x, int y) {
        super(sprite, x, y, false);
        this.name = "Fruit";
    }

    public void tick(App app) {
        this.isEaten(app.waka); 
        if (!this.uneaten) {
            this.sprite = new PImage();
            if (!this.accounted) {
                app.fruitCount--;
                this.accounted = true;
            }
        }
    }

    // Determines if fruit object has been consumed by Waka
    public void isEaten(Waka waka) {
        if (this.uneaten) { 
            boolean collision = CollisionGauge.checkCollision(waka, this);
            if (collision) {
                this.uneaten = false;
            }
        }
    }
}


