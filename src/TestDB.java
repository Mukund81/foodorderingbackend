import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class TestDB {
    public static void main(String[] args) throws Exception {
//        User user = new User(1,"Mukund","Mukund81","Mukund81$",Role.USER);
//        UserDAO.registeruser(user);
//        List<User> users = UserDAO.getAllUsers();
//        System.out.println(users);
//        UserDAO.UpdatePassword(1, "newPassword123");
          UserDAO.DeleteUser(1);
    }
}