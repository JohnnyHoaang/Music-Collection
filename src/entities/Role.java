package src.entities;
public class Role {
    private String roleId;
    private String rolename;

    public Role(String roleId, String rolename){
        if(roleId == null || rolename == null){
            throw new IllegalArgumentException("roleid or rolename cannot be null");
        }
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
