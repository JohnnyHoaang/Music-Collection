package src.entities;
import java.sql.Date;

public class Compilation {
    private Recording rec;
    private Date date;
    private Album album;

    public Compilation(Recording rec, Date date, Album album){
        this.rec = rec;
        this.date = date; 
        this.album = album;
    }
   
    public Recording getRecording() {
        return this.rec;
    }
    public Date getDate() {
        return this.date;
    }
    public Album getAlbum() {
        return this.album;
    }
 

    public String toString(){
        String printing = "Recording: "+this.rec+" | Album: "+this.album+" | Date: "+this.date;
        return printing;
    }
}
