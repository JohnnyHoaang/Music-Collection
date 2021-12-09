package src.entities;
import java.sql.Date;

import src.entities.Album;

public class Compilation {
    
    private Date date;
    private Recording rec;
    private Album album;
    private String compilationid;

    public Compilation(Recording rec, Album album, Date date, String compilationid){
        this.rec = rec;
        this.album = album;
        this.date = date;  
        this.compilationid = compilationid;
    }

    public Date getDate() {
        return date;
    }
    public Recording getRec() {
        return rec;
    }
    public Album getAlbum() {
        return album;
    }
    public String getCompilationid(){
        return this.compilationid;
    }

    public String toString(){
        String printing = "Recording: "+this.rec+" | Album: "+this.album+" | Date: "+this.date+" | CompilationId: "+this.compilationid;
        return printing;
    }
}
