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

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    void fetchCorrectAnswer() {
        System.out.println("fetching correctAnswer..");
        correctAnswer =  answerKeyRepository.findAnswerKeyByQuestionId(questionsDTO.getQuestionId());
        System.out.println(correctAnswer);
    }

    @Override
    void fetchUserAnswer() {
        System.out.println("fetching userAnswer..");
        userAnswer = userAnswerRepository.findUserAnswerById(questionsDTO.getAnswerId()).get(0);
        System.out.println(userAnswer);
    }

    @Override
    void checkUserAnswer() {
        System.out.println("checking user answer");
        isCorrect = userAnswer.getUserAnswer() == correctAnswer.getCorrectAnswer();
        System.out.println(isCorrect);

    }

    @Override
    void updateScore() {
        UserAnswer newUserAnswer  = userAnswer;
        if (isCorrect){
            newUserAnswer.setIsCorrect(1);
            Questions answeredQuestion = questionRepository
                    .findByQuestionId(questionsDTO.getQuestionId());
            newUserAnswer.setPoints(answeredQuestion.getPoints());
        }else {
            newUserAnswer.setIsCorrect(0);
            newUserAnswer.setPoints(0);
        }
        userAnswerRepository.save(newUserAnswer);
        System.out.println(newUserAnswer);
    }
}
