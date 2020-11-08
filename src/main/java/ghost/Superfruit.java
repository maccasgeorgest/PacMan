package ghost;

import processing.core.PImage;

public class Superfruit extends Fruit {
    private boolean used = false;

    public Superfruit(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Superfruit";
    }

    public void eat() {
        this.used = true;
    }
}