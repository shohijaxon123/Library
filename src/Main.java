import java.sql.*;
import java.util.*;
import javafx.geometry.*;
//import models.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //---LOGIN SCENE---
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
        registerButton.setStyle("-fx-background-color: #e4364a; -fx-text-fill: white;");
        HBox registerHBox = new HBox(registerLabel, registerButton);
        registerHBox.setAlignment(Pos.CENTER);
        registerHBox.setSpacing(10);
        registerHBox.setPadding(new Insets(10));

        /*ComboBox<String> roleComboBox = new ComboBox<>();
        String role1 = "Admin";
        String role2 = "User";
        roleComboBox.getItems().addAll(role1, role2); // Add options
        roleComboBox.setPromptText("Choose role"); // Placeholder text*/

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #0088ff; -fx-text-fill: white;");
        Button clearButton = new Button("Clear");
        clearButton.setStyle("-fx-background-color: #848d93; -fx-text-fill: white;");
        HBox actionHBox = new HBox(submitButton, clearButton);
        actionHBox.setSpacing(20);
        actionHBox.setAlignment(Pos.CENTER);
        VBox layoutx = new VBox(10);
        layoutx.setStyle("-fx-alignment: center;");
        layoutx.getChildren().addAll(usernameHBox, passwordHBox, actionHBox);

        BorderPane loginPane = new BorderPane();
        loginPane.setTop(loginBox);
        loginPane.setCenter(layoutx);
        loginPane.setBottom(registerHBox);
        Scene scene1 = new Scene(loginPane, 500, 300);



        //Adminhere

        clearButton.setOnAction(event -> {
            usernameTextField.clear();
            passwordTextField.clear();
        });

        //Userhere

        stage.setTitle("Library Management System");
        stage.setScene(scene1);
        stage.show();
    }

    /*public static void displayBooks(Vector<Book> vec){
        for (Book elem : vec){
            elem.displayInfo();
            System.out.println();
        }
    }*/

    public static void main(String[] args) {
        Application.launch();
        /*UserDbConnector udb = new UserDbConnector();
        udb.display();

        System.out.println();
        System.out.println(udb.checkUserExistence("", "topgun00"));
        System.out.println(udb.checkUserIsAdmin("topix10", "topgun00"));*/
    }
}

