package CS434.ExamPortal.DTO;

import java.sql.Timestamp;

public class ExamsDTO {
    private String examId;
    private String title;
    private Integer score;
    private Integer creatorId;
    private String startingAt;
    private String endingAt;
    private String createdAt;

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

    public String getStartingAt() {
        return this.startingAt;
    }

    public void setStartingAt(String startingAt) {
        this.startingAt = startingAt;
    }

    public String getEndingAt() {
        return this.endingAt;
    }

    public void setEndingAt(String endingAt) {
        this.endingAt = endingAt;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
