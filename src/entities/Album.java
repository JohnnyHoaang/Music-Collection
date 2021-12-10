package src.entities;
import java.sql.*;
public class Album {
    private String albumid;
    private String title;
    private String category;
    private Date pubdate;
    private String collectionid;
    private String market;
    private String label;

    public Album(String albumid, String title){
        if (albumid == null || title == null){
            throw new IllegalArgumentException("Album id or title cannot be null");
        }
        this.albumid = albumid;
        this.title = title;
    }
    // public Album(String albumid, String category, Date pubdate, String title, String collectionid){
    //     this(albumid, title);
    //     this.category = category;
    //     this.pubdate = pubdate;
    //     this.collectionid = collectionid;
    // }

    public Album(String albumid, String title, String category, Date pubdate, String collectionid, String market, String label){
        this(albumid, title);
        this.category = category;
        this.pubdate = pubdate;
        this.collectionid = collectionid;
        this.market = market;
        this.label = label;
    }

    //Getters
    public String getAlbumid() {
        return this.albumid;
    }
    public String getTitle() {
        return this.title;
    }
    public String getCategory() {
        return this.category;
    }
    public Date getPubdate() {
        return this.pubdate;
    }
    public String getCollectionid() {
        return this.collectionid;
    }
    public String getMarket(){
        return this.market;
    }
    public String getLabel(){
        return this.label;
    }

    public String toString(){
        String printing = "AlbumId: "+this.albumid+" | Title: "+this.title+ " | Category: "+this.category+
            " | PubDate: "+this.pubdate+" | CollectionId: "+this.collectionid+" | Market: "+this.market+" | Label: "+this.label;
        return printing;
    }
}
