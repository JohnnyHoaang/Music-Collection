package src.entities;

public class Logs {
    private String username;
    private String changeTrack;
//Declare constructor Logs with username as inputs
    public Logs(String username){
        this.username = username;
        this.changeTrack = "";
    }
//Getter method for username
    public String getUsername() {
        return this.username;
    }
//add track to the log user activities
    public void addTrack(String log){
        this.changeTrack += log + '\n';
    }
//Getter method for changetrack 
    public String getChangeTrack() {
        return this.changeTrack;
    }
//toString method 
    public String toString(){
        String printing = "User: "+this.username +" | Track: "+this.changeTrack;
        return printing;
    }
}
