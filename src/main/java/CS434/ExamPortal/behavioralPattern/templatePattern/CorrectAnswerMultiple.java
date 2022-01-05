package CS434.ExamPortal.behavioralPattern.templatePattern;

import CS434.ExamPortal.DAO.AnswerKey;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DAO.UserAnswer;
import CS434.ExamPortal.DTO.QuestionsDTO;
import CS434.ExamPortal.Repositories.AnswerKeyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CorrectAnswerMultiple extends CorrectAnswer{
    protected ArrayList<Boolean> isCorrect = new ArrayList<>();
    protected List<AnswerKey> correctAnswer;
    protected List<UserAnswer> userAnswer;
    @Override
    void fetchCorrectAnswer() {
        correctAnswer = answerKeyRepository.findByQuestionId(questionsDTO.getQuestionId());
    }

    @Override
    void fetchUserAnswer() {
        userAnswer = userAnswerRepository.findUserAnswerById(questionsDTO.getAnswerId());
    }

    @Override
    void checkUserAnswer() {
        for (int i = 0; i < userAnswer.size(); i++) {
            if (userAnswer.get(i).getUserAnswer() != -1) {
                isCorrect.add(
                        correctAnswer.get(i).getCorrectAnswer() == userAnswer.get(i).getUserAnswer()
                );
            }
        }
    }

    @Override
    void updateScore() {
        for (int i = 0; i < isCorrect.size(); i++) {
            if (isCorrect.get(i)){
                userAnswer.get(i).setIsCorrect(1);
                userAnswer.get(i).setPoints(1);
            }else {
                userAnswer.get(i).setIsCorrect(0);
                userAnswer.get(i).setPoints(0);
            }
        }

    }
}
