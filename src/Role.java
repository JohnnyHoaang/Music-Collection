package src;
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

}
