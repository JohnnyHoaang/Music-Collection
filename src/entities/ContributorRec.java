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
        return con;
    }

    public Recording getRec() {
        return rec;
    }

    public Role getRole() {
        return role;
    }
}
