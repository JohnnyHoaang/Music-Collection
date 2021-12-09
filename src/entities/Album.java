package src.entities;
import java.sql.*;
public class Album {
    private String albumid;
    private String category;
    private Date pubdate;
    private String title;
    private String collectionid;

    public Album(String albumid, String title){
        if (albumid == null || title == null){
            throw new IllegalArgumentException("Album id or title cannot be null");
        }
        this.albumid = albumid;
        this.title = title;
    }
    public Album(String albumid,String category, Date pubdate, String title, String collectionid){
        this(albumid, title);
        this.category = category;
        this.pubdate = pubdate;
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

    public String getTitle() {
        return title;
    }

    public String getCollectionid() {
        return collectionid;
    }
}
