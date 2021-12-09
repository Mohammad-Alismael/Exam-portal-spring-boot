package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "announcement_id")
    private Integer announcementId;

    @Column(name = "seen")
    private Integer seen;

    @Column(name = "student_id")
    private Integer studentId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnnouncementId() {
        return this.announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    public Integer getSeen() {
        return this.seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
