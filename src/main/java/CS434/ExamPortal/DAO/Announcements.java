package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Announcements")
public class Announcements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "instructor_id")
    private Integer instructorId;

    @Column(name = "announcement_text")
    private String announcementText;

    @Column(name = "created_at")
    private Long createdAt;

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

    public String getAnnouncementText() {
        return this.announcementText;
    }

    public void setAnnouncementText(String announcementText) {
        this.announcementText = announcementText;
    }

    public Long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
