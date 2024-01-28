package en.com.choca.havoc.entities.character;

import en.com.choca.havoc.engine.Direction;
import en.com.choca.havoc.engine.Position;
import en.com.choca.havoc.game.Game;

public class Zombie extends Character {

    public Zombie(Game game, Position position) {
        super(game, position);
        this.direction = Direction.LEFT;
    }
    
}
