package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Answer_key")
public class AnswerKey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_key_id")
    private Integer answerKeyId;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "correct_answer")
    private String correctAnswer;

    public Integer getAnswerKeyId() {
        return this.answerKeyId;
    }

    public void setAnswerKeyId(Integer answerKeyId) {
        this.answerKeyId = answerKeyId;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
