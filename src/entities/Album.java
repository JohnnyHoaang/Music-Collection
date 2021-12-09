package src.entities;
import java.sql.*;
public class Album {
    private String albumid;
    private String category;
    private Date pubdate;
    private String name;
    private String collectionid;

    public Album(String albumid,String category, Date pubdate, String name, String collectionid){
        this.albumid = albumid;
        this.category = category;
        this.pubdate = pubdate;
        this.name = name;
        this.collectionid = collectionid;
    }

    public String getAlbumid() {
        return albumid;
    }

    public String getCategory() {
        return category;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public String getName() {
        return name;
    }

    public String getCollectionid() {
        return collectionid;
    }

    public String toString(){
        String printing = "AlbumId: "+this.albumid+" | Category: "+" | PubDate: "+this.pubdate+" | Name: "+this.name+" | CollectionId: "+this.collectionid;
        return printing;
    }
}
