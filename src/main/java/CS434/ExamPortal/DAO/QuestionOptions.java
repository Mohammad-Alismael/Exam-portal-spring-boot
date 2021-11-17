package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Question_options")
public class QuestionOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "choise_value")
    private String choiseValue;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getChoiseValue() {
        return this.choiseValue;
    }

    public void setChoiseValue(String choiseValue) {
        this.choiseValue = choiseValue;
    }
}
