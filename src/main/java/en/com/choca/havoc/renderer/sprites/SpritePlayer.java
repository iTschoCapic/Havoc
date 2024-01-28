package en.com.choca.havoc.renderer.sprites;

import en.com.choca.havoc.engine.Direction;
import en.com.choca.havoc.engine.Hitbox;
import en.com.choca.havoc.entities.character.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SpritePlayer extends Sprite {

    private static final int NUM_FRAMES = 52;
    private static final int ANIMATION_DURATION = 128;

    private Image[] playerImages;
    private int downFrameIndex;
    private int leftFrameIndex = 11;
    private int rightFrameIndex = 19;
    private int upFrameIndex;
    private Timeline downAnimationTimeline;
    private Timeline leftAnimationTimeline;
    private Timeline rightAnimationTimeline;
    private Timeline upAnimationTimeline;

    public SpritePlayer(Pane layer, Player player) {
        super(layer, null, player, new Hitbox(player.getPosition().getX(), player.getPosition().getY(), size, size));
        updateImage(0);
        this.playerImages = new Image[NUM_FRAMES];
        this.playerImages = PlayerImageLoader.loadPlayerImages();
        initializeAnimations();
        hitbox = new Hitbox(this.getPosition().getX(), this.getPosition().getY(), size, size);
    }

    //@Override
    public void updateImage(int number) {
        Player player = (Player) getGameObject();
        Image image = ImageFactory.getPlayer(player.getDirection(), number).getImage();
        setImage(image);
    }

    private void initializeAnimations() {
        upAnimationTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> renderNextFrame(Direction.UP)),
                new KeyFrame(Duration.millis(ANIMATION_DURATION))
        );
        upAnimationTimeline.setCycleCount(Animation.INDEFINITE);
        downAnimationTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> renderNextFrame(Direction.DOWN)),
                new KeyFrame(Duration.millis(ANIMATION_DURATION))
        );
        downAnimationTimeline.setCycleCount(Animation.INDEFINITE);
        leftAnimationTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> renderNextFrame(Direction.LEFT)),
                new KeyFrame(Duration.millis(ANIMATION_DURATION))
        );
        leftAnimationTimeline.setCycleCount(Animation.INDEFINITE);
        rightAnimationTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> renderNextFrame(Direction.RIGHT)),
                new KeyFrame(Duration.millis(ANIMATION_DURATION))
        );
        rightAnimationTimeline.setCycleCount(Animation.INDEFINITE);
    }

    private void renderNextFrame(Direction direction) {
        switch(direction){
            case DOWN:
                setImage(playerImages[downFrameIndex]);
                downFrameIndex = (downFrameIndex + 1) % NUM_FRAMES;
                break;
            case LEFT:
                setImage(playerImages[leftFrameIndex]);
                if (((leftFrameIndex + 1) % NUM_FRAMES) > 17){
                    leftFrameIndex = 11;
                } else {
                    leftFrameIndex = (leftFrameIndex + 1) % NUM_FRAMES;
                }
                break;
            case RIGHT:
                setImage(playerImages[rightFrameIndex]);
                if (((rightFrameIndex + 1) % NUM_FRAMES) > 25){
                    rightFrameIndex = 18;
                } else {
                    rightFrameIndex = (rightFrameIndex + 1) % NUM_FRAMES;
                }
                break;
            case UP:
                setImage(playerImages[upFrameIndex]);
                upFrameIndex = (upFrameIndex + 1) % NUM_FRAMES;
                break;
        }
    }

    public void startAnimation(Direction direction) {
        switch(direction){
            case UP:
                upAnimationTimeline.play();
                break;
            case DOWN:
                downAnimationTimeline.play();
                break;
            case LEFT:
                leftAnimationTimeline.play();
                break;
            case RIGHT:
                rightAnimationTimeline.play();
                break;
        }
    }

    public void stopAnimation(Direction direction) {
        switch(direction){
            case UP:
                upAnimationTimeline.stop();
                break;
            case DOWN:
                downAnimationTimeline.stop();
                break;
            case LEFT:
                leftAnimationTimeline.stop();
                break;
            case RIGHT:
                rightAnimationTimeline.stop();
                break;
        }
    }

    public void update(){
        hitbox.update(getPosition().getX(), getPosition().getY(), size, size);
    }
}
