package ghost;

public class CollisionGauge {

    public static boolean checkCollision(MovableCharacter character, GameCell collideWith) {
        if (character.Right() + character.getXVel() > collideWith.Left() && character.Left() 
            + character.getXVel() < collideWith.Right() && character.Bottom() > collideWith.Top() &&
            character.Top() < collideWith.Bottom()) {
                return true;
        }
        if (character.Right() > collideWith.Left() && character.Left() < collideWith.Right() &&
            character.Bottom() + character.getYVel() > collideWith.Top() && character.Top() 
            + character.getYVel() < collideWith.Bottom()) {
                return true;
        }

        return false;
    }
}
