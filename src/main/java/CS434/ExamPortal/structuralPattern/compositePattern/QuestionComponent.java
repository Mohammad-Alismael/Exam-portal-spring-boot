package CS434.ExamPortal.structuralPattern.compositePattern;

import CS434.ExamPortal.DAO.QuestionOptions;

import java.util.List;

public interface QuestionComponent {

    Object getQuestion(int randomNumber);
    List<QuestionOptions> getQuestionOptions();

    void add(Question q);
}
