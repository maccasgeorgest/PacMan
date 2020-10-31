package ghost;

import processing.core.PImage;

public class Fruit extends GameCell {

    private boolean uneaten = true;
    private boolean accounted = false;

    public Fruit(PImage sprite, int x, int y) {
        super(sprite, x, y);
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
            // TOP
            if (waka.getCellCoord()[3] < this.getCellCoord()[3] && waka.getCellCoord()[3] > this.getCellCoord()[1]
                && waka.getCellCoord()[0] >= this.getCellCoord()[0] && waka.getCellCoord()[2] <= this.getCellCoord()[2]) {
                this.uneaten = false;
            }
            // BOTTOM
            else if (waka.getCellCoord()[1] < this.getCellCoord()[3] && waka.getCellCoord()[1] > this.getCellCoord()[1]
                && waka.getCellCoord()[0] >= this.getCellCoord()[0] && waka.getCellCoord()[2] <= this.getCellCoord()[2]) {
                this.uneaten = false;
            }
            // LEFT
            else if (waka.getCellCoord()[2] < this.getCellCoord()[2] && waka.getCellCoord()[2] > this.getCellCoord()[0]
                && waka.getCellCoord()[1] >= this.getCellCoord()[1] && waka.getCellCoord()[3] <= this.getCellCoord()[3]) {
                this.uneaten = false;
            }
            // RIGHT
            else if (waka.getCellCoord()[0] < this.getCellCoord()[2] && waka.getCellCoord()[0] > this.getCellCoord()[0]
                && waka.getCellCoord()[1] >= this.getCellCoord()[1] && waka.getCellCoord()[3] <= this.getCellCoord()[3]) {
                this.uneaten = false;
            }
        }
    }
}

