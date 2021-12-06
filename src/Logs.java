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

    public String getChangeTrack() {
        return changeTrack;
    }
}
