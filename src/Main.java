import java.sql.*;
import java.util.*;

import javafx.collections.ObservableList;
import javafx.geometry.*;
//import models.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        UserDbConnector userdb = new UserDbConnector();
        BookDbConnector bookdb = new BookDbConnector();

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

        TableView<Book> tableView = new TableView<>();
        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        TableColumn<Book, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        TableColumn<Book, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn, genreColumn, quantityColumn, statusColumn);
        ObservableList<Book> books = bookdb.getBooks();
        tableView.setItems(books);
        VBox vbox = new VBox(tableView);
        Scene scene6 = new Scene(vbox, 800, 600);


        btn4.setOnAction(e ->{
            stage.setScene(scene6);
        });

        Button btn5 = new Button("Add new book");

        Label addNewBookLabel = new Label("Add new book");
        addNewBookLabel.setFont(new Font("Constantia", 30));
        HBox addNewBookBox = new HBox(addNewBookLabel);
        addNewBookBox.setAlignment(Pos.CENTER);
        addNewBookBox.setPadding(new Insets(30));

        Label addTitleLabel = new Label("Title: ");
        addTitleLabel.setFont(new Font("Arial", 18));
        TextField addTitleTextField = new TextField();
        HBox addTitleHBox = new HBox(addTitleLabel, addTitleTextField);
        addTitleHBox.setAlignment(Pos.CENTER);

        Label addAuthorLabel = new Label("Author: ");
        addAuthorLabel.setFont(new Font("Arial", 18));
        TextField addAuthorTextField = new TextField();
        HBox addAuthorHBox = new HBox(addAuthorLabel, addAuthorTextField);
        addAuthorHBox.setAlignment(Pos.CENTER);

        Label addGenreLabel = new Label("Genre: ");
        addGenreLabel.setFont(new Font("Arial", 18));
        TextField addGenreTextField = new TextField();
        HBox addGenreHBox = new HBox(addGenreLabel, addGenreTextField);
        addGenreHBox.setAlignment(Pos.CENTER);

        Label addQuanityLabel = new Label("Quanity: ");
        addQuanityLabel.setFont(new Font("Arial", 18));
        TextField addQuanityTextField = new TextField();
        HBox addQuanityHBox = new HBox(addQuanityLabel, addQuanityTextField);
        addQuanityHBox.setAlignment(Pos.CENTER);

        Button submitButton3 = new Button("Submit");
        submitButton3.setStyle("-fx-background-color: #0088ff; -fx-text-fill: white;");
        Button clearButton3 = new Button("Clear");
        clearButton3.setStyle("-fx-background-color: #848d93; -fx-text-fill: white;");
        HBox actionHBox3 = new HBox(submitButton3, clearButton3);
        actionHBox3.setSpacing(20);
        actionHBox3.setAlignment(Pos.CENTER);

        VBox addNewBookLayout = new VBox(10);
        addNewBookLayout.getChildren().addAll(addTitleHBox, addAuthorHBox, addGenreHBox, addQuanityHBox, actionHBox3);
        BorderPane addNewBookPane = new BorderPane();
        addNewBookPane.setTop(addNewBookBox);
        addNewBookPane.setCenter(addNewBookLayout);

        Scene scene5 = new Scene(addNewBookPane, 500, 300);

        btn5.setOnAction(e ->{
            stage.setScene(scene5);
        });

        submitButton3.setOnAction(e ->{
            String Title = addTitleTextField.getText();
            String Author = addAuthorTextField.getText();
            String Genre = addGenreTextField.getText();
            String Quantity = addQuanityTextField.getText();
            int Quantity2 = Integer.parseInt(Quantity);
            bookdb.addNewBook(Title, Author, Genre, Quantity2);
        });

        Button btn6 = new Button("Update book info"); //write almost the same code as in add new book

        Button btn7 = new Button("Delete book"); //delete book by id

        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(lbl3, btn4, btn5, btn6, btn7, backToFirstButton);
        Scene scene2 = new Scene(layout2, 300, 250);


        //---REGISTER SCENE---

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

        VBox registerLayout = new VBox(10);
        registerLayout.getChildren().addAll(lastNameHBox, firstNameHBox, userNameHBox, passwordRegisterHBox, actionHBox2);
        BorderPane registerPane = new BorderPane();
        registerPane.setTop(registerBox2);
        registerPane.setCenter(registerLayout);

        Scene scene4 = new Scene(registerPane, 500, 300);

        registerButton.setOnAction(e -> {
            stage.setScene(scene4);// this is scene for register(add) user
        });

        submitButton2.setOnAction(e ->{
            String lastName = lastNameTextField.getText();
            String firstName = firstNameTextField.getText();
            String username = usernameTextField.getText();
            String password = passwordRegisterTextField.getText();
            userdb.addNewUser(lastName, firstName, username, password);
            stage.setScene(scene1);
        });

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

        submitButton.setOnAction(event -> {
            String Username = usernameTextField.getText();
            String Password = passwordTextField.getText();
            //String Role = roleComboBox.getValue();


            userdb.checkUserExistence(Username, Password);

            //System.out.println(userdb.checkUserIsAdmin(Username, Password));
            if (userdb.checkUserExistence(Username, Password) && userdb.checkUserIsAdmin(Username, Password)) {
                stage.setScene(scene2);
            } else if(userdb.checkUserExistence(Username, Password)) {
                stage.setScene(scene3);
            }
            else {
                System.out.println("Username and/or password is incorrect");
            }
        });

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
        UserDbConnector udb = new UserDbConnector();
        udb.display();

        System.out.println();
        System.out.println(udb.checkUserExistence("", "topgun00"));
        System.out.println(udb.checkUserIsAdmin("topix10", "topgun00"));
    }
}

