package ghost;

import processing.core.PImage;

public class Whim extends Ghost {
    public Whim(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Whim";
    }

    @Override
    // the formula here was derived from the midpoint formula where Waka(x1, y1)
    // and Whim(x3, y3) were the endpoints of the line on which Chaser(x2, y2) was 
    // the midpoint
    public void setTarget(App app) {
        if (app.chaser.isDead()) {
            this.targetX = app.waka.CentreX();
            this.targetY = app.waka.CentreY();
        } else {
            if (app.waka.getXVel() > 0) {
                this.targetX = 2 * app.waka.CentreX() - app.chaser.CentreX() + 64;
                this.targetY = 2 * app.waka.CentreY() - app.chaser.CentreY();
            } else if (app.waka.getXVel() < 0) {
                this.targetX = 2 * app.waka.CentreX() - app.chaser.CentreX() - 64;
                this.targetY = 2 * app.waka.CentreY() - app.chaser.CentreY();
            } else if (app.waka.getYVel() > 0) {
                this.targetX = 2 * app.waka.CentreX() - app.chaser.CentreX();
                this.targetY = 2 * app.waka.CentreY() - app.chaser.CentreY() + 64;
            } else if (app.waka.getYVel() < 0) {
                this.targetX = 2 * app.waka.CentreX() - app.chaser.CentreX();
                this.targetY = 2 * app.waka.CentreY() - app.chaser.CentreY() - 64;
            }
        }
    }
}
