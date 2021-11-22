package CS434.ExamPortal.DTO;

public class QuestionsDTO {
    private Integer questionId;
    private Integer questionType;
    private Integer creatorExamId;
    private String questionText;
    private Integer points;
    private String examId;
    private Byte isActive;
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
