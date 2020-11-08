package ghost;

public class CollisionGauge {
    public static boolean collision(MovableCharacter character, GameCell gamecell) {
        // Character heading up
        if (character.getXVel() == 0 && character.getYVel() < 0) {
            if (character.Left() < gamecell.Right() && character.Right() > gamecell.Left()
                && character.Top() + character.getYVel() < gamecell.Bottom() && character.Bottom() + character.getYVel() > gamecell.Top()) {
                if (gamecell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gamecell.getName().equals("Superfruit")) {
                    character.makeInvincible();
                }
                return true;
            }
        // Character heading down
        } else if (character.getXVel() == 0 && character.getYVel() > 0) {
            if (character.Left() < gamecell.Right() && character.Right() > gamecell.Left()
                && character.Top() + character.getYVel() < gamecell.Bottom() && character.Bottom() + character.getYVel() > gamecell.Top()) {
                if (gamecell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gamecell.getName().equals("Superfruit")) {
                    character.makeInvincible();
                }
                return true;
            }
        // Character heading left
        } else if (character.getXVel() < 0 && character.getYVel() == 0) {
            if (character.Left() + character.getXVel() < gamecell.Right() && character.Right() + character.getXVel() > gamecell.Left()
                && character.Top() < gamecell.Bottom() && character.Bottom() > gamecell.Top()) {
                if (gamecell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gamecell.getName().equals("Superfruit")) {
                    character.makeInvincible();
                }
                return true;
            }
        // Character heading right
        } else if (character.getXVel() > 0 && character.getYVel() == 0) {
            if (character.Left() + character.getXVel() < gamecell.Right() && character.Right() + character.getXVel() > gamecell.Left()
                && character.Top() < gamecell.Bottom() && character.Bottom() > gamecell.Top()) {
                if (gamecell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gamecell.getName().equals("Superfruit")) {
                    character.makeInvincible();
                }
                return true;
            }
        }    
        character.skipMovement = false;
        return false;
    }
}
