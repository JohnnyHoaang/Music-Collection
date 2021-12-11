package src.entities;


public class Collection {
    private String collectionId;
    private String name;
  
    public Collection(String collectionId, String name){
        if(collectionId == null || name == null){
            throw new IllegalArgumentException("Collection id or name cannot be null!");
        }
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
