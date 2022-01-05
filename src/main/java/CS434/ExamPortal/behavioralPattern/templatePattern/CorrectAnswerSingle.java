package CS434.ExamPortal.behavioralPattern.templatePattern;

import CS434.ExamPortal.DAO.AnswerKey;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DAO.UserAnswer;
import CS434.ExamPortal.DTO.QuestionsDTO;

import java.util.List;

public class CorrectAnswerSingle extends CorrectAnswer{
    protected Boolean isCorrect = false;
    protected AnswerKey correctAnswer;
    protected UserAnswer userAnswer;
    @Override
    void fetchCorrectAnswer() {
        correctAnswer =  answerKeyRepository.findAnswerKeyByQuestionId(questionsDTO.getQuestionId());
    }

    @Override
    void fetchUserAnswer() {
        userAnswer = userAnswerRepository.findUserAnswerByIdV2(questionsDTO.getAnswerId());
    }

    @Override
    void checkUserAnswer() {
        isCorrect = userAnswer.getUserAnswer() == correctAnswer.getCorrectAnswer();
    }

    @Override
    void updateScore() {
        UserAnswer newUserAnswer  = userAnswer;
        if (isCorrect){

            newUserAnswer.setIsCorrect(1);
            Questions answeredQuestion = questionRepositoryImpl
                    .findByQusId(questionsDTO.getQuestionId());
            newUserAnswer.setPoints(answeredQuestion.getPoints());
            questionRepositoryImpl.save(answeredQuestion);
        }else {
            newUserAnswer.setIsCorrect(0);
            newUserAnswer.setPoints(0);
        }
    }
}
