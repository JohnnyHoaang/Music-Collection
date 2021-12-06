package src;

public class Logs {
    private String username;
    private String changeTrack;

    public Logs(String username, String changeTrack){
        this.username = username;
        this.changeTrack = changeTrack;
    }

    public String getUsername() {
        return username;
    }
    public void addTrack(String log){
        this.changeTrack += '\n'  + log;
    }
    public String getChangeTrack() {
        return changeTrack;
    }
}
