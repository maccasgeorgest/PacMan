package ghost;


// check if you actually need character.getXVel() == 0 etc for each of them


public class CollisionGauge {
    public static boolean collision(MovableCharacter character, GameCell gamecell) {
        // Character heading up
        if (character.getYVel() < 0) {
            if (character.Left() < gamecell.Right() && character.Right() > gamecell.Left()
                && character.Top() + character.getYVel() < gamecell.Bottom() && character.Bottom() + character.getYVel() > gamecell.Top()) {
                if (gamecell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gamecell.getName().equals("Superfruit")) {
                    character.changeVulnerability(true);
                }
                return true;
            }
        // Character heading down
        } else if (character.getYVel() > 0) {
            if (character.Left() < gamecell.Right() && character.Right() > gamecell.Left()
                && character.Top() + character.getYVel() < gamecell.Bottom() && character.Bottom() + character.getYVel() > gamecell.Top()) {
                if (gamecell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gamecell.getName().equals("Superfruit")) {
                    character.changeVulnerability(true);
                }
                return true;
            }
        // Character heading left
        } else if (character.getYVel() == 0) {
            if (character.Left() + character.getXVel() < gamecell.Right() && character.Right() + character.getXVel() > gamecell.Left()
                && character.Top() < gamecell.Bottom() && character.Bottom() > gamecell.Top()) {
                if (gamecell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gamecell.getName().equals("Superfruit")) {
                    character.changeVulnerability(true);
                }
                return true;
            }
        // Character heading right
        } else if (character.getYVel() == 0) {
            if (character.Left() + character.getXVel() < gamecell.Right() && character.Right() + character.getXVel() > gamecell.Left()
                && character.Top() < gamecell.Bottom() && character.Bottom() > gamecell.Top()) {
                if (gamecell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gamecell.getName().equals("Superfruit")) {
                    character.changeVulnerability(true);
                }
                return true;
            }
        }    
        character.skipMovement = false;
        return false;
    }
}
