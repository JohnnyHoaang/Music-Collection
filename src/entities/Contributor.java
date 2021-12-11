package src.entities;
public class Contributor {
    private String contributorId;
    private String cFirst;
    private String cLast;
//Declare constructor Contributor with contributorid , contributor first and last name as inputs 
    public Contributor(String contributorId, String cFirst, String cLast){
        if(contributorId == null || cFirst == null || cLast == null){
            throw new IllegalArgumentException("contributor id or firstname or lastname cannot be null!");
        }
        this.contributorId = contributorId;
        this.cFirst = cFirst;
        this.cLast = cLast;
    }
//Getter method for contributorid 
    public String getContributorId() {
        return this.contributorId;
    }
    //Getter method for contributor first name
    public String getCfirst() {
        return this.cFirst;
    }
//Getter method for contributor last name
    public String getClast() {
        return this.cLast;
    }
//toString method 
    public String toString(){
        String printing = "ContributorId: "+this.contributorId+" | First Name: "+this.cFirst+" | Last Name: "+this.cLast;
        return printing;
    }


}
