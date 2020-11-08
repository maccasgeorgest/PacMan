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
        if (!this.uneaten) {      // if it is has been eaten, disable it's sprite
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
            boolean collision = CollisionGauge.collision(waka, this);
            if (collision) {
                this.uneaten = false;
            }
        }
    }

    public boolean checkCollision(Waka waka) {
            
        // Character heading up
        if (waka.getXVel() == 0 && waka.getYVel() < 0) {
            if (waka.Left() < this.Right() && waka.Right() > this.Left()
                && waka.Top() + waka.getYVel() < this.Bottom() && waka.Bottom() + waka.getYVel() > this.Top()) {
                    return true;
            }
        // Character heading down
        } else if (waka.getXVel() == 0 && waka.getYVel() > 0) {
            if (waka.Left() < this.Right() && waka.Right() > this.Left()
                && waka.Top() + waka.getYVel() < this.Bottom() && waka.Bottom() + waka.getYVel() > this.Top()) {
                    return true;
            }
        // Character heading left
        } else if (waka.getXVel() < 0 && waka.getYVel() == 0) {
            if (waka.Left() + waka.getXVel() < this.Right() && waka.Right() + waka.getXVel() > this.Left()
                && waka.Top() < this.Bottom() && waka.Bottom() > this.Top()) {
                    return true;
            }
        // Character heading right
        } else if (waka.getXVel() > 0 && waka.getYVel() == 0) {
            if (waka.Left() + waka.getXVel() < this.Right() && waka.Right() + waka.getXVel() > this.Left()
                && waka.Top() < this.Bottom() && waka.Bottom() > this.Top()) {
                    return true;
            }
        }    
        return false;
    }
}


