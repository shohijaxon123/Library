package Scenes;

import DB_Connectors.UserDbConnector;
//import com.sun.tools.javac.Main;
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

public class RegisterScene {
    //---REGISTER SCENE---

    UserDbConnector userdb = new UserDbConnector();

    private Scene scene;
    private Main main;

    public RegisterScene(Main mainApp) {
        mainApp = main;
        initializeScene();
    }


    public void initializeScene()
    {
        Label registerLabel2 = new Label("Register");
        registerLabel2.setFont(new Font("Constantia", 30));
        HBox registerBox2 = new HBox(registerLabel2);
        registerBox2.setAlignment(Pos.CENTER);
        registerBox2.setPadding(new Insets(30));

        Label lastNameLabel = new Label("Last Name: ");
        lastNameLabel.setFont(new Font("Arial", 18));
        TextField lastNameTextField = new TextField();
        HBox lastNameHBox = new HBox(lastNameLabel, lastNameTextField);
        lastNameHBox.setAlignment(Pos.CENTER);

        Label firstNameLabel = new Label("First Name: ");
        firstNameLabel.setFont(new Font("Arial", 18));
        TextField firstNameTextField = new TextField();
        HBox firstNameHBox = new HBox(firstNameLabel, firstNameTextField);
        firstNameHBox.setAlignment(Pos.CENTER);

        Label userNameLabel = new Label("Username: ");
        userNameLabel.setFont(new Font("Arial", 18));
        TextField userNameTextField = new TextField();
        HBox userNameHBox = new HBox(userNameLabel, userNameTextField);
        userNameHBox.setAlignment(Pos.CENTER);

        Label passwordRegisterLabel = new Label("Password: ");
        passwordRegisterLabel.setFont(new Font("Arial", 18));
        TextField passwordRegisterTextField = new TextField();
        HBox passwordRegisterHBox = new HBox(passwordRegisterLabel, passwordRegisterTextField);
        passwordRegisterHBox.setAlignment(Pos.CENTER);

        Button submitButton2 = new Button("Submit");
        submitButton2.setStyle("-fx-background-color: #0088ff; -fx-text-fill: white;");
        Button clearButton2 = new Button("Clear");
        clearButton2.setStyle("-fx-background-color: #848d93; -fx-text-fill: white;");
        HBox actionHBox2 = new HBox(submitButton2, clearButton2);
        actionHBox2.setSpacing(20);
        actionHBox2.setAlignment(Pos.CENTER);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        VBox registerLayout = new VBox(10);
        registerLayout.getChildren().addAll(lastNameHBox, firstNameHBox, userNameHBox, passwordRegisterHBox, actionHBox2);
        registerLayout.getChildren().addAll(errorLabel);

        BorderPane registerPane = new BorderPane();
        registerPane.setTop(registerBox2);
        registerPane.setCenter(registerLayout);
        scene = new Scene(registerPane, 500, 300);

        submitButton2.setOnAction(e -> {
            String lastName = lastNameTextField.getText();
            String firstName = firstNameTextField.getText();
            String username = userNameTextField.getText();
            String password = passwordRegisterTextField.getText();

            if (lastName.isEmpty() || firstName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Properties cannot be empty.");
                return;
            }

            userdb.addNewUser(lastName, firstName, username, password);

            LoginScene loginScene = new LoginScene(Main.getInstance());
            Main.getInstance().switchScene(loginScene.getScene());
        });

        clearButton2.setOnAction(e -> {
            lastNameTextField.clear();
            firstNameTextField.clear();
            userNameTextField.clear();
            passwordRegisterTextField.clear();
        });
    }
    public Scene getScene() {
        return scene;
    }
}
