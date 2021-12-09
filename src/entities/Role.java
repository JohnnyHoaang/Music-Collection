package src.entities;
public class Role {
    private String roleId;
    private String rolename;

    public Role(String roleId, String rolename){
        this.roleId = roleId;
        this.rolename = rolename;
    }

    public String getRoleId() {
        return roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public String toString(){
        String printing = "RoleId: "+this.roleId+" | Role name: "+this.rolename;
        return printing;
    }
}
