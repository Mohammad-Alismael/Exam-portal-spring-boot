package CS434.ExamPortal.behavioralPattern.templatePattern;

import CS434.ExamPortal.DAO.AnswerKey;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DAO.UserAnswer;

public class CorrectAnswerSingle extends CorrectAnswer{
    private AnswerKey correctAnswer;
    private Boolean isCorrect = false;
    @Override
    void fetchCorrectAnswer() {
        correctAnswer =  answerKeyRepository.findAnswerKeyByQuestionId(userAnswer.getQuestionId());
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
                    .findByQusId(userAnswer.getQuestionId());
            newUserAnswer.setPoints(answeredQuestion.getPoints());

        }else {
            newUserAnswer.setIsCorrect(0);
            newUserAnswer.setPoints(0);
        }
    }
}
