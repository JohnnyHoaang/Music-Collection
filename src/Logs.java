package src;

public class Logs {
    private String username;
    private String changeTrack;

    public Logs(String username){
        this.username = username;
        this.changeTrack = "";
    }

    public String getUsername() {
        return username;
    }
    public void addTrack(String log){
        this.changeTrack += log + '\n';
    }
    public String getChangeTrack() {
        return changeTrack;
    }

    public String toString(){

        return "User: "+this.username +" | Track: "+this.changeTrack;
    }
}
