package ghost;

import processing.core.PImage;

public class Ambusher extends Ghost {
    public Ambusher(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Ambusher";
        this.normalSprite = "src/main/resources/ambusher.png";
    }

    @Override
    public void setTarget(GameEvent gameEvent, boolean mode) {
        if (mode) { // Scatter mode = top right corner
            this.targetX = 448; 
            this.targetY = 0;
        } else {
            if (gameEvent.waka.getXVel() > 0) {
                this.targetX = gameEvent.waka.CentreX() + 64;
                this.targetY = gameEvent.waka.CentreY();
            } else if (gameEvent.waka.getXVel() < 0) {
                        this.targetX = gameEvent.waka.CentreX() - 64;
                        this.targetY = gameEvent.waka.CentreY();
            } else if (gameEvent.waka.getYVel() > 0) {
                        this.targetX = gameEvent.waka.CentreX();
                        this.targetY = gameEvent.waka.CentreY() + 64;
            } else if (gameEvent.waka.getYVel() < 0) {
                        this.targetX = gameEvent.waka.CentreX();
                        this.targetY = gameEvent.waka.CentreY() - 64;
            }
        }
    }
}
