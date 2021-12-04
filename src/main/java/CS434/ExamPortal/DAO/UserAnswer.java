package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "User_answer")
public class UserAnswer {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "points")
    private Integer points;

    @Column(name = "is_correct")
    private Integer isCorrect;

    @Column(name = "answered_at")
    private long answeredAt;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getUserAnswer() {
        return this.userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

    public long getAnsweredAt() {
        return this.answeredAt;
    }

    public void setAnsweredAt(long answeredAt) {
        this.answeredAt = answeredAt;
    }
}
