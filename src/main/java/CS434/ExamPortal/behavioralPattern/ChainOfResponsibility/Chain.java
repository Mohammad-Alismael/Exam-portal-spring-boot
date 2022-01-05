package CS434.ExamPortal.behavioralPattern.ChainOfResponsibility;

import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DTO.QuestionsDTO;
import CS434.ExamPortal.behavioralPattern.templatePattern.CorrectAnswer;

public interface Chain {
    public void setNextChain(Chain nextChain);
    public void getFinalResult(QuestionsDTO questions);
    public void setCorrectAnswer(CorrectAnswer correctAnswer);
}
