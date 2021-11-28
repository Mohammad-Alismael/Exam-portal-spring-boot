package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "question_options")
public class QuestionOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "option_value")
    private String optionValue;

    @Column(name = "question_id")
    private Integer questionId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOptionValue() {
        return this.optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
}
