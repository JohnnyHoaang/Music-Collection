package src.entities;
import java.sql.Date;

public class Compilation {
    private Recording rec;
    private Date date;
    private Album album;
//Declare constructor Compilation with recording, rec date and the album as inputs 
    public Compilation(Recording rec, Date date, Album album){
        if(rec == null || album == null){
            throw new IllegalArgumentException("Recording or album object cannot be null");
        }
        this.rec = rec;
        this.date = date; 
        this.album = album;
    }
   //getter method for the recording
    public Recording getRecording() {
        return this.rec;
    }
    //getter method for the date
    public Date getDate() {
        return this.date;
    }
    //getter method for the album
    public Album getAlbum() {
        return this.album;
    }
 
//toString method 
    public String toString(){
        String printing = "Recording: "+this.rec+" | Album: "+this.album+" | Date: "+this.date;
        return printing;
    }
}
