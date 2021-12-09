package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "instructor_id")
    private Integer instructorId;

    @Column(name = "student_id")
    private Integer studentId;

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

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", instructorId=" + instructorId +
                ", studentId=" + studentId +
                '}';
    }
}
