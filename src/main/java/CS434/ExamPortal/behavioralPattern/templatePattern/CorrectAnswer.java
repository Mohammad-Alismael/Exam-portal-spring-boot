package CS434.ExamPortal.behavioralPattern.templatePattern;

import CS434.ExamPortal.DAO.AnswerKey;
import CS434.ExamPortal.DAO.UserAnswer;
import CS434.ExamPortal.DTO.QuestionsDTO;
import CS434.ExamPortal.Repositories.AnswerKeyRepository;
import CS434.ExamPortal.Repositories.QuestionRepository2;
import CS434.ExamPortal.Repositories.UserAnswerRepository;
import CS434.ExamPortal.RepositoriesImplement.QuestionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public abstract class CorrectAnswer {
    protected QuestionsDTO questionsDTO;
    @Autowired
    protected AnswerKeyRepository answerKeyRepository;
    @Autowired
    protected UserAnswerRepository userAnswerRepository;
    @Autowired
    protected QuestionRepository2 questionRepository;
    public final void correctingAnswer(){
        fetchCorrectAnswer();
        fetchUserAnswer();
        checkUserAnswer();
        updateScore();
    }
    public Boolean getCorrect() {
        return null;
    }

    public void setCorrect(Boolean correct) {

    }
    public QuestionsDTO getQuestionsDTO() {
        return questionsDTO;
    }

    public void setQuestionsDTO(QuestionsDTO questionsDTO) {
        this.questionsDTO = questionsDTO;
    }

    @Autowired
    public void setAnswerKeyRepository(AnswerKeyRepository answerKeyRepository) {
        this.answerKeyRepository = answerKeyRepository;
    }
    @Autowired
    public void setUserAnswerRepository(UserAnswerRepository userAnswerRepository) {
        this.userAnswerRepository = userAnswerRepository;
    }
    @Autowired
    public void setQuestionRepository(QuestionRepository2 questionRepository) {
        this.questionRepository = questionRepository;
    }

    abstract void fetchCorrectAnswer();
    abstract void fetchUserAnswer();
    abstract void checkUserAnswer();
    abstract void updateScore();
}
