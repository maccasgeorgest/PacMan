package ghost;

import processing.core.PImage;

public class Ghost extends MovableCharacter {

    private boolean frightened = false;

    public Ghost(PImage sprite, int x, int y) {
        super(sprite, x, y);
    }
    public void tick(App app) {
        if (this.frightened) {
            this.sprite = app.loadImage("src/main/resources/frightened.png");
        }
    }

    public void frighten() {
        this.frightened = true;
    }
}
