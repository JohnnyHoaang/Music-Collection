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

//Declare constructor Album with albumid, title, category, pubdate, collectionid,market and label as the inputs 
    public Album(String albumid, String title, String category, Date pubdate, String collectionid, String market, String label){
        this(albumid, title);
        this.category = category;
        this.pubdate = pubdate;
        this.collectionid = collectionid;
        this.market = market;
        this.label = label;
    }

    //Getter method for the albumid
    public String getAlbumid() {
        return this.albumid;
    }
    //getter methodfor the title
    public String getTitle() {
        return this.title;
    }
    //getter method for the category 
    public String getCategory() {
        return this.category;
    }
    //getter method for the pubdate 
    public Date getPubdate() {
        return this.pubdate;
    }
    //getter method for the collectionid
    public String getCollectionid() {
        return this.collectionid;
    }
    //getter method for the category 
    public String getMarket(){
        return this.market;
    }
    //getter method for the label
    public String getLabel(){
        return this.label;
    }
    
//toString method 
    public String toString(){
        String printing = "AlbumId: "+this.albumid+" | Title: "+this.title+ " | Category: "+this.category+
            " | PubDate: "+this.pubdate+" | CollectionId: "+this.collectionid+" | Market: "+this.market+" | Label: "+this.label;
        return printing;
    }
}
