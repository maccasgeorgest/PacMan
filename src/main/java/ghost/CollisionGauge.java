package ghost;
/**
 * Acts as the Collision manager between Game Cells. <br>
 * 
 * Contains methods for collision checks, turning at an intersection and detecting an intersection
 * @author Ronen Bhaumik
 */
public class CollisionGauge {
    /**
     * Initialises a new CollisionGauge object
     */
    public CollisionGauge() {}
    
    /** This method is the main collision detection between walls and ghosts/waka
     * @param character character to be checked if collided with
     * @param gameCell game cell to be checked if collided with
     * @return whether a collision occured 
     */ 
    public static boolean collision(MovableCharacter character, GameCell gameCell) {
        // Character heading up
        if (character.getYVel() < 0) {
            if (character.Left() < gameCell.Right() && character.Right() > gameCell.Left()
                    && character.Top() + character.getYVel() < gameCell.Bottom()
                    && character.Bottom() + character.getYVel() > gameCell.Top()) {
                if (checkList(character, gameCell)) {
                    return true;
                }
            }
            // Character heading down
        } else if (character.getYVel() > 0) {
            if (character.Left() < gameCell.Right() && character.Right() > gameCell.Left()
                    && character.Top() + character.getYVel() < gameCell.Bottom()
                    && character.Bottom() + character.getYVel() > gameCell.Top()) {
                if (checkList(character, gameCell)) {
                    return true;
                }
            }
            // Character heading left
        } else if (character.getXVel() < 0) {
            if (character.Left() + character.getXVel() < gameCell.Right()
                    && character.Right() + character.getXVel() > gameCell.Left() && character.Top() < gameCell.Bottom()
                    && character.Bottom() > gameCell.Top()) {
                if (checkList(character, gameCell)) {
                    return true;
                }
            }
            // Character heading right
        } else if (character.getXVel() > 0) {
            if (character.Left() + character.getXVel() < gameCell.Right()
                    && character.Right() + character.getXVel() > gameCell.Left() && character.Top() < gameCell.Bottom()
                    && character.Bottom() > gameCell.Top()) {
                if (checkList(character, gameCell)) {
                    return true;
                }
            }
        }    
        character.skipMovement = false;
        return false;
    }
    /**
     * Functionality for wall/superfruit/soda, returning a boolean based on if a collision has occurred <br>
     * Acts as a helper method to Collision
     * @param character character to be checked if collided with
     * @param gameCell game cell to be checked if collided with
     * @return whether a collision occured 
     */
    public static boolean checkList(MovableCharacter character, GameCell gameCell) {
        if (gameCell.getName().equals("Wall")) {
                    character.skipMovement = true;
                } else if (gameCell.getName().equals("Superfruit")) {
                    character.changeVulnerability(true);
                } else if (gameCell.getName().equals("Soda")) {
                    character.sodaEffect(true);
                }
                return true;
    }

    /**
     * This method provides functionality for turns at intersection, returning a boolean based on if a character can make the turn
     * @param gameEvent GameEvent object that is hosting the game
     * @param character Character to be checked
     * @param move Attempted move
     * @return whether the character can make the turn
     */ 
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
     * This method acts as the ghosts/waka intersection detector, returning a boolean if the character is at an intersection. <br>
     * Works by checking if for any given frame perpendicular movment is possible - if yes, then the character is at an intersection
     * @param gameEvent GameEvent object that is hosting the game
     * @param character character to be checked
     * @param move character's attempted move
     * @return whether character is at an intersection
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
