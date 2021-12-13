package CS434.ExamPortal.behavioralPattern.templatePattern;

import CS434.ExamPortal.DAO.UserAnswer;
import CS434.ExamPortal.Repositories.AnswerKeyRepository;
import CS434.ExamPortal.Repositories.UserAnswerRepository;
import CS434.ExamPortal.RepositoriesImplement.QuestionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class CorrectAnswer {
    protected UserAnswer userAnswer;
    protected AnswerKeyRepository answerKeyRepository;
    protected UserAnswerRepository userAnswerRepository;
    protected QuestionRepositoryImpl questionRepositoryImpl;
    public final void correctingAnswer(){
        fetchCorrectAnswer();
        checkUserAnswer();
        updateScore();
    }

    public UserAnswer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(UserAnswer userAnswer) {
        this.userAnswer = userAnswer;
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
    public void setQuestionRepositoryImpl(QuestionRepositoryImpl questionRepositoryImpl) {
        this.questionRepositoryImpl = questionRepositoryImpl;
    }

    abstract void fetchCorrectAnswer();
    abstract void checkUserAnswer();
    abstract void updateScore();
}
