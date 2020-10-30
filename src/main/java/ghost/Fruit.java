package ghost;

import processing.core.PImage;

public class Fruit extends GameCell {

    private boolean uneaten = true;

    public Fruit(PImage sprite, int x, int y) {
        super(sprite, x, y);
    }

    public void tick(App app) {
        if (this.isEaten(app.waka)) {
            this.sprite = new PImage();
        } 
    }

    // Determines if fruit object has been consumed by Waka
    public boolean isEaten(Waka waka) {
        if (this.uneaten) {
        }
        return true;
    }
}
