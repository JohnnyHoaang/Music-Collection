package src;

public class Credentials {
    private String user;
    private String password;
    //Create the Credentials constructor with user's username and password
    public Credentials(String user, String password){
        this.user = user;
        this.password = password;
    }
    //getter method for username
    public String getUser() {
        return user;
    }
    //getter method for the password
    public String getPassword() {
        return password;
    }
}
