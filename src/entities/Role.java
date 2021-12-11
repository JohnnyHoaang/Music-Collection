package src.entities;
public class Role {
    private String roleId;
    private String rolename;
    //Declare constructor Role with roleId, rolename as inputs 

    public Role(String roleId, String rolename){
        if(roleId == null || rolename == null){
            throw new IllegalArgumentException("roleid or rolename cannot be null");
        }
        this.roleId = roleId;
        this.rolename = rolename;
    }
 //getter for roleid 
    public String getRoleId() {
        return roleId;
    }
//getter for role name 
    public String getRolename() {
        return rolename;
    }
//toString method 
    public String toString(){
        String printing = "RoleId: "+this.roleId+" | Role name: "+this.rolename;
        return printing;
    }
}
