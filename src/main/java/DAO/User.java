package DAO;

public class User {
    private final int userId;
    private final String username;
    private final String password;
    private final String permissions;

    private static int nrUsers;

    public User(String username, String password, String permissions) {
        this.userId = nrUsers++;
        this.username = username;
        this.password = password;
        this.permissions = permissions;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPermissions() {
        return permissions;
    }
}
