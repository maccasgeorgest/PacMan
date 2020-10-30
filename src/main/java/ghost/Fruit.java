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
            app.fruitCount--;
        } 
    }

    // Determines if fruit object has been consumed by Waka
    public boolean isEaten(Waka waka) {
        if (this.uneaten) {
            if (waka.getCellCoord()[0] == this.getCellCoord()[0] && waka.getCellCoord()[1] == this.getCellCoord()[1]) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}

//   
// -----------------
// |                |
// |                |
// |                |
// |                |
// -----------------
