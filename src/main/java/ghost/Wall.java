package ghost;

import processing.core.PImage;

public class Wall extends GameCell {

    int value; // Used to determine type of wall
    boolean collided = false;

    public Wall(PImage sprite, int x, int y) {
        super(sprite, x, y);
    }

    public void tick(App app) {
        collided = CollisionGauge.checkCollision(app.waka, this);
        // if (collided) {
        //     app.waka.setXVel(0);
        //     app.waka.setYVel(0);
        // }
    }
}
