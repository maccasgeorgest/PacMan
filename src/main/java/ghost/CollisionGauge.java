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
        } else if (character.getXVel() < 0) {
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
        } else if (character.getXVel() > 0) {
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

    public static boolean turnCheck(App app, MovableCharacter character, String move) {
        for (Wall wall : app.wallList) {
            if (move.equals("up")) {
                if (character.CentreY() - 16 == wall.CentreY() && character.CentreX() >= wall.Left() && character.CentreX() <= wall.Right()) {
                    return false;
                }
            } else if (move.equals("down")) {
                if (character.CentreY() + 16 == wall.CentreY() && character.CentreX() >= wall.Left() && character.CentreX() <= wall.Right()) {
                    return false;
                }
            } else if (move.equals("left")) {
                if (character.CentreY() >= wall.Top() && character.CentreY() <= wall.Bottom() && character.CentreX() - 16 == wall.CentreX()) {
                    return false;
                }
            } else if (move.equals("right")) {
                if (character.CentreY() >= wall.Top() && character.CentreY() <= wall.Bottom() && character.CentreX() + 16 == wall.CentreX()) {
                    return false;
                }
            }
        }
        return true;
    }
}
