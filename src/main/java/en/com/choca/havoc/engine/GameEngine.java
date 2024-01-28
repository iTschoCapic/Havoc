package en.com.choca.havoc.engine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import en.com.choca.havoc.entities.character.Player;
import en.com.choca.havoc.entities.character.Zombie;
import en.com.choca.havoc.entities.structures.Snow;
import en.com.choca.havoc.entities.structures.Structure;
import en.com.choca.havoc.game.Game;
import en.com.choca.havoc.renderer.sprites.Sprite;
import en.com.choca.havoc.renderer.sprites.SpriteFactory;
import en.com.choca.havoc.renderer.sprites.SpritePlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public final class GameEngine {

    private final Stage stage;
    private Game game;
    private Input input;
    private static AnimationTimer gameLoop;
    private Pane layer;
    private Player player;
    private SpritePlayer spritePlayer;
    private long gravityTime;
    private long inputTime;
    private final List<Sprite> spritesStructures = new LinkedList<>();
    private final List<Sprite> spritesEntities = new LinkedList<>();
    private final Set<Sprite> cleanUpSprites = new HashSet<>();
    private Rectangle playerHighlightRectangle;
    private Group root;
    private Sprite sprite_zombie;

    public GameEngine(final Stage stage, Game game){
        this.stage = stage;
        this.game = game;
        initialize();
        loop();
    }

    public void start(){
        gameLoop.start();
    }

    public void initialize(){
        root = new Group();
        layer = new Pane();
        Scene scene = new Scene(root);
        input = new Input(scene);

        stage.setScene(scene);
        stage.hide(); // Need to rework this to not close the window every time I go from the main menu in here.
        stage.show();
        
        root.getChildren().add(layer);

        spritesStructures.clear();

        for (var block : game.getGrid().values()) {
            spritesStructures.add(SpriteFactory.create(layer, block));
            block.setModified(true);
        }

        Zombie zombie = new Zombie(game, new Position(8*64, 8*64));
        sprite_zombie = SpriteFactory.create(layer, zombie);
        spritesEntities.add(sprite_zombie);

        player = new Player(game, new Position(1*64, 7*64));
        player.setDirection(Direction.LEFT);
        spritePlayer = new SpritePlayer(layer, player);
        spritesEntities.add(spritePlayer);

        Rectangle zombieHighlightRectangle = new Rectangle(sprite_zombie.getPosition().getX(), sprite_zombie.getPosition().getY(), 64, 64);
        zombieHighlightRectangle.setStroke(Color.RED);
        zombieHighlightRectangle.setFill(Color.TRANSPARENT);

        playerHighlightRectangle = new Rectangle(player.getPosition().getX()*64, player.getPosition().getY()*64, 64, 64);
        playerHighlightRectangle.setStroke(Color.RED);
        playerHighlightRectangle.setFill(Color.TRANSPARENT);
        root.getChildren().addAll(zombieHighlightRectangle, playerHighlightRectangle);
        
    }

    public void loop(){
        gameLoop = new AnimationTimer() {
            public void handle(long now) {
                // Check keyboard actions
                processInput(now);
                player.update();
                checkContext(now);
                handleCollisions(spritesEntities);

                // Temporary
                root.getChildren().remove(playerHighlightRectangle);
                playerHighlightRectangle = new Rectangle(player.getPosition().getX(), player.getPosition().getY(), 64, 64);
                playerHighlightRectangle.setStroke(Color.RED);
                playerHighlightRectangle.setFill(Color.TRANSPARENT);
                root.getChildren().add(playerHighlightRectangle);

                // Process actions
                //update(now);
                //checkCollision(now);
                //checkExplosions(now);

                // Graphical update
                cleanupSprites();
                render();
                //statusBar.update(game);

            }
        };
    }

    public void handleCollisions(List<Sprite> sprites) {
        for (int i = 0; i < sprites.size() - 1; i++) {
            for (int j = i + 1; j < sprites.size(); j++) {
                if (sprites.get(i).collides(sprites.get(j))) {
                    sprites.get(i).handleCollision(sprites.get(j));
                    sprites.get(j).handleCollision(sprites.get(i));
                }
            }
        }
    }

    private void render() {
        spritesStructures.forEach(Sprite::render);
        spritesEntities.forEach(Sprite::render);
    }

    public void cleanupSprites() {
        spritesStructures.forEach(sprite -> {
            if (sprite.getGameObject().isDeleted()) {
                game.getGrid().remove(sprite.getPosition());
                cleanUpSprites.add(sprite);
            }
        });
        spritesEntities.forEach(sprite -> {
            if (sprite.getGameObject().isDeleted()) {
                game.getGrid().remove(sprite.getPosition());
                cleanUpSprites.add(sprite);
            }
        });
        cleanUpSprites.forEach(Sprite::remove);
        spritesStructures.removeAll(cleanUpSprites);
        spritesEntities.removeAll(cleanUpSprites);
        cleanUpSprites.clear();
    }

    private void processInput(long now) {
        if (input.isExit()) {
            gameLoop.stop();
            Platform.exit();
            System.exit(0);
        } else if ((now - inputTime)/1000000 > 25){
            if (input != null){
                for (KeyCode inputs : input.getKeyPressed()) {
                    if (inputs == KeyCode.Z) {
                        if (!player.hasJumped()){
                            //player.requestMove(Direction.UP);
                            System.out.println("Vertical Velocity before :" + player.getVerticalVelocity());
                            player.updateVelocity(Direction.UP);
                            System.out.println("Vertical Velocity after :" + player.getVerticalVelocity());
                            player.setHasJumped(true);
                            spritePlayer.startAnimation(Direction.UP);
                            gravityTime = now;
                        }
                    } else {
                        spritePlayer.stopAnimation(Direction.UP);
                    }
                    if (inputs == KeyCode.S) {
                        player.updateVelocity(Direction.DOWN);
                        //player.requestMove(Direction.DOWN);
                        spritePlayer.startAnimation(Direction.DOWN);
                    } else {
                        spritePlayer.stopAnimation(Direction.DOWN);
                    }
                    if (inputs == KeyCode.Q) {
                        player.updateVelocity(Direction.LEFT);
                        //player.requestMove(Direction.LEFT);
                        spritePlayer.startAnimation(Direction.LEFT);
                    } else {
                        spritePlayer.stopAnimation(Direction.LEFT);
                    }
                    if (inputs == KeyCode.D) {
                        player.updateVelocity(Direction.RIGHT);
                        //player.requestMove(Direction.RIGHT);
                        spritePlayer.startAnimation(Direction.RIGHT);
                    } else {
                        spritePlayer.stopAnimation(Direction.RIGHT);
                    }
                    player.setModified(true);
                    inputTime = now;
                }
            }
        }
    }

    public void checkContext(long now){ // Check any height for a floor to land on and give gravity motion to player
        Structure bottom_structure = null;
        Structure bottom_structure2 = null;

        for(int i = 0; i < game.getGrid().getHeight(); i+=64){
            if (player.getPosition().getY() > i){ // Doesn't need to check above player
                continue;
            }
            bottom_structure = game.getGrid().getStructure(new Position((int)Math.ceil((double)player.getPosition().getX()/64)*64, i)); // We need this to have a block beneath the player.
            bottom_structure2 = game.getGrid().getStructure(new Position((int)Math.floor((double)player.getPosition().getX()/64)*64, i)); // So we check both blocks (because player can be on top of multiple blocks)
            
            if (bottom_structure != null || bottom_structure2 != null){ // We find what we want
                break;
            }
        }
        if (bottom_structure == null && bottom_structure2 == null){ // It means we didn't find any floor so we're above the void and we start falling
            bottom_structure = new Snow(new Position(player.getPosition().getX(), game.getGrid().getHeight()+256));
        } else if (bottom_structure == null && bottom_structure2 != null){ // So we only have to write the tests for bottom_structure and not for both
            bottom_structure = bottom_structure2;
        }

        if (player.getPosition().getY() < bottom_structure.getPosition().getY()-64){ // We need to ensure that player is currently flying and the -64 is for the player height
            System.out.println("Player on ground:" + player.isOnGround());
            player.setIsOnGround(false);
            if ((now - gravityTime)/1000000 > 400){ // This is to limit the time between the jump and the fall
                player.requestFall(Direction.DOWN, new Position(player.getPosition().getX(), bottom_structure.getPosition().getY()-64)); // Acceleration
            }
        }
        
    }    
}
