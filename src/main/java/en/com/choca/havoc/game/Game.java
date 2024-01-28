package en.com.choca.havoc.game;

import en.com.choca.havoc.engine.Position;
import en.com.choca.havoc.entities.character.Player;

public class Game {

    private Grid grid;
    private Player player;

    public Game(Grid grid) {
        this.grid = grid;
        this.player = new Player(this, new Position(1, 1));
    }

    public Grid getGrid(){
        return this.grid;
    }
}
