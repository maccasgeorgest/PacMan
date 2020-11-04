package ghost;

public class CollisionGauge {

    public static boolean checkCollision(MovableCharacter character, Wall wall) {
        if (character.Right() + character.getXVel() > wall.Left() && character.Left() 
            + character.getXVel() < wall.Right() && character.Bottom() > wall.Top() &&
            character.Top() < wall.Bottom()) {
                return true;
        }
        if (character.Right() > wall.Left() && character.Left() < wall.Right() &&
            character.Bottom() + character.getYVel() > wall.Top() && character.Top() 
            + character.getYVel() < wall.Bottom()) {
                return true;
        }

        return false;
    }
}
