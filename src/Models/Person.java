package Models;

public class Person {
    private int ID;
    private String lastName;
    private String firstName;
    private String username;
    private String password;
    private boolean isAdmin = false;

    public Person(int id, String firstName, String lastName, String username, String password, boolean isAdmin){
        ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    //by default person is not admin
    public Person(int id, String firstName, String lastName, String username, String password){
        ID = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public boolean checkIsAdmin(){
        return this.isAdmin;
    }

    public int getID(){
        return ID;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
