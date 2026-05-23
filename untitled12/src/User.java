import java.util.Scanner;

public abstract class User {
    private String username;
    private String password;
    private String name;
    private static final Scanner sc = new Scanner(System.in);

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void register(String newUsername, String newPassword) {
        this.username = newUsername;
        this.password = newPassword;
        System.out.println("Welcome! You have registered successfully.");
    }

    public void login(String inUsername, String inPassword) {
        if (inUsername.equals(username) && inPassword.equals(password)) {
            System.out.println("WELCOME! You have logged in successfully.");
        } else {
            System.out.println("WRONG USERNAME OR PASSWORD. PLEASE TRY AGAIN.");
        }
    }

    public void changePassword(String newPassword) {
        System.out.println("ENTER YOUR CURRENT PASSWORD:");
        String currentPassword = sc.next();

        if (currentPassword.equals(password)) {
            this.password = newPassword;
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("INVALID PASSWORD. CHANGE PASSWORD FAILED.");
        }
    }

    public void displayUserInfo() {
        System.out.println("USERNAME: " + username);
        System.out.println("NAME: " + name);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
