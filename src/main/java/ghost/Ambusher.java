package ghost;

import processing.core.PImage;

public class Ambusher extends Ghost {
    public Ambusher(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Ambusher";
        this.normalSprite = "src/main/resources/ambusher.png";
    }

    @Override
    public void setTarget(App app, boolean mode) {
        if (mode) { // Scatter mode = top right corner
            this.targetX = 448; 
            this.targetY = 0;
        } else {
            if (app.waka.getXVel() > 0) {
                this.targetX = app.waka.CentreX() + 64;
                this.targetY = app.waka.CentreY();
            } else if (app.waka.getXVel() < 0) {
                        this.targetX = app.waka.CentreX() - 64;
                        this.targetY = app.waka.CentreY();
            } else if (app.waka.getYVel() > 0) {
                        this.targetX = app.waka.CentreX();
                        this.targetY = app.waka.CentreY() + 64;
            } else if (app.waka.getYVel() < 0) {
                        this.targetX = app.waka.CentreX();
                        this.targetY = app.waka.CentreY() - 64;
            }
        }
    }
}
