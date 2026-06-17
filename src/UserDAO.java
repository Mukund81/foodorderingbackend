import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    //CREATE OF CRUD
    public static void registeruser(User user) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/foodordermanagement",
                    "root",
                    "Mukund81$"
            );
            String query = "INSERT INTO user(name,username,password,role) VALUES(?,?,?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getName());
            statement.setString(2,user.getUsername());
            statement.setString(3,user.getPassword());
            statement.setString(4,user.getRole().name());
            statement.executeUpdate();
            System.out.println("User registered successfully");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //READ OF CRUD
    public static List<User> getAllUsers()throws ClassNotFoundException{
        List<User> res = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodordermanagement",
                    "root",
                    "Mukund81$");
            String query = "select * from user";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))
                );

                res.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    //UPDATE OF CRUD
    public static void UpdatePassword(int id,String newpassword) throws ClassNotFoundException{
        Connection connection = null;
        PreparedStatement stmt = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodordermanagement",
                    "root",
                    "Mukund81$");
            String query = "update user set password = ? where id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1,newpassword);
            stmt.setInt(2,id);
            int r = stmt.executeUpdate();
            System.out.println(r>=1?"updated successfully":"invalid id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //DELETE OF CRUD
    public static void DeleteUser(int id){
        Connection connection = null;
        PreparedStatement stmt = null;
        try{
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/foodordermanagement",
                    "root",
                    "Mukund81$");
            String query = "delete from user where id = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            int r = stmt.executeUpdate();
            System.out.println(r>=1?"successfully deleted":"Invalid id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
