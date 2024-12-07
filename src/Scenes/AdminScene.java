package Scenes;

import DB_Connectors.BookDbConnector;
import DB_Connectors.UserDbConnector;
import Models.Book;
//import com.sun.tools.javac.Main;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
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

public class AdminScene {

    UserDbConnector userdb = new UserDbConnector();
    BookDbConnector bookdb = new BookDbConnector();

    ObservableList<Book> books = bookdb.getBooks();

    private Scene scene;
    private Main main; // Reference to Scenes.Main for scene switching

    public AdminScene(Main mainApp) {
        main = mainApp;
        initializeScene();
    }

    public void initializeScene() {
        Label adminPanelLabel = new Label("Admin Panel");
        adminPanelLabel.setFont(new Font("Constantia", 48));
        adminPanelLabel.setTextFill(Color.WHITE);
        Rectangle headerBg = new Rectangle(700, 100);
        headerBg.setFill(Color.BLUE);
        StackPane labelPane = new StackPane();

        Button backToFirstButton = new Button("Log out");
        backToFirstButton.setFont(new Font("Verdana", 20));
        backToFirstButton.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-border-radius: 10; -fx-background-radius: 10;");
        backToFirstButton.setMinSize(250, 70);

        backToFirstButton.setOnAction(event ->
        {
            LoginScene loginScene = new LoginScene(Main.getInstance());
            Main.getInstance().switchScene(loginScene.getScene());
        });

        Button viewAllBooksButton = new Button("View all books");
        viewAllBooksButton.setFont(new Font("Verdana", 20));
        viewAllBooksButton.setStyle("-fx-background-color: #dac32d; -fx-text-fill: white; -fx-border-radius: 10; -fx-background-radius: 10;");
        viewAllBooksButton.setMinSize(250, 70);

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

        Button updateBookButton = new Button("Update book info"); //write almost the same code as in add new book
        updateBookButton.setFont(new Font("Verdana", 20));
        updateBookButton.setStyle("-fx-background-color: #dac32d; -fx-text-fill: white; -fx-border-radius: 10; -fx-background-radius: 10;");
        updateBookButton.setMinSize(250, 70);

        Label updateBookLabel = new Label("Update book");
        updateBookLabel.setFont(new Font("Constantia", 30));
        HBox updateBookBox = new HBox(updateBookLabel);
        updateBookBox.setAlignment(Pos.CENTER);
        updateBookBox.setPadding(new Insets(10));

        Label updateIDLabel = new Label("ID: ");
        updateIDLabel.setFont(new Font("Arial", 18));
        TextField updateIDTextField = new TextField();
        HBox updateIDHBox = new HBox(updateIDLabel, updateIDTextField);
        updateIDHBox.setAlignment(Pos.CENTER);

        Label updateTitleLabel = new Label("Title: ");
        updateTitleLabel.setFont(new Font("Arial", 18));
        TextField updateTitleTextField = new TextField();
        HBox updateTitleHBox = new HBox(updateTitleLabel, updateTitleTextField);
        updateTitleHBox.setAlignment(Pos.CENTER);

        Label updateAuthorLabel = new Label("Author: ");
        updateAuthorLabel.setFont(new Font("Arial", 18));
        TextField updateAuthorTextField = new TextField();
        HBox updateAuthorHBox = new HBox(updateAuthorLabel, updateAuthorTextField);
        updateAuthorHBox.setAlignment(Pos.CENTER);

        Label updateGenreLabel = new Label("Genre: ");
        updateGenreLabel.setFont(new Font("Arial", 18));
        TextField updateGenreTextField = new TextField();
        HBox updateGenreHBox = new HBox(updateGenreLabel, updateGenreTextField);
        updateGenreHBox.setAlignment(Pos.CENTER);

        Label updateQuanityLabel = new Label("Quanity: ");
        updateQuanityLabel.setFont(new Font("Arial", 18));
        TextField updateQuanityTextField = new TextField();
        HBox updateQuanityHBox = new HBox(updateQuanityLabel, updateQuanityTextField);
        updateQuanityHBox.setAlignment(Pos.CENTER);

        Button submitButton4 = new Button("Submit");
        submitButton4.setFont(new Font("Arial", 15));
        submitButton4.setStyle("-fx-background-color: #0088ff; -fx-text-fill: white;");

        Button clearButton4 = new Button("Clear");
        clearButton4.setFont(new Font("Arial", 15));
        clearButton4.setStyle("-fx-background-color: #848d93; -fx-text-fill: white;");

        Button goBackButton3 = new Button("Go Back");
        goBackButton3.setFont(new Font("Verdana", 15));
        goBackButton3.setMinSize(10, 5);
        goBackButton3.setStyle("-fx-background-color: #e4364a; -fx-text-fill: white;");

        goBackButton3.setOnAction(e -> {
            AdminScene adminScene = new AdminScene(Main.getInstance());
            Main.getInstance().switchScene(adminScene.getScene());
        });

        HBox actionHBox4 = new HBox(submitButton4, clearButton4, goBackButton3);
        actionHBox4.setSpacing(20);
        actionHBox4.setAlignment(Pos.CENTER);

        VBox updateBookLayout = new VBox(10);
        updateBookLayout.getChildren().addAll(updateIDHBox, updateTitleHBox, updateAuthorHBox, updateGenreHBox, updateQuanityHBox, actionHBox4);
        BorderPane updateBookPane = new BorderPane();
        updateBookPane.setTop(updateBookBox);
        updateBookPane.setCenter(updateBookLayout);

        Scene updateBookScene = new Scene(updateBookPane, 500, 300);

        submitButton4.setOnAction(e -> {
            String ID = updateIDTextField.getText();
            int ID2 = Integer.parseInt(ID);
            String Title = updateTitleTextField.getText();
            String Author = updateAuthorTextField.getText();
            String Genre = updateGenreTextField.getText();
            String Quantity = updateQuanityTextField.getText();
            int Quantity2 = Integer.parseInt(Quantity);


            bookdb.updateBookDetails(ID2, Title, Author, Genre, Quantity2);
        });

        updateBookButton.setOnAction(e -> {
            Main.getInstance().switchScene(updateBookScene);
        });


        Button btn7 = new Button("Delete book"); //delete book by id
        btn7.setFont(new Font("Verdana", 20));
        btn7.setStyle("-fx-background-color: #dac32d; -fx-text-fill: white; -fx-border-radius: 10; -fx-background-radius: 10;");
        btn7.setMinSize(250, 70);


        Label searchBookLabel = new Label("Search Book");
        searchBookLabel.setFont(new Font("Verdana", 20));
        TextField searchBookTextField = new TextField();
        String titleSearch = searchBookTextField.getText();

        ObservableList<Book> olBook = bookdb.getBooksByTitle(titleSearch);

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
            AdminScene adminScene = new AdminScene(Main.getInstance());
            Main.getInstance().switchScene(adminScene.getScene());
        });

        VBox tableViewVBox = new VBox(searchBookHBox, tableView, goBackButton);
        tableViewVBox.setAlignment(Pos.CENTER);
        tableViewVBox.setSpacing(10);
        Scene tableScene = new Scene(tableViewVBox, 700, 500);

        viewAllBooksButton.setOnAction(e -> {
            Main.getInstance().switchScene(tableScene);
        });


        /*FilteredList<Book> filteredData = new FilteredList<>(books, b-> true);
        searchBookTextField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredList.setPredicate(bookdb -> { // If filter text is empty, display all books
                if (newValue == null || newValue.isEmpty()) { return true; }
                // Compare book title with filter text
                String lowerCaseFilter = newValue.toLowerCase();
                if (bookdb..toLowerCase().contains(lowerCaseFilter)) { return true; // Filter matches title
                } else if (book.getAuthor().toLowerCase().contains(lowerCaseFilter)) { return true; // Filter matches author
                } else if (book.getGenre().toLowerCase().contains(lowerCaseFilter)) { return true; // Filter matches genre
                     } else { return false; // Does not match
                     } }); });*/



        Button manageAdmins = new Button("Manage Admins");
        manageAdmins.setFont(new Font("Verdana", 20));
        manageAdmins.setStyle("-fx-background-color: #848d93; -fx-text-fill: white; -fx-border-radius: 10; -fx-background-radius: 10;");
        manageAdmins.setMinSize(250, 70);




        Button addNewBook = new Button("Add new book");
        addNewBook.setFont(new Font("Verdana", 20));
        addNewBook.setStyle("-fx-background-color: #dac32d; -fx-text-fill: white; -fx-border-radius: 10; -fx-background-radius: 10;");
        addNewBook.setMinSize(250, 70);

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
        submitButton3.setFont(new Font("Arial", 15));
        submitButton3.setStyle("-fx-background-color: #0088ff; -fx-text-fill: white;");

        Button clearButton3 = new Button("Clear");
        clearButton3.setFont(new Font("Arial", 15));
        clearButton3.setStyle("-fx-background-color: #848d93; -fx-text-fill: white;");

        Button goBackButton2 = new Button("Go Back");
        goBackButton2.setFont(new Font("Verdana", 15));
        goBackButton2.setMinSize(10, 5);
        goBackButton2.setStyle("-fx-background-color: #e4364a; -fx-text-fill: white;");

        goBackButton2.setOnAction(e -> {
            AdminScene adminScene = new AdminScene(Main.getInstance());
            Main.getInstance().switchScene(adminScene.getScene());
        });

        HBox actionHBox3 = new HBox(submitButton3, clearButton3, goBackButton2);
        actionHBox3.setSpacing(20);
        actionHBox3.setAlignment(Pos.CENTER);

        VBox addNewBookLayout = new VBox(10);
        addNewBookLayout.getChildren().addAll(addTitleHBox, addAuthorHBox, addGenreHBox, addQuanityHBox, actionHBox3);
        BorderPane addNewBookPane = new BorderPane();
        addNewBookPane.setTop(addNewBookBox);
        addNewBookPane.setCenter(addNewBookLayout);

        Scene addNewBookScene = new Scene(addNewBookPane, 500, 300);

        addNewBook.setOnAction(e -> {
            Main.getInstance().switchScene(addNewBookScene);
        });


        submitButton3.setOnAction(e -> {
            String Title = addTitleTextField.getText();
            String Author = addAuthorTextField.getText();
            String Genre = addGenreTextField.getText();
            String Quantity = addQuanityTextField.getText();
            int Quantity2 = Integer.parseInt(Quantity);
            bookdb.addNewBook(Title, Author, Genre, Quantity2);
        });





        VBox layout2 = new VBox(10);
        labelPane.getChildren().addAll(headerBg, adminPanelLabel);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(labelPane, viewAllBooksButton, addNewBook, updateBookButton, btn7, manageAdmins, backToFirstButton);
        scene = new Scene(layout2, 700, 600);

    }
    public Scene getScene() {
        return scene;
    }
}
