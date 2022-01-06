package CS434.ExamPortal.structuralPattern.compositePattern;

import CS434.ExamPortal.DAO.AnswerKey;
import CS434.ExamPortal.DAO.QuestionOptions;
import CS434.ExamPortal.DAO.UserAnswer;

import java.util.List;

public interface QuestionComponent {

    Object getQuestion(int randomNumber);
    List<QuestionOptions> getQuestionOptions();
    List<AnswerKey> getAnswerKeys();
    List<UserAnswer> getUserAnswer();
    void add(Question q);
}
