package src.entities;


public class Collection {
    private String collectionId;
    private String name;
  
    public Collection(String collectionId, String name){
        this.collectionId = collectionId;
        this.name = name;
    }

    //Get Methods
    public String getCollectionId() {
        return this.collectionId;
    }
    public String getName() {
        return this.name;
    }

    public String toString(){
        String printing = "CollectionId: "+this.collectionId+" | Name: "+this.name;
        return printing;
    }
}
