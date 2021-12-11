package src.entities;
public class Contributor {
    private String contributorId;
    private String cFirst;
    private String cLast;

    public Contributor(String contributorId, String cFirst, String cLast){
        if(contributorId == null || cFirst == null || cLast == null){
            throw new IllegalArgumentException("contributor id or firstname or lastname cannot be null!");
        }
        this.contributorId = contributorId;
        this.cFirst = cFirst;
        this.cLast = cLast;
    }

    public String getContributorId() {
        return this.contributorId;
    }
    public String getCfirst() {
        return this.cFirst;
    }

    public String getClast() {
        return this.cLast;
    }

    public String toString(){
        String printing = "ContributorId: "+this.contributorId+" | First Name: "+this.cFirst+" | Last Name: "+this.cLast;
        return printing;
    }


}
