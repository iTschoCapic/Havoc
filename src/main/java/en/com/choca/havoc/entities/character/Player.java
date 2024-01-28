package en.com.choca.havoc.entities.character;

import en.com.choca.havoc.engine.Direction;
import en.com.choca.havoc.engine.Position;
import en.com.choca.havoc.entities.structures.Structure;
import en.com.choca.havoc.game.Game;

public class Player extends Character {

    private boolean onGround = false;
    private final int verticalMaxSpeed = 128;
    private final int horizontalMaxSpeed = 8;
    public int currentImageNumber = 0;
    private int verticalVelocity = 0;
    private int horizontalVelocity = 0;
    private int verticalAcceleration = 2;
    private int horizontalAcceleration = 2;
    private double damping_factor = 0.9;
    private boolean hasJumped = false;

    public Player(Game game, Position position) {
        super(game, position);
        this.direction = Direction.LEFT;
    }

    public void requestMove(Direction direction){
        if (direction != this.direction){
            this.direction = direction;
            setModified(true);
        }
        Position nextPosition;

        switch (direction) {
            case UP:
                if (verticalVelocity-(verticalAcceleration*8) < -verticalMaxSpeed){
                    nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), verticalMaxSpeed);
                } else {
                    nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), verticalVelocity-(verticalAcceleration*8)); // Don't check right
                }
            case DOWN:
                if (verticalVelocity+verticalAcceleration > verticalMaxSpeed){
                    nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), verticalMaxSpeed);
                } else {
                    nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), verticalVelocity);
                }
                //nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), verticalVelocity);
            case LEFT:
                if ((horizontalVelocity-horizontalAcceleration) < -horizontalMaxSpeed){
                    nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), horizontalMaxSpeed);
                } else {
                    nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), horizontalVelocity-horizontalAcceleration);
                }
                //nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), horizontalVelocity);
            case RIGHT:
                if (horizontalVelocity+horizontalAcceleration > horizontalMaxSpeed){
                    nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), horizontalMaxSpeed);
                } else {
                    nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), horizontalVelocity+horizontalAcceleration);
                }
                //nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), horizontalVelocity);
            default:
                nextPosition = direction.nextPosition(new Position(getPosition().getX(), getPosition().getY()), verticalVelocity);
        }
        Structure nextStructure = game.getGrid().getStructure(nextPosition);
        if (checkMove(nextStructure, nextPosition)) {
            move(direction, nextPosition);
        }
    }

    public void updateVelocity(Direction direction){
        if (direction != this.direction){
            this.direction = direction;
            setModified(true);
        }
        switch (direction) {
            case UP:
                if (verticalVelocity-(verticalAcceleration*48) < -verticalMaxSpeed){
                    verticalVelocity = -verticalMaxSpeed;
                } else {
                    verticalVelocity -= verticalAcceleration*48;
                }
                break;
            case DOWN:
                if (verticalVelocity+(verticalAcceleration*48) > verticalMaxSpeed){
                    verticalVelocity = verticalMaxSpeed;
                } else {
                    verticalVelocity += verticalAcceleration*48;
                }
                break;
            case LEFT:
                if (horizontalVelocity-horizontalAcceleration < -horizontalMaxSpeed){
                    horizontalVelocity = -horizontalMaxSpeed;
                } else {
                    horizontalVelocity -= horizontalAcceleration;
                }
                break;
            case RIGHT:
                if (horizontalVelocity+horizontalAcceleration > horizontalMaxSpeed){
                    horizontalVelocity = horizontalMaxSpeed;
                } else {
                    horizontalVelocity += horizontalAcceleration;
                }
                break;
            default:
                break;
        }
    }



    public void requestFall(Direction direction, Position position){
        if (direction != this.direction){
            this.direction = direction;
            setModified(true);
        }
        
        verticalVelocity += verticalAcceleration;
        if (verticalVelocity > verticalMaxSpeed){
            verticalVelocity = verticalMaxSpeed;
        }
        
        //norm = Math.sqrt((horizontalVelocity*horizontalVelocity)+(verticalVelocity*verticalVelocity))/2;
        //if (getPosition().getY()+(int)(verticalVelocity/norm)+1 >= position.getY()){
        if (getPosition().getY()+verticalVelocity+1 >= position.getY()){
            setPosition(new Position(getPosition().getX()+horizontalVelocity, position.getY()));
            verticalVelocity = 0;
            setIsOnGround(true);
            setHasJumped(false);
            return;
            //norm = Math.sqrt((horizontalVelocity*horizontalVelocity)+(verticalVelocity*verticalVelocity))/2;
        }
        
        //setPosition(new Position(getPosition().getX()+(int)(horizontalVelocity/norm), getPosition().getY()+(int)(verticalVelocity/norm)+1));
        //setPosition(new Position(getPosition().getX()+horizontalVelocity, getPosition().getY()+verticalVelocity+1));
        //update();
    }

    public boolean checkMove(Structure structure, Position position){
        if (game.getGrid().inside(position)){
            if (structure != null) {
                return structure.walkableBy(this);
            }
            return true;
        }
        return false;
    }

    public void move(Direction direction, Position position){
        switch (direction) {
            case UP:
                verticalVelocity -= verticalAcceleration*8;
                if (verticalVelocity < -verticalMaxSpeed){
                    verticalVelocity = -verticalMaxSpeed;
                }
                //norm = Math.sqrt((horizontalVelocity*horizontalVelocity)+(verticalVelocity*verticalVelocity))/2;
                //setPosition(new Position(getPosition().getX()+(int)(horizontalVelocity/norm), getPosition().getY()+(int)(verticalVelocity/norm)));
                setPosition(new Position(getPosition().getX()+horizontalVelocity, getPosition().getY()+verticalVelocity));
                break;
            case DOWN:
                verticalVelocity += verticalAcceleration;
                if (verticalVelocity > verticalMaxSpeed){
                    verticalVelocity = verticalMaxSpeed;
                }
                //norm = Math.sqrt((horizontalVelocity*horizontalVelocity)+(verticalVelocity*verticalVelocity))/2;
                //setPosition(new Position(getPosition().getX()+(int)(horizontalVelocity/norm), getPosition().getY()+(int)(verticalVelocity/norm)));
                setPosition(new Position(getPosition().getX()+horizontalVelocity, getPosition().getY()+verticalVelocity));
                break;
            case LEFT:
                horizontalVelocity -= horizontalAcceleration;
                if (horizontalVelocity < -horizontalMaxSpeed){
                    horizontalVelocity = -horizontalMaxSpeed;
                }
                //norm = Math.sqrt((horizontalVelocity*horizontalVelocity)+(verticalVelocity*verticalVelocity))/2;
                //setPosition(new Position(getPosition().getX()+(int)(horizontalVelocity/norm), getPosition().getY()+(int)(verticalVelocity/norm)));
                setPosition(new Position(getPosition().getX()+horizontalVelocity, getPosition().getY()+verticalVelocity));
                update(Direction.UP);
                break;
            case RIGHT:
                horizontalVelocity += horizontalAcceleration;
                if (horizontalVelocity > horizontalMaxSpeed){
                    horizontalVelocity = horizontalMaxSpeed;
                }
                //norm = Math.sqrt((horizontalVelocity*horizontalVelocity)+(verticalVelocity*verticalVelocity))/2;
                //setPosition(new Position(getPosition().getX()+(int)(horizontalVelocity/norm), getPosition().getY()+(int)(verticalVelocity/norm)));
                setPosition(new Position(getPosition().getX()+horizontalVelocity, getPosition().getY()+verticalVelocity));
                update(Direction.UP);
                break;
        }
        
    }

    public void update(){
        if (horizontalVelocity != 0 || verticalVelocity != 0){
            setPosition(new Position(getPosition().getX()+horizontalVelocity, getPosition().getY()+verticalVelocity));
        }
        if (isOnGround()){
            verticalVelocity = 0;
        }
        if (verticalVelocity < 0){
            verticalVelocity += verticalAcceleration;
            if (verticalVelocity >= 0){
                verticalVelocity = 0;
            }
        } else {
            verticalVelocity -= verticalAcceleration;
            if (verticalVelocity <= 0){
                verticalVelocity = 0;
            }
        }
        if (horizontalVelocity < 0){
            horizontalVelocity += horizontalAcceleration;
            if (horizontalVelocity >= 0){
                horizontalVelocity = 0;
            }
        } else {
            horizontalVelocity -= horizontalAcceleration;
            if (horizontalVelocity <= 0){
                horizontalVelocity = 0;
            }
        }
    }

    public void update(Direction direction){
        if (isOnGround()){
            verticalVelocity = 0;
        }
        switch (direction) {
            case UP:
                if (verticalVelocity < 0){
                    verticalVelocity += verticalAcceleration;
                    if (verticalVelocity >= 0){
                        verticalVelocity = 0;
                    }
                }
                break;
            case DOWN:
                if (verticalVelocity > 0) {
                    verticalVelocity -= verticalAcceleration;
                    if (verticalVelocity <= 0){
                        verticalVelocity = 0;
                    }
                }
                break;
            case LEFT:
                if (horizontalVelocity < 0){
                    horizontalVelocity += horizontalAcceleration;
                    if (horizontalVelocity >= 0){
                        horizontalVelocity = 0;
                    }
                }
                break;
            case RIGHT:
                if (horizontalVelocity > 0) {
                    horizontalVelocity -= horizontalAcceleration;
                    if (horizontalVelocity <= 0){
                        horizontalVelocity = 0;
                    }
                }
                break;
        }
        if (horizontalVelocity != 0 || verticalVelocity != 0){
            //norm = Math.sqrt((horizontalVelocity*horizontalVelocity)+(verticalVelocity*verticalVelocity))/2;
            //setPosition(new Position(getPosition().getX()+(int)(horizontalVelocity/norm), getPosition().getY()+(int)(verticalVelocity/norm))); // Needed to change the position correctly, we need to modify #move() to use it (Problem is the velocity+acceleration)
            setPosition(new Position(getPosition().getX()+horizontalVelocity, getPosition().getY()+verticalVelocity)); // Needed to change the position correctly, we need to modify #move() to use it (Problem is the velocity+acceleration)
        }
    }

    public boolean isOnGround(){
        return onGround;
    }

    public void setIsOnGround(boolean is){
        this.onGround = is;
    }

    public boolean hasJumped(){
        return this.hasJumped;
    }

    public void setHasJumped(boolean state){
        this.hasJumped = state;
    }

    public int getVerticalVelocity(){
        return verticalVelocity;
    }
}
