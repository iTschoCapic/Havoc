package en.com.choca.havoc.renderer.sprites;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class PlayerImageLoader {

    private static final int SPRITE_WIDTH = 128; // Width of each frame in pixels
    private static final int SPRITE_HEIGHT = 128; // Height of each frame in pixels

    private static final int[] FRAMES_PER_ROW = { 5, 5, 8, 8, 5, 5, 8, 8 };

    public static Image[] loadPlayerImages() {
        Image spriteSheet = new Image("/images/player/character2.png");
        PixelReader pixelReader = spriteSheet.getPixelReader();
        int totalFrames = 0;
        for (int frames : FRAMES_PER_ROW) {
            totalFrames += frames;
        }
        Image[] playerImages = new Image[totalFrames];

        int frameIndex = 0;
        for (int row = 0; row < FRAMES_PER_ROW.length; row++) {
            int frames = FRAMES_PER_ROW[row];
            for (int frame = 0; frame < frames; frame++) {
                WritableImage frameImage = new WritableImage(SPRITE_WIDTH, SPRITE_HEIGHT);
                for (int y = 0; y < SPRITE_HEIGHT; y++) {
                    for (int x = 0; x < SPRITE_WIDTH; x++) {
                        Color color = pixelReader.getColor(x + frame * SPRITE_WIDTH, y + row * SPRITE_HEIGHT);
                        frameImage.getPixelWriter().setColor(x, y, color);
                    }
                }
                playerImages[frameIndex] = frameImage;
                frameIndex++;
            }
        }

        return playerImages;
    }
}
