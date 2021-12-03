package src;
public class Contributor {
    private String contributorId;
    private String cfirst;
    private String cLast;

    public Contributor(String contributorId, String cfirst, String cLast){
        this.contributorId = contributorId;
        this.cfirst = cfirst;
        this.cLast = cLast;
    }

    public String getContributorId() {
        return contributorId;
    }
    public String getCfirst() {
        return cfirst;
    }

    public String getcLast() {
        return cLast;
    }



}
