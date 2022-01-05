package CS434.ExamPortal.DTO;

import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;


@Service
public class QuestionsDTO implements INullObject {
    private Integer questionId;
    private Integer questionType;
    private Integer creatorExamId;
    private String questionText;
    private Integer points;
    private String examId;
    private Integer isActive;
    private Integer whoCanSee;
    private Integer userAnswer;
    private Integer answerId;
    private Integer isCorrect;
    private Integer correctAnswer;

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

    public Integer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Integer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
