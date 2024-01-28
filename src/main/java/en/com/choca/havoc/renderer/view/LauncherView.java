package en.com.choca.havoc.renderer.view;

import en.com.choca.havoc.engine.GameEngine;
import en.com.choca.havoc.game.Game;
import en.com.choca.havoc.game.Grid;
import static en.com.choca.havoc.launcher.Entity.*;
import en.com.choca.havoc.launcher.Entity;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LauncherView extends BorderPane {

    private Stage stage; // Used to setup the view but isn't "used"

    public LauncherView(Stage stage) {
        this.stage = stage;

        Button play_button = new Button("Play");
        Button settings_button = new Button("Settings");
        Button exit_button = new Button("Exit");

        VBox buttonsContainer = new VBox(Screen.getPrimary().getVisualBounds().getHeight()/3);
        buttonsContainer.getChildren().addAll(play_button, settings_button, exit_button);

        // Set the VBox in the center of the BorderPane
        this.setCenter(buttonsContainer);

        EventHandler<ActionEvent> play_event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Entity[][] entities = {
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow, Snow, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Snow, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Snow},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Snow, Empty, Empty, Empty},
                    { Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty, Empty}
                };
                Grid grid = new Grid(20, 10, entities);
                Game game = new Game(grid);

                GameEngine engine = new GameEngine(stage, game); // I need to execute that after the activation of a button otherwise it will display GameEngine first and then display the main screen.
                engine.start();
            }
        };
        play_button.setOnAction(play_event);
    }
}
