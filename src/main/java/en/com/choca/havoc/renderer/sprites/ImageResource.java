package en.com.choca.havoc.renderer.sprites;

import javafx.scene.image.Image;

public enum ImageResource {

    PLAYER("player.png"),
    LEFT_RUN_PLAYER_1("left_run_player_1.png"),
    LEFT_RUN_PLAYER_2("left_run_player_2.png"),
    LEFT_RUN_PLAYER_3("left_run_player_3.png"),
    LEFT_RUN_PLAYER_4("left_run_player_4.png"),
    LEFT_RUN_PLAYER_5("left_run_player_5.png"),
    LEFT_RUN_PLAYER_6("left_run_player_6.png"),
    LEFT_RUN_PLAYER_7("left_run_player_7.png"),
    LEFT_RUN_PLAYER_8("left_run_player_8.png"),
    SNOW_BLOCK("snow_block.png"),
    STONE("stone.png"),
    ZOMBIE("zombie.png");

    private final Image image;

    public static final int size = 128;

    ImageResource(String file) {
        try {
            if (file.contains("player")){
                this.image = new Image(ImageResource.class.getResourceAsStream("/images/player/" + file));
            } else {
                this.image = new Image(ImageResource.class.getResourceAsStream("/images/" + file));
            }
            if (image.getWidth() != size && image.getHeight() != size) {
                String msg = "File " + file + " does not have the correct size " + image.getWidth() + " x " + image.getHeight();
                throw new RuntimeException (msg);
            }
        } catch (NullPointerException  e) {
            System.err.println("Resource not found : " + file);
            throw e;
        }
    }

    public Image getImage() {
        return image;
    }
    
}
