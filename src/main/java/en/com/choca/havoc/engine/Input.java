package en.com.choca.havoc.engine;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class Input {
    private Scene scene;
    private Set<KeyCode> keysPressed;

    public Input(Scene scene) {
        this.scene = scene;
        this.keysPressed = new HashSet<>();
        initialize();
    }

    private void initialize() {
        scene.setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            keysPressed.add(keyCode);
        });

        scene.setOnKeyReleased(e -> {
            KeyCode keyCode = e.getCode();
            keysPressed.remove(keyCode);
        });
    }

    public Set<KeyCode> getKeyPressed(){
        return this.keysPressed;
    }

    public boolean isMoveUp() {
        return keysPressed.contains(KeyCode.Z);
    }

    public boolean isMoveDown() {
        return keysPressed.contains(KeyCode.S);
    }

    public boolean isMoveLeft() {
        return keysPressed.contains(KeyCode.Q);
    }

    public boolean isMoveRight() {
        return keysPressed.contains(KeyCode.D);
    }

    public boolean isSpace() {
        return keysPressed.contains(KeyCode.SPACE);
    }

    public boolean isBomb() {
        return keysPressed.contains(KeyCode.ENTER);
    }

    public boolean isExit() {
        return keysPressed.contains(KeyCode.ESCAPE);
    }

    public void clear() {
        keysPressed.clear();
    }
}
