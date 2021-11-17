package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "User_answer")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "questionID")
    private Integer questionId;

    @Column(name = "answered_at")
    private String answeredAt;

    @Column(name = "points_each_question")
    private Integer pointsEachQuestion;

    @Column(name = "isCorrect")
    private Byte isCorrect;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAnswer() {
        return this.userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnsweredAt() {
        return this.answeredAt;
    }

    public void setAnsweredAt(String answeredAt) {
        this.answeredAt = answeredAt;
    }

    public Integer getPointsEachQuestion() {
        return this.pointsEachQuestion;
    }

    public void setPointsEachQuestion(Integer pointsEachQuestion) {
        this.pointsEachQuestion = pointsEachQuestion;
    }

    public Byte getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(Byte isCorrect) {
        this.isCorrect = isCorrect;
    }
}
