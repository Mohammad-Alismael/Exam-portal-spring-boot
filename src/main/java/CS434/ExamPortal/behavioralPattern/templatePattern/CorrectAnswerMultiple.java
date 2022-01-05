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
        userAnswer = userAnswerRepository.findUserAnswerByUserIdAndQuestionId(
                questionsDTO.getWhoCanSee(),
                questionsDTO.getQuestionId());
    }

    @Override
    void checkUserAnswer() {
        isCorrect.clear();
        for (int i = 0; i < userAnswer.size(); i++) {
            if (userAnswer.get(i).getUserAnswer() != -1) {
                boolean isItCorrect = correctAnswer.get(i).getCorrectAnswer() == userAnswer.get(i).getUserAnswer();
                isCorrect.add(isItCorrect);
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
            userAnswerRepository.save(userAnswer.get(i));
        }

    }
}
