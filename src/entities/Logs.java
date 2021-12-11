package src.entities;

public class Logs {
    private String username;
    private String changeTrack;

    public Logs(String username){
        if(username == null){
            throw new IllegalArgumentException("username cannot be null!");
        }
        this.username = username;
        this.changeTrack = "";
    }

    public String getUsername() {
        return this.username;
    }
    public void addTrack(String log){
        this.changeTrack += log + '\n';
    }
    public String getChangeTrack() {
        return this.changeTrack;
    }

    public String toString(){
        String printing = "User: "+this.username +" | Track: "+this.changeTrack;
        return printing;
    }
}
