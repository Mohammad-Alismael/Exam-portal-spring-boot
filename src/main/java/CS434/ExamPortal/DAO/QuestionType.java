package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Question_type")
public class QuestionType {
    @Id
    @Column(name = "question_type_id")
    private Integer questionTypeId;

    @Column(name = "which_type")
    private String whichType;

    public Integer getQuestionTypeId() {
        return this.questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getWhichType() {
        return this.whichType;
    }

    public void setWhichType(String whichType) {
        this.whichType = whichType;
    }
}
