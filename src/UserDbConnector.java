import java.sql.*;

public class UserDbConnector {
        private static final String url = "jdbc:mysql://127.0.0.1:3306/library_db";
        private static final String user = "root";
        private static final String password = "topgun10";
        private Connection conn;

        public UserDbConnector(){
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

        public boolean checkUserExistence(String username, String password){
            String q = "Select * from user where username = ? and password = ?";
            boolean val = false;
            try {
                PreparedStatement statement = conn.prepareStatement(q);
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet rs = statement.executeQuery();
                val = rs.next();
            } catch (SQLException ex){
                System.err.println(ex);
            }
            return val;
        }

        public boolean checkUserIsAdmin(String username, String password){
            String q = "Select * from user where username = ? and password = ?";
            boolean val = false;

            try {
                PreparedStatement statement = conn.prepareStatement(q);
                statement.setString(1, username);
                statement.setString(2, password);

                ResultSet rs = statement.executeQuery();
                if (rs.next()){
                    val = rs.getBoolean("isAdmin");
                }
            } catch (SQLException ex) {
                System.err.println(ex);
            }
            return val;
        }

        //display data on console to test output
        public void display(){
            String q = "Select * from user";
            try {
                PreparedStatement statement = conn.prepareStatement(q);
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    System.out.println(rs.getString("username")+ "  ---  " + rs.getBoolean("isAdmin")+ "  ---  " + rs.getString("password"));
                }
            } catch (SQLException ex){
                System.err.println(ex);
            }
        }

        public void addNewUser(String lastName, String firstName, String username, String password){
            String query = "INSERT INTO user (lastName, firstname, username, password) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                // Set the parameters for the query
                statement.setString(1, lastName);
                statement.setString(2, firstName);
                statement.setString(3, username);
                statement.setString(4, password);

                // Execute the update
                int rowsInserted = statement.executeUpdate();
            } catch (SQLException ex) {
                System.err.println("Error inserting user: " + ex.getMessage());
            }
        }

}