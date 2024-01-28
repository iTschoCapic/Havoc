package en.com.choca.havoc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import en.com.choca.havoc.renderer.view.LauncherView;

public class Main extends Application {

    @Override
    public void start(Stage stage)  {
        LauncherView launcher = new LauncherView(stage);
        Scene scene = new Scene(launcher);

        stage.setTitle("Havoc");
        stage.setScene(scene);
        stage.setMaximized(true); //stage.setFullScreen(true);
        stage.show(); // showAndWait
    }

public static void main(String[] args) { launch(); }
}