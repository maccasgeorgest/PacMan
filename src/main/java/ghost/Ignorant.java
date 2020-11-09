package ghost;

import processing.core.PImage;

public class Ignorant extends Ghost {
    public Ignorant(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Ignorant";
    }

    @Override
    public void setTarget(App app) {
        double distance = Math.sqrt(Math.pow((this.CentreX() - app.waka.CentreX()), 2) + Math.pow((this.CentreY() - app.waka.CentreY()), 2));
        if (distance < 128) {
            this.targetX = app.waka.CentreX();
            this.targetY = app.waka.CentreY();
        } else {
            this.targetX = 40;
            this.targetY = 500;
        }
    }
}
