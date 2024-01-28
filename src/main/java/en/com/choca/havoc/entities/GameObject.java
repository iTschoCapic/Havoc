package en.com.choca.havoc.entities;

import en.com.choca.havoc.engine.Position;
import en.com.choca.havoc.game.Game;

public abstract class GameObject {
    public final Game game;
    private boolean deleted = false;
    private boolean modified = true;
    private Position position;

    public GameObject(Game game, Position position) {
        this.game = game;
        this.position = position;
    }

    public GameObject(Position position) {
        this(null, position);
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
        setModified(true); //Otherwise animation won't stop
    }

    public boolean isModified() {
        return modified;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void remove() {
        deleted = true;
    }
}
