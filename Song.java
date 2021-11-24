public class Song {
    private String songId;
    private String name;
    private int songOffset;
    private int durationUsed;

    public Song(String songId, String name, int songOffset, int durationUsed){
        this.songId = songId;
        this.name = name;
        this.songOffset = songOffset;
        this.durationUsed = durationUsed;
    }

    
    public int getDurationUsed() {
        return durationUsed;
    }

    public void setDurationUsed(int durationUsed) {
        this.durationUsed = durationUsed;
    }


    public int getSongOffset() {
        return songOffset;
    }

    public void setSongOffset(int songOffset) {
        this.songOffset = songOffset;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
