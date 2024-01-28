package en.com.choca.havoc.entities.character;

import en.com.choca.havoc.engine.Direction;
import en.com.choca.havoc.engine.Position;
import en.com.choca.havoc.entities.GameObject;
import en.com.choca.havoc.game.Game;

public abstract class Character extends GameObject {

    protected Direction direction;
    public Game game;

    public Character(Game game, Position position) {
        super(game, position);
        this.game = game;
    }

    public Character(Position position) {
        super(position);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

}
