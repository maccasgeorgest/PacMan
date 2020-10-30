package ghost;

import processing.core.PImage;

public class Fruit extends GameCell {

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
        if (waka.x == this.x && waka.y == this.y) {
            System.out.println("Eaten!");
            return true;
        }
        return false;
    }
}
