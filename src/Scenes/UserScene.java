package Scenes;

import DB_Connectors.BookDbConnector;
import DB_Connectors.UserDbConnector;
import Models.Book;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class UserScene {
    UserDbConnector userdb = new UserDbConnector();
    BookDbConnector bookdb = new BookDbConnector();
    ObservableList<Book> books = bookdb.getBooks();

    private Scene scene;
    private Main main;

    public UserScene(Main mainApp) {
        mainApp = main;
        initializeScene();
    }

    public void initializeScene() {
        Label appLabel = new Label("Welcome to Library");
        appLabel.setFont(new Font("Constantia", 48));
        appLabel.setTextFill(Color.WHITE);
        Rectangle bg = new Rectangle(700, 100);
        bg.setFill(Color.ORANGERED);
        StackPane labelPane = new StackPane();

        Button logOutButton = new Button("Log Out");
        logOutButton.setFont(new Font("Verdana", 20));
        logOutButton.setStyle("-fx-background-color: #e4364a; -fx-text-fill: white;");
        logOutButton.setMinSize(250, 70);

        logOutButton.setOnAction(e ->{
            LoginScene scene = new LoginScene(Main.getInstance());
            Main.getInstance().switchScene(scene.getScene());
        });

        Button getListButton = new Button("Get List Of All Books");
        getListButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        getListButton.setMinSize(250, 70);
        getListButton.setFont(new Font("Verdana", 20));

        TableView<Book> tableView = new TableView<>();
        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
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
        tableView.setItems(books);

        Label searchBookLabel = new Label("Search Book");
        searchBookLabel.setFont(new Font("Verdana", 20));
        TextField searchBookTextField = new TextField();
        searchBookTextField.setPromptText("Keywrods...");
        searchBookTextField.setPrefWidth(200);
        HBox searchBookHBox = new HBox(searchBookLabel, searchBookTextField);
        searchBookHBox.setSpacing(10);
        searchBookHBox.setAlignment(Pos.CENTER);

        Button goBackButton = new Button("Go Back");
        goBackButton.setFont(new Font("Verdana", 15));
        goBackButton.setMinSize(10, 5);
        goBackButton.setStyle("-fx-background-color: #e4364a; -fx-text-fill: white;");

        goBackButton.setOnAction(e -> {
            UserScene scene = new UserScene(Main.getInstance());
            Main.getInstance().switchScene(scene.getScene());
        });

        VBox tableViewVBox = new VBox(searchBookHBox, tableView, goBackButton);
        tableViewVBox.setAlignment(Pos.CENTER);
        tableViewVBox.setSpacing(10);
        Scene tableScene = new Scene(tableViewVBox, 700, 500);

        getListButton.setOnAction(e -> {
            Main.getInstance().switchScene(tableScene);
        });

        Button reserveBookButton = new Button("Reserve A Book");
        reserveBookButton.setStyle("-fx-background-color: #848d93; -fx-text-fill: white;");
        reserveBookButton.setMinSize(250, 70);
        reserveBookButton.setFont(new Font("Verdana", 20));

        Label idLabel = new Label("Enter ID of book for reserve: ");
        idLabel.setFont(new Font("Verdana", 20));
        TextField idTextField = new TextField();
        String bookres = idTextField.getText();
        //Integer bookr = Integer.parseInt(bookres);

        Button gback = new Button("Go Back");
        gback.setOnAction(e ->{
            UserScene scene = new UserScene(Main.getInstance());
            Main.getInstance().switchScene(scene.getScene());
        });

        HBox labelHBox7 = new HBox(idLabel, idTextField, gback);

        Scene reserveBookScene = new Scene(labelHBox7, 700, 500);


        reserveBookButton.setOnAction(e ->{
            Main.getInstance().switchScene(reserveBookScene);
        });

        Button addBookRequest = new Button("Add Book Request");
        addBookRequest.setStyle("-fx-background-color: #938485; -fx-text-fill: white;");
        addBookRequest.setMinSize(250, 70);
        addBookRequest.setFont(new Font("Verdana", 20));

        VBox layout1 = new VBox(20);
        labelPane.getChildren().addAll(bg, appLabel);
        layout1.getChildren().addAll(labelPane, getListButton, reserveBookButton, addBookRequest, logOutButton);
        VBox buttonBox = new VBox(20, labelPane, layout1);
        buttonBox.setStyle("-fx-alignment: center;");

        layout1.setStyle("-fx-alignment: center;");
        logOutButton.setAlignment(Pos.CENTER);

        BorderPane root2 = new BorderPane();
        root2.setCenter(buttonBox);
        scene = new Scene(root2, 700, 600);
    }

    public Scene getScene() {
        return scene;
    }

}
