package Scenes;

import DB_Connectors.UserDbConnector;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class LoginScene {
    //---LOGIN SCENE---

    UserDbConnector userdb = new UserDbConnector();

    private Scene scene;
    private Main main; // Reference to Scenes.Main for scene switching

    public LoginScene(Main mainApp) {
        mainApp = main;
        initializeScene();
    }

    public void initializeScene() {

        Label loginLabel = new Label("Login");
        loginLabel.setFont(new Font("Constantia", 30));
        HBox loginBox = new HBox(loginLabel);
        loginBox.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("Username: ");
        usernameLabel.setFont(new Font("Arial", 18));
        TextField usernameTextField = new TextField();
        HBox usernameHBox = new HBox(usernameLabel, usernameTextField);
        usernameHBox.setAlignment(Pos.CENTER);

        Label passwordLabel = new Label("Password:  ");
        passwordLabel.setFont(new Font("Arial", 18));
        TextField passwordTextField = new TextField();
        HBox passwordHBox = new HBox(passwordLabel, passwordTextField);
        passwordHBox.setAlignment(Pos.CENTER);

        Label registerLabel = new Label("Have not account, register here:");
        registerLabel.setTextFill(Color.GREY);
        registerLabel.setFont(new Font("Arial", 12));
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #fbdb03; -fx-text-fill: white;");
        HBox registerHBox = new HBox(registerLabel, registerButton);
        registerHBox.setAlignment(Pos.CENTER);
        registerHBox.setSpacing(10);
        registerHBox.setPadding(new Insets(10));

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button loginSubmitButton = new Button("Submit");
        loginSubmitButton.setStyle("-fx-background-color: #0088ff; -fx-text-fill: white;");
        Button clearButton = new Button("Clear");
        clearButton.setStyle("-fx-background-color: #e4364a; -fx-text-fill: white;");
        HBox actionHBox = new HBox(loginSubmitButton, clearButton);
        actionHBox.setSpacing(20);
        actionHBox.setAlignment(Pos.CENTER);
        VBox loginVBox = new VBox(10);
        loginVBox.setStyle("-fx-alignment: center;");
        loginVBox.getChildren().addAll(usernameHBox, passwordHBox, actionHBox);
        loginVBox.getChildren().add(errorLabel);

        BorderPane loginPane = new BorderPane();
        loginPane.setTop(loginBox);
        loginPane.setCenter(loginVBox);
        loginPane.setBottom(registerHBox);

        registerButton.setOnAction(e ->
        {
            RegisterScene registerScene = new RegisterScene(Main.getInstance());
            Main.getInstance().switchScene(registerScene.getScene());
        });

        loginSubmitButton.setOnAction(event -> {
            String Username = usernameTextField.getText();
            String Password = passwordTextField.getText();

            if (Username.isEmpty() || Password.isEmpty()) {
                errorLabel.setText("Username and password cannot be empty.");
                return;
            }

            userdb.checkUserExistence(Username, Password);

            if (userdb.checkUserExistence(Username, Password) && userdb.checkUserIsAdmin(Username, Password)) {
                AdminScene adminScene = new AdminScene(Main.getInstance());
                Main.getInstance().switchScene(adminScene.getScene());
            } else if (userdb.checkUserExistence(Username, Password)) {
                UserScene userScene = new UserScene(Main.getInstance());
                Main.getInstance().switchScene(userScene.getScene());
            } else {
                System.out.println("Username and/or password is incorrect");
            }
        });

        clearButton.setOnAction(event -> {
            usernameTextField.clear();
            passwordTextField.clear();
        });

        this.scene = new Scene(loginPane, 500, 300);
    }

    public Scene getScene() {
        return scene;
    }
}
