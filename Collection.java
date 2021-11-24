import java.sql.Date;

public class Collection {
    private String collectionId;
    private String songId;
    private Date releaseDate;
    private String label;
    private String market;
  
    public Collection(String collectionId, String songId, Date releaseDate, String label, String market){
        this.collectionId = collectionId;
        this.songId = songId;
        this.releaseDate = releaseDate;
        this.label = label;
        this.market = market;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }
}
