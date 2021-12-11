package src.entities;
public class ContributorRec {
    private Contributor con;
    private Recording rec;
    private Role role;
//Declare constructor ContributorRec with contributor object , Recording object and Role object
    public ContributorRec(Contributor con, Recording rec, Role role){
        if(con == null || rec == null || role == null){
            throw new IllegalArgumentException("Contributor or recording or role objects cannot be null!");
        }
        this.con = con;
        this.rec = rec;
        this.role = role;
    }
//Getter method for contributor object
    public Contributor getCon() {
        return this.con;
    }
//Getter method for Recording object
    public Recording getRec() {
        return this.rec;
    }
//Getter method for Role object
    public Role getRole() {
        return this.role;
    }
//toString method 
    public String toString(){
        String printing = this.con+" | "+this.rec+" | "+this.role;
        return printing;
    }
}
