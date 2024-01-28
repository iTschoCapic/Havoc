package en.com.choca.havoc.renderer.sprites;

import en.com.choca.havoc.entities.GameObject;
import en.com.choca.havoc.entities.character.Zombie;
import en.com.choca.havoc.entities.structures.Snow;
import en.com.choca.havoc.entities.structures.Stone;
import javafx.scene.layout.Pane;

import static en.com.choca.havoc.renderer.sprites.ImageResource.*;

import en.com.choca.havoc.engine.Hitbox;

public final class SpriteFactory {

    public static Sprite create(Pane layer, GameObject gameObject) {
        if (gameObject instanceof Stone)
            return new Sprite(layer, STONE.getImage(), gameObject, new Hitbox(gameObject.getPosition().getX(), gameObject.getPosition().getY(), size, size));
        if (gameObject instanceof Snow)
            return new Sprite(layer, SNOW_BLOCK.getImage(), gameObject, new Hitbox(gameObject.getPosition().getX(), gameObject.getPosition().getY(), size, size));
        if (gameObject instanceof Zombie)
            return new Sprite(layer, ZOMBIE.getImage(), gameObject, new Hitbox(gameObject.getPosition().getX(), gameObject.getPosition().getY(), size, size));
        /*if (gameObject instanceof Door) {
            if (((Door)gameObject).isOpen()) {
                if (((Door)gameObject).isSuperior())
                    return new Sprite(layer, DOOR_OPENED_PLUS.getImage(), gameObject);
                else
                    return new Sprite(layer, DOOR_OPENED_MINUS.getImage(), gameObject);
            }
            else {
                if (((Door)gameObject).isSuperior())
                    return new Sprite(layer, DOOR_CLOSED_PLUS.getImage(), gameObject);
                else
                    return new Sprite(layer, DOOR_CLOSED_MINUS.getImage(), gameObject);
            }
        }
        if (gameObject instanceof BombCapacity) {
            if (((BombCapacity)gameObject).positive()) {
                return new Sprite(layer, BONUS_BOMB_NB_INC.getImage(), gameObject);
            }
            else
                return new Sprite(layer, BONUS_BOMB_NB_DEC.getImage(), gameObject);
        }
        if (gameObject instanceof BombRange) {
            if (((BombRange)gameObject).positive()) {
                return new Sprite(layer, BONUS_BOMB_RANGE_INC.getImage(), gameObject);
            }
            else
                return new Sprite(layer, BONUS_BOMB_RANGE_DEC.getImage(), gameObject);
        }*/
        throw new RuntimeException("Unsupported sprite for decor " + gameObject);
    }
}
