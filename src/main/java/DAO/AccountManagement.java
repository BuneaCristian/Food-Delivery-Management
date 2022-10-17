package DAO;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement {
    public final static List<User> users;

    static {
        users = new ArrayList<>();
        User client = new User("client", "parolac", "client");
        User admin = new User("admin", "parolaa", "admin");
        User employee = new User("employee", "parolae", "employee");
        users.add(client);
        users.add(admin);
        users.add(employee);
    }

    public static void createNewAccount(String username, String password) {
        User u = new User(username, password, "client");
        users.add(u);
    }

    public static User login(String username, String password) {
        for(User u: users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
