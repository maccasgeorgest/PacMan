package ghost;

public class CollisionGauge {

    public static boolean checkCollision(MovableCharacter character, GameCell collideWith) {
        
        // Character heading up
        if (character.getXVel() == 0 && character.getYVel() < 0) {
            if (character.Left() < collideWith.Right() && character.Right() > collideWith.Left()
                && character.Top() + character.getYVel() < collideWith.Bottom() && character.Bottom() + character.getYVel() > collideWith.Top()) {
                    return true;
            }
        // Character heading down
        } else if (character.getXVel() == 0 && character.getYVel() > 0) {
            if (character.Left() < collideWith.Right() && character.Right() > collideWith.Left()
                && character.Top() + character.getYVel() < collideWith.Bottom() && character.Bottom() + character.getYVel() > collideWith.Top()) {
                    return true;
            }
        // Character heading left
        } else if (character.getXVel() < 0 && character.getYVel() == 0) {
            if (character.Left() + character.getXVel() < collideWith.Right() && character.Right() + character.getXVel() > collideWith.Left()
                && character.Top() < collideWith.Bottom() && character.Bottom() > collideWith.Top()) {
                    return true;
            }
        // Character heading right
        } else if (character.getXVel() > 0 && character.getYVel() == 0) {
            if (character.Left() + character.getXVel() < collideWith.Right() && character.Right() + character.getXVel() > collideWith.Left()
                && character.Top() < collideWith.Bottom() && character.Bottom() > collideWith.Top()) {
                    return true;
            }
        }    
        return false;
    }
}
