package src;
import java.sql.Date;

public class Collection {
    private String collectionId;
    private String name;
    private Date releaseDate;
    private String label;
    private String market;
  
    public Collection(String collectionId, String name, Date releaseDate, String label, String market){
        this.collectionId = collectionId;
        this.name = name;
        this.releaseDate = releaseDate;
        this.label = label;
        this.market = market;
    }

    public String getMarket() {
        return market;
    }

    public String getLabel() {
        return label;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getCollectionId() {
        return collectionId;
    }
}
