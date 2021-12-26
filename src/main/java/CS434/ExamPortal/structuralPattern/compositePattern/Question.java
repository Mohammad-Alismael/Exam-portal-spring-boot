package CS434.ExamPortal.structuralPattern.compositePattern;

import CS434.ExamPortal.DAO.QuestionOptions;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DTO.QuestionsDTO;
import CS434.ExamPortal.Repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Question implements QuestionComponent{
    Questions question;
    List<QuestionOptions> options;
    private OptionRepository optionRepository;

    public Question(Questions question) {
        this.question = question;
    }

    public Questions getQuestion() {
        return question;
    }

    @Autowired
    public void setOptionRepository(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public Questions getQuestion(int randomNumber) {
        return question;
    }

    @Override
    public List<QuestionOptions> getQuestionOptions() {
        options = optionRepository
                .findQuestionOptionsByQuestionId(question.getQuestionId());
        return options;
    }

    @Override
    public void add(Question q) {

    }
}
