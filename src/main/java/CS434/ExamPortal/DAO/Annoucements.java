package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Annoucements")
public class Annoucements {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "instructor_id")
    private Integer instructorId;

    @Column(name = "annoucment_text")
    private String annoucmentText;

    @Column(name = "created_at")
    private Integer createdAt;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstructorId() {
        return this.instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public String getAnnoucmentText() {
        return this.annoucmentText;
    }

    public void setAnnoucmentText(String annoucmentText) {
        this.annoucmentText = annoucmentText;
    }

    public Integer getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }
}
