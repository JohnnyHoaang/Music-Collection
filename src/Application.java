package src;

import java.util.*;

public class Application{
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String username = reader.nextLine();  
        String password = reader.nextLine();
        Credentials creds = new Credentials(username, password);
    }
}