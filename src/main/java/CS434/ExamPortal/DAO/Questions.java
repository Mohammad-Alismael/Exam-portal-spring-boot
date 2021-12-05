package CS434.ExamPortal.DAO;

import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;

import javax.persistence.*;

@Entity
@Table(name = "Questions")
public class Questions implements INullObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "question_type")
    private Integer questionType;

    @Column(name = "creator_exam_id")
    private Integer creatorExamId;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "points")
    private Integer points;

    @Column(name = "exam_id")
    private String examId;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "who_can_see")
    private Integer whoCanSee;

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionType() {
        return this.questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getCreatorExamId() {
        return this.creatorExamId;
    }

    public void setCreatorExamId(Integer creatorExamId) {
        this.creatorExamId = creatorExamId;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getExamId() {
        return this.examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public Integer getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getWhoCanSee() {
        return this.whoCanSee;
    }

    public void setWhoCanSee(Integer whoCanSee) {
        this.whoCanSee = whoCanSee;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questionId=" + questionId +
                ", questionType=" + questionType +
                ", creatorExamId=" + creatorExamId +
                ", questionText='" + questionText + '\'' +
                ", points=" + points +
                ", examId='" + examId + '\'' +
                ", isActive=" + isActive +
                ", whoCanSee=" + whoCanSee +
                '}';
    }
}
