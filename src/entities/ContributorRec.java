package src.entities;

public class ContributorRec {
    private Contributor con;
    private Recording rec;
    private Role role;

    public ContributorRec(Contributor con, Recording rec, Role role){
        this.con = con;
        this.rec = rec;
        this.role = role;
    }

    public Contributor getCon() {
        return this.con;
    }

    public Recording getRec() {
        return this.rec;
    }

    public Role getRole() {
        return this.role;
    }
    public String toString(){
        String printing = this.con+" | "+this.rec+" | "+this.role;
        return printing;
    }
}
