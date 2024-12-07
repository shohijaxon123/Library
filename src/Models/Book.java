package Models;

public class Book {
    private int ID;
    private String title;
    private String author;
    private String genre;
    private int quantity;
    private boolean status;

    public Book(int id, String title, String author, String genre, int num, boolean status){
        ID = id;
        quantity = num;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }

    public void incrementQuantity() {
        quantity++;
    }


    public int getID(){
        return ID;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getGenre(){
        return genre;
    }
    public Boolean getStatus(){
        return status;
    }
    public int getQuantity(){
        return quantity;
    }
}
