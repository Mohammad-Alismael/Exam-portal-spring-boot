package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Exams")
public class Exams {
    @Id
    @Column(name = "exam_id")
    private String examId;

    @Column(name = "title")
    private String title;

    @Column(name = "score")
    private Integer score;

    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "starting_at")
    private java.sql.Timestamp startingAt;

    @Column(name = "ending_at")
    private java.sql.Timestamp endingAt;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    public String getExamId() {
        return this.examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public java.sql.Timestamp getStartingAt() {
        return this.startingAt;
    }

    public void setStartingAt(java.sql.Timestamp startingAt) {
        this.startingAt = startingAt;
    }

    public java.sql.Timestamp getEndingAt() {
        return this.endingAt;
    }

    public void setEndingAt(java.sql.Timestamp endingAt) {
        this.endingAt = endingAt;
    }

    public java.sql.Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
