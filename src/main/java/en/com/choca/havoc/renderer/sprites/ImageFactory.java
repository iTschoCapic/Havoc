package en.com.choca.havoc.renderer.sprites;

import en.com.choca.havoc.engine.Direction;

public class ImageFactory {
    /*public static ImageResource digit(int i) {
        if (i < 0 || i > 9) throw new IllegalArgumentException("Digit must be in [0-9]");
        return ImageResource.valueOf("DIGIT_" + i);
    }*/

    public static ImageResource getPlayer(Direction direction, int i){
        if (direction != null && i != 0){
            return ImageResource.valueOf(direction + "_RUN_PLAYER_" + i);
        } else {
            return ImageResource.valueOf("PLAYER");
        }
    }

}
