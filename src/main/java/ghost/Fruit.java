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
        if (this.uneaten == false) {
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
            // // HIT TOP
            // if (waka.Bottom() <= this.Bottom() && waka.Bottom() >= this.Top()
            //     && waka.Left() >= this.Left() && waka.Right() <= this.Right()) {
            //     this.uneaten = false;
            // }
            // // HITBOTTOM
            // else if (waka.Top() <= this.Bottom() && waka.Top() >= this.Top()
            //     && waka.Left() >= this.Left() && waka.Right() <= this.Right()) {
            //     this.uneaten = false;
            // }
            // // LEFT
            // else if (waka.Right() <= this.Right() && waka.Right() >= this.Left()
            //     && waka.Top() >= this.Top() && waka.Bottom() <= this.Bottom()) {
            //     this.uneaten = false;
            // }
            // // RIGHT
            // else if (waka.Left() <= this.Right() && waka.Left() >= this.Left()
            //     && waka.Top() >= this.Top() && waka.Bottom() <= this.Bottom()) {
            //     this.uneaten = false;
            // }


            // if (waka.Right() + waka.getXVel() > this.Left() && waka.Left() 
            //     + waka.getXVel() < this.Right() && waka.Bottom() > this.Top() &&
            //     waka.Top() < this.Bottom()) {
            //         this.uneaten = false;
            // }
            // if (waka.Right() > this.Left() && waka.Left() < this.Right() && waka.Bottom()
            //     + waka.getYVel() > this.Top() && waka.Top() + waka.getYVel() < this.Bottom()) {
            //         this.uneaten = false;
                
            // }
        }
    }
}


