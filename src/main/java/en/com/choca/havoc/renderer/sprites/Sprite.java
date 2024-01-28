package en.com.choca.havoc.renderer.sprites;

import en.com.choca.havoc.engine.Hitbox;
import en.com.choca.havoc.engine.Position;
import en.com.choca.havoc.entities.GameObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Sprite {

    public static final int size = 64;
    private final Pane layer;
    private final GameObject gameObject;
    private ImageView imageView;
    private Image image;
    protected Hitbox hitbox;

    public Sprite(Pane layer, Image image, GameObject gameObject, Hitbox hitbox) {
        this.layer = layer;
        this.image = image;
        this.gameObject = gameObject;
        this.hitbox = hitbox;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public Hitbox getHitbox(){
        return this.hitbox;
    }

    public final void setImage(Image image) {
        if (this.image == null || this.image != image ) {
            this.image = image;
        }
    }

    public void handleCollision(Sprite sprite){
        //System.out.println("Collision");
    }

    public boolean collides(Sprite sprite){
        return hitbox.intersects(sprite.getHitbox());
    }

    /*public void updateImage() {
        if (this.gameObject instanceof Door){
            if (((Door) this.gameObject).isModified()){
                if (((Door) this.gameObject).isOpen()){
                    this.image = DOOR_OPENED.getImage();
                    /*if (((Door) this.gameObject).isSuperior()){
                        this.image = DOOR_OPENED_PLUS.getImage();
                    } else {
                        this.image = DOOR_OPENED_MINUS.getImage();
                    }* /
                } else {
                    this.image = DOOR_CLOSED.getImage();
                    /*if (((Door) this.gameObject).isSuperior()){
                        this.image = DOOR_CLOSED_PLUS.getImage();
                    } else {
                        this.image = DOOR_CLOSED_MINUS.getImage();
                    }* /
                }
            }
        }
    }*/

    public Position getPosition() {
        return getGameObject().getPosition();
    }

    public final void render() {
        if (gameObject.isModified()) {
            if (imageView != null) {
                remove();
            }
            //updateImage();
            imageView = new ImageView(this.image);
            imageView.setX(getPosition().getX());
            imageView.setY(getPosition().getY());
            imageView.setFitHeight(size);
            imageView.setFitWidth(size);
            layer.getChildren().add(imageView);
            gameObject.setModified(false);
            hitbox.update(getPosition().getX(), getPosition().getY(), size, size);
        }
    }

    public final void remove() {
        layer.getChildren().remove(imageView);
        imageView = null;
    }
}
