package ghost;

import java.util.Random;

import processing.core.PImage;

public class Ghost extends MovableCharacter {

    protected int targetX;
    protected int targetY;
    protected int distanceX;
    protected int distanceY;
    protected boolean dead;
    protected String normalSprite;
    protected boolean frightened;
    protected int modeShiftCounter = 0;
    protected int modeInterval = 0;
    protected boolean scatter;

    public Ghost(PImage sprite, int x, int y) {
        super(sprite, x, y);
        this.invincible = true;
    }
    public void tick(App app) {
        if (app.waka.isInvincible()) {              // if waka has eaten superfruit
            this.frighten(true);
            this.changeVulnerability(false);
        } else {                                    // if waka has not eaten superfruit
            this.frighten(false);
            this.changeVulnerability(true);
        }
        if (this.frightened) {
            this.sprite = app.loadImage("src/main/resources/frightened.png");
            app.frightenedCounter++;
            int GhostMultiplier = app.ghostList.size(); // this is used to account for the fact that multiple ghosts are adding to any count per tick
            if (app.frightenedCounter == 60 * app.frightenedLength * GhostMultiplier) {
                this.frighten(false);
                app.waka.changeVulnerability(false);
                app.frightenedCounter = 0;
            } 
        } else {
            this.sprite = app.loadImage(this.normalSprite);
        }

        if (this.isDead()) {
            this.sprite = new PImage();
        }

        // Check collision with Waka
        boolean collision = CollisionGauge.collision(app.waka, this); 
        if (collision) {
            if (!this.isDead()) {
                if (this.isInvincible()) {
                    app.waka.reset();
                    app.lives--;
                    for (Ghost ghost : app.ghostList) {
                        ghost.reset();
                        ghost.die(false);
                    }
                } else {
                    this.die(true);
                }
            }
        }

        this.setCellCoord();
        this.setTarget(app, this.scatter);
        this.distanceX = this.CentreX() - this.targetX;
        this.distanceY = this.CentreY() - this.targetY;
        this.moveAttempt = this.ghostAI(this.distanceX, this.distanceY, app);
        this.move(this.moveAttempt, app);
        this.moveAfterCollision(app);

        // System.out.println(this.getName());
        // System.out.println("Direction: " + this.direction);
        // System.out.println("Move Attempt: " + this.moveAttempt);
        // System.out.println("distanceXabs: " + Math.abs(this.distanceX) + " distanceYabs: " + Math.abs(this.distanceY));
        // System.out.println("distanceX: " + this.distanceX + " distanceY: " + this.distanceY);
        
        
        if (!this.skipMovement) {
            this.y += this.yVel;
            this.x += this.xVel;
        }

        if (!this.frightened) {
            this.modeShiftCounter++;
        }
        int time = app.modeLengths.get(this.modeInterval); // time prescribed by config file
        if (this.modeShiftCounter == 60 * time) {
            this.switchMode();
            if (this.modeInterval == app.modeLengths.size() - 1) {
                this.modeInterval = -1; // if its the last element in the array, reset from beginning
            }
            this.modeInterval++;
            this.modeShiftCounter = 0;
        }
        
        if (app.debugMode) {
            if (!this.frightened) {
                if (!this.isDead()) {
                    this.targetLine(app, this.targetX, this.targetY);
                }
            }
        }
    }

    public void setTarget(App app, boolean mode) {}

    public String ghostAI(int distanceX, int distanceY, App app) {
        boolean up = CollisionGauge.intersectionDetector(app, this, "up");
        boolean down = CollisionGauge.intersectionDetector(app, this, "down");
        boolean left = CollisionGauge.intersectionDetector(app, this, "left");
        boolean right = CollisionGauge.intersectionDetector(app, this, "right");
        if (this.frightened) {
            String[] moveList = new String[3];
            if (this.direction.equals("up")) {
                moveList[0] = "up";
                moveList[1] = "left";
                moveList[2] = "right";
            } else if (this.direction.equals("down")) {
                moveList[0] = "down";
                moveList[1] = "left";
                moveList[2] = "right";
            } else if (this.direction.equals("left")) {
                moveList[0] = "left";
                moveList[1] = "down";
                moveList[2] = "up";
            } else {
                moveList[0] = "right";
                moveList[1] = "down";
                moveList[2] = "up";
            }
            Random generator = new Random();
            int number = generator.nextInt(moveList.length);
            if (moveList[number].equals("up") && up) {
                return moveList[number];
            } else if (moveList[number].equals("down") && down) {
                return moveList[number];
            } else if (moveList[number].equals("left") && left) {
                return moveList[number];
            } else if (moveList[number].equals("right") && right) {
                return moveList[number];
            }
        }
        if (this.direction.equals("up")) { // when going up
            if (left || right) { // if an intersection is reached
                if (Math.abs(this.distanceX) > Math.abs(this.distanceY)) {
                    if (this.distanceX > 0) {
                        if (left) {
                            return "left"; 
                        } else if (up) {
                            return "up";
                        } else if (right) {
                            return "right";
                        } else {
                            return "down";
                        }
                    } else { // if this.distanceX < 0
                        if (right) {
                            return "right"; 
                        } else if (up) {
                            return "up";
                        } else if (left) {
                            return "left";
                        } else {
                            return "down";
                        }
                    }
                } else {
                    if (up) {
                        return "up";
                    } else {
                        if (this.distanceX > 0) {
                            if (left) {
                                return "left";
                            } else if (right) {
                                return "right";
                            } else {
                                return "down";
                            }
                        } else {
                            if (right) {
                                return "right";
                            } else if (left) {
                                return "left";
                            } else {
                                return "down";
                            }
                        }
                    }
                }
            }
        } else if (this.direction.equals("down")) { // when going down
            if (left || right) { // if an intersection is reached
                if (Math.abs(this.distanceX) > Math.abs(this.distanceY)) {
                    if (this.distanceX > 0) {
                        if (left) {
                            return "left"; 
                        } else if (down) {
                            return "down";
                        } else if (right) {
                            return "right";
                        } else {
                            return "up";
                        }
                    } else { // if this.distanceX < 0
                        if (right) {
                            return "right"; 
                        } else if (down) {
                            return "down";
                        } else if (left) {
                            return "left";
                        } else {
                            return "up";
                        }
                    }
                } else {
                    if (down) {
                        return "down";
                    } else {
                        if (this.distanceX > 0) {
                            if (left) {
                                return "left";
                            } else if (right) {
                                return "right";
                            } else {
                                return "up";
                            }
                        } else {
                            if (right) {
                                return "right";
                            } else if (left) {
                                return "left";
                            } else {
                                return "up";
                            }
                        }
                    }
                }
            }
        } else if (this.direction.equals("left")) { // when going left
            if (up || down) { // if an intersection is reached
                if (Math.abs(this.distanceY) > Math.abs(this.distanceX)) {
                    if (this.distanceY > 0) {
                        if (up) {
                            return "up"; 
                        } else if (left) {
                            return "left";
                        } else if (down) {
                            return "down";
                        } else {
                            return "right";
                        }
                    } else { // if this.distanceX < 0
                        if (down) {
                            return "down"; 
                        } else if (left) {
                            return "left";
                        } else if (up) {
                            return "up";
                        } else {
                            return "right";
                        }
                    }
                } else {
                    if (left) {
                        return "left";
                    } else {
                        if (this.distanceX > 0) {
                            if (up) {
                                return "up";
                            } else if (down) {
                                return "down";
                            } else {
                                return "right";
                            }
                        } else {
                            if (down) {
                                return "down";
                            } else if (up) {
                                return "up";
                            } else {
                                return "right";
                            }
                        }
                    }
                }
            }
        } else { // when going right
            if (up || down) { // if an intersection is reached
                if (Math.abs(this.distanceY) > Math.abs(this.distanceX)) {
                    if (this.distanceY > 0) {
                        if (up) {
                            return "up"; 
                        } else if (right) {
                            return "right";
                        } else if (down) {
                            return "down";
                        } else {
                            return "left";
                        }
                    } else { // if this.distanceX < 0
                        if (down) {
                            return "down"; 
                        } else if (right) {
                            return "right";
                        } else if (up) {
                            return "up";
                        } else {
                            return "left";
                        }
                    }
                } else {
                    if (right) {
                        return "right";
                    } else {
                        if (this.distanceX > 0) {
                            if (up) {
                                return "up";
                            } else if (down) {
                                return "down";
                            } else {
                                return "left";
                            }
                        } else {
                            if (down) {
                                return "down";
                            } else if (up) {
                                return "up";
                            } else {
                                return "left";
                            }
                        }
                    }
                }
            }
        }
        return this.direction;
    }
    
    public void die(boolean death) {
        this.dead = death;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void frighten(boolean fright) {
        this.frightened = fright;
    }

    public void targetLine(App app, int targetX, int targetY) {
        app.stroke(255);
        app.line(this.CentreX(), this.CentreY(), targetX, targetY);
    }

    public String doubleBack(String move) {
        if (move.equals("up")) {
            return "down";
        } else if (move.equals("down")) {
            return "up";
        } else if (move.equals("left")) {
            return "right";
        } else if (move.equals("right")) {
            return "left";
        } else {
            return null;
        }
    }

    public void switchMode() {
        if (this.scatter) {
            this.scatter = false;
        } else {
            this.scatter = true;
        }
    }
}
