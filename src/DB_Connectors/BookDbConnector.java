package DB_Connectors;

import Models.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Vector;

public class BookDbConnector {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/library_db";
    private static final String user = "root";
    private static final String password = "topgun10";
    private Connection conn;


    public BookDbConnector(){
        try{
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    public void closeConnection(){
        try {
            conn.close();
        } catch (SQLException ex){
            System.err.println(ex);
        }
    }

    //Return a ObservableList of all books in db
    public ObservableList<Book> getBooks(){
        ObservableList<Book> books = FXCollections.observableArrayList();

        String q = "Select * from books";
        try {
            PreparedStatement statement = conn.prepareStatement(q);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("quantity"),
                        rs.getBoolean("status")
                ));
            }
        }catch (SQLException ex){
            System.err.println(ex);
        }
        return books;
    }

    public Vector<Book> getBooksByGenre(String genre){
        Vector<Book> vec = new Vector<>();

        String q = "Select * from books where genre=?";
        try {
            PreparedStatement statement = conn.prepareStatement(q);
            statement.setString(1, genre);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                vec.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("quantity"),
                        rs.getBoolean("status")
                ));
            }
        }catch (SQLException ex){
            System.err.println(ex);
        }
        return vec;
    }

    public Vector<Book> getBooksByAuthor(String author){
        Vector<Book> vec = new Vector<>();

        String q = "Select * from books where author=?";
        try {
            PreparedStatement statement = conn.prepareStatement(q);
            statement.setString(1, author);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                vec.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("quantity"),
                        rs.getBoolean("status")
                ));
            }
        }catch (SQLException ex){
            System.err.println(ex);
        }
        return vec;
    }

    public ObservableList<Book> getBooksByTitle(String title){
        ObservableList<Book> vec = FXCollections.observableArrayList();

        String q = "Select * from books where title=?";
        try {
            PreparedStatement statement = conn.prepareStatement(q);
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                vec.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getInt("quantity"),
                        rs.getBoolean("status")
                ));
            }
        }catch (SQLException ex){
            System.err.println(ex);
        }
        return vec;
    }

    public void deleteByID(int id){
        String sql_q = "DELETE FROM books where id=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql_q);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A book was deleted successfully!");
            }
            else {
                System.out.println("No such book!!!");
            }
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }

    public void updateBookDetails(int id, String title,String author, String genre, int quantity){
        String sql_q = "Update books SET title=?, author=?, genre=?, quantity=? where id=?";
        try{
            PreparedStatement statement = conn.prepareStatement(sql_q);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, genre);
            statement.setInt(4, quantity);
            statement.setInt(5, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing book was updated successfully!");
            }
            else{
                System.out.println("Problem occurred!!!");
            }
        }catch(SQLException ex){
            System.err.println(ex);
        }
    }


    public void addNewBook(String title,String author, String genre, int quantity){
        String sql_q = "INSERT INTO books (title, author, genre, quantity) VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql_q);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, genre);
            statement.setInt(4, quantity);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new book was inserted successfully!");
            }
            else{
                System.out.println("Problem occurred, book wasn't inserted");
            }
        } catch(SQLException ex){
            System.err.println(ex);
        }

    }
}

