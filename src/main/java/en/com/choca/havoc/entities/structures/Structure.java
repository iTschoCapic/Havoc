package en.com.choca.havoc.entities.structures;

import en.com.choca.havoc.engine.Position;
import en.com.choca.havoc.entities.GameObject;
import en.com.choca.havoc.entities.character.Player;
import en.com.choca.havoc.game.Game;

public abstract class Structure extends GameObject {

    public Structure(Game game, Position position) {
        super(game, position);
    }

    public Structure(Position position) {
        super(position);
    }

    public boolean walkableBy(Player player) {
        return false;
    }

}
