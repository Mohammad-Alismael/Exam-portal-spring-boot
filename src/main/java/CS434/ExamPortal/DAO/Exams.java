package CS434.ExamPortal.DAO;

import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;

import javax.persistence.*;

@Entity
@Table(name = "Exams")
public class Exams implements INullObject {
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
    private long startingAt;

    @Column(name = "ending_at")
    private long endingAt;

    @Column(name = "created_at")
    private long createdAt;

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

    public long getStartingAt() {
        return this.startingAt;
    }

    public void setStartingAt(long startingAt) {
        this.startingAt = startingAt;
    }

    public long getEndingAt() {
        return this.endingAt;
    }

    public void setEndingAt(long endingAt) {
        this.endingAt = endingAt;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String toString() {
        return "Exams{" +
                "examId='" + examId + '\'' +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", creatorId=" + creatorId +
                ", startingAt=" + startingAt +
                ", endingAt=" + endingAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
