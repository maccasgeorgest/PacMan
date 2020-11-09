package ghost;

import processing.core.PImage;

public class Chaser extends Ghost {
    public Chaser(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Chaser";
    }

    @Override
    public void setTarget(App app) {
        this.targetX = app.waka.CentreX();
        this.targetY = app.waka.CentreY();
    }
}
