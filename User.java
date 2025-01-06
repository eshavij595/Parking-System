package Data;

public class User {
    public enum UserType {
        REGISTERED_CLIENT,
        UNREGISTERED_CLIENT,
        MANAGER,
        SUPER_MANAGER
    }
    public int userID;
    public String name;
    public String email;
    public String username;
    public String password;
    public UserType userType;
    
    public User(int userID, String name, String email, String username, String password, UserType userType) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
}
