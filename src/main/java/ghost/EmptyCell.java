package ghost;

import processing.core.PImage;

public class EmptyCell extends GameCell {
    public EmptyCell(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.name = "Empty";
    }

    public void tick(GameEvent gameEvent) {}
}
