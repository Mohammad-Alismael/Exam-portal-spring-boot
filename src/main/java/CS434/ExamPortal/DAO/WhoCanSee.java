package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Who_can_see")
public class WhoCanSee {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "who_sees")
    private String whoSees;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWhoSees() {
        return this.whoSees;
    }

    public void setWhoSees(String whoSees) {
        this.whoSees = whoSees;
    }
}
