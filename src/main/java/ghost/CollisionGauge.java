package ghost;

public class CollisionGauge {
    /** This method is the main collision detection between walls and ghosts/waka, as well as 
     * functionality for fruit/superfruit/soda, returning a boolean based on if a collision has occurred
     */ 
    public static boolean collision(MovableCharacter character, GameCell gamecell) {
        // Character heading up
        if (character.getYVel() < 0) {
            if (character.Left() < gamecell.Right() && character.Right() > gamecell.Left()
                && character.Top() + character.getYVel() < gamecell.Bottom() && character.Bottom() + character.getYVel() > gamecell.Top()) {
                if (gamecell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gamecell.getName().equals("Superfruit")) {
                    character.changeVulnerability(true);
                } else if (gamecell.getName().equals("Soda")) {
                    character.sodaEffect(true);
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
                }  else if (gamecell.getName().equals("Soda")) {
                    character.sodaEffect(true);
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
                }  else if (gamecell.getName().equals("Soda")) {
                    character.sodaEffect(true);
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
                }  else if (gamecell.getName().equals("Soda")) {
                    character.sodaEffect(true);
                }
                return true;
            }
        }    
        character.skipMovement = false;
        return false;
    }

    /**
     * This method provides functionality for turns at intersection, returning a boolean based on if a character can make the turn
     * */ 
    public static boolean turnCheck(GameEvent gameEvent, MovableCharacter character, String move) {
        for (Wall wall : gameEvent.wallList) {
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

    /**
     * This method acts as the ghosts/waka intersection detector, returning a boolean if the character is at an intersection 
     */ 
    public static boolean intersectionDetector(GameEvent gameEvent, MovableCharacter character, String move) {
        for (GameCell cell : gameEvent.spaceList) {
            if (move.equals("up")) {
                if (character.CentreY() - 16 == cell.CentreY() && character.CentreX() == cell.CentreX()) {
                    return true;
                }
            } else if (move.equals("down")) {
                if (character.CentreY() + 16 == cell.CentreY() && character.CentreX() == cell.CentreX()) {
                    return true;
                }
            } else if (move.equals("left")) {
                if (character.CentreY() == cell.CentreY() && character.CentreX() - 16 == cell.CentreX()) {
                    return true;
                }
            } else if (move.equals("right")) {
                if (character.CentreY() == cell.CentreY() && character.CentreX() + 16 == cell.CentreX()) {
                    return true;
                }
            }
        }
        return false;
    }
}
