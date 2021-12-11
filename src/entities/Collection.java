package src.entities;


public class Collection {
    private String collectionId;
    private String name;
  //Declare constructor Collection  with Collectionid and ita name as inputs 
    public Collection(String collectionId, String name){
        this.collectionId = collectionId;
        this.name = name;
    }

    //Getter method to get collectionid
    public String getCollectionId() {
        return this.collectionId;
    }
    //getter method to get collection name
    public String getName() {
        return this.name;
    }
//toString method 
    public String toString(){
        String printing = "CollectionId: "+this.collectionId+" | Name: "+this.name;
        return printing;
    }
}
