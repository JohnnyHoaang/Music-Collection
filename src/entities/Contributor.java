package src.entities;
public class Contributor {
    private String contributorId;
    private String cFirst;
    private String cLast;

    public Contributor(String contributorId, String cFirst, String cLast){
        this.contributorId = contributorId;
        this.cFirst = cFirst;
        this.cLast = cLast;
    }

    public String getContributorId() {
        return contributorId;
    }
    public String getcFirst() {
        return cFirst;
    }

    public String getClast() {
        return cLast;
    }

    public String toString(){
        String printing = "ContributorId: "+this.contributorId+" | First Name: "+this.cFirst+" | Last Name: "+this.cLast;
        return printing;
    }


}
