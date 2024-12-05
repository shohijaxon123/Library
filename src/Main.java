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


        //---ADMIN SCENE---
        Label lbl3 = new Label("Admin panel");
        Button backToFirstButton = new Button("Go back to login");
        Button btn4 = new Button("View all books");
        Button btn5 = new Button("Add new book");
        Button btn6 = new Button("Update book info");
        Button btn7 = new Button("Delete book");

        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(lbl3, btn4, btn5, btn6, btn7, backToFirstButton);
        Scene scene2 = new Scene(layout2, 300, 250);

        //Scene scene4 = new Scene(loginPane, 500, 300);

        //registerButton.setOnAction(e -> {
        //    stage.setScene(scene4);// this is scene for register(add) user
        //});

        backToFirstButton.setOnAction(event ->
        {
            stage.setScene(scene1);
        });

        clearButton.setOnAction(event -> {
            usernameTextField.clear();
            passwordTextField.clear();
        });

        //---USER SCENE---
        Label appLabel = new Label("Welcome to E_Library");
        appLabel.setFont(new Font("Constantia", 48));
        appLabel.setTextFill(Color.WHITE);
        Rectangle bg = new Rectangle(700, 100);
        bg.setFill(Color.ORANGERED);
        StackPane labelPane = new StackPane();

        Button searchButton = new Button("Search books");
        searchButton.setFont(new Font("Verdana", 20));
        searchButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        searchButton.setMinSize(250, 70);
        Button getListButton = new Button("Get List Of All Books");
        getListButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        getListButton.setMinSize(250, 70);
        getListButton.setFont(new Font("Verdana", 20));
        Button reserveBookButton = new Button("Reserve Book");
        reserveBookButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        reserveBookButton.setMinSize(250, 70);
        reserveBookButton.setFont(new Font("Verdana", 20));
        Button addBookRequest = new Button("Add Book Request");
        addBookRequest.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        addBookRequest.setMinSize(250, 70);
        addBookRequest.setFont(new Font("Verdana", 20));

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(labelPane, searchButton, getListButton, reserveBookButton, addBookRequest);

        labelPane.getChildren().addAll(bg, appLabel, searchButton);
        VBox buttonBox = new VBox(20, labelPane, searchButton, getListButton, reserveBookButton, addBookRequest);
        buttonBox.setStyle("-fx-alignment: center;");

        layout1.setStyle("-fx-alignment: center;");
        searchButton.setAlignment(Pos.CENTER);

        BorderPane root2 = new BorderPane();
        root2.setTop(layout1);
        root2.setCenter(buttonBox);
        Scene scene3 = new Scene(root2, 700, 600);


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
        /*UserDBConnector.UserDbConnector udb = new UserDBConnector.UserDbConnector();
        udb.display();

        System.out.println();
        System.out.println(udb.checkUserExistence("", "topgun00"));
        System.out.println(udb.checkUserIsAdmin("topix10", "topgun00"));*/
    }
}

