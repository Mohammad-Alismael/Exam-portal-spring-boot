package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "which_role")
    private String whichRole;

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getWhichRole() {
        return this.whichRole;
    }

    public void setWhichRole(String whichRole) {
        this.whichRole = whichRole;
    }
}
