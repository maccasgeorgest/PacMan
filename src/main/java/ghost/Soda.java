package ghost;

import processing.core.PImage;

public class Soda extends Fruit { // although Soda isn't strictly a type of fruit, it uses the same game logic

    public Soda(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Soda";
    }
}