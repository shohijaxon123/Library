package Scenes;

//import Scenes.LoginScene;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    private Stage stage;
    private static Main instance;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        instance = this;

        LoginScene scene = new LoginScene(this);
        stage.setTitle("Library Management System");
        stage.setScene(scene.getScene());
        stage.show();
    }

    public void switchScene(Scene newScene) {
        stage.setScene(newScene);
    }

    public static Main getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Application.launch();
    }
}

