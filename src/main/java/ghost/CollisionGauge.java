package ghost;

public class CollisionGauge {

    public static boolean checkCollision(GameCell character, Wall wall) {
        // TOP
        if (character.Bottom() < wall.Bottom() && character.Bottom() > wall.Top()
            && character.Left() >= wall.Left() && character.Right() <= wall.Right()) {
            return true;
        }
        // BOTTOM
        else if (character.Top() < wall.Bottom() && character.Top() > wall.Top()
            && character.Left() >= wall.Left() && character.Right() <= wall.Right()) {
            return true;
        }
        // LEFT
        else if (character.Right() < wall.Right() && character.Right() > wall.Left()
            && character.Top() >= wall.Top() && character.Bottom() <= wall.Bottom()) {
            return true;
        }
        // RIGHT
        else if (character.Left() < wall.Right() && character.Left() > wall.Left()
            && character.Top() >= wall.Top() && character.Bottom() <= wall.Bottom()) {
            return true;
        }
        return false;
    }
}
