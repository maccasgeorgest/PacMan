package ghost;

import processing.core.PImage;

public class Chaser extends Ghost {
    public Chaser(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Chaser";
        this.normalSprite = "src/main/resources/chaser.png";
    }

    @Override
    public void setTarget(App app, boolean mode) {
        if (mode) { // Scatter mode = top left corner
            this.targetX = 0;
            this.targetY = 0;
        } else {
            this.targetX = app.waka.CentreX();
            this.targetY = app.waka.CentreY();
        }
    }
}
