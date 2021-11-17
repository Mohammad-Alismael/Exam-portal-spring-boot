package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "isActive")
    private Byte isActive;

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

    public Byte getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Integer getWhoCanSee() {
        return this.whoCanSee;
    }

    public void setWhoCanSee(Integer whoCanSee) {
        this.whoCanSee = whoCanSee;
    }
}
