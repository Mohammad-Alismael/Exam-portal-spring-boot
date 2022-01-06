//package CS434.ExamPortal.behavioralPattern.ChainOfResponsibility;
//
//import CS434.ExamPortal.DAO.Questions;
//import CS434.ExamPortal.DTO.QuestionsDTO;
//import CS434.ExamPortal.behavioralPattern.templatePattern.CorrectAnswer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Component
//public class SingleAnswerChain implements Chain{
//    private Chain nextInChain;
//    @Autowired
//    CorrectAnswer correctAnswer;
//
//    @Override
//    public void setNextChain(Chain nextChain) {
//        nextInChain = nextChain;
//    }
//
//    @Override
//    public void getFinalResult(QuestionsDTO questions) {
//        if (questions.getQuestionType() == 1 ||
//            questions.getQuestionType() == 4 ||
//            questions.getQuestionType() == 5
//        ){
//            correctAnswer.correctingAnswer();
//        }else {
//            nextInChain.getFinalResult(questions);
//        }
//
//    }
//
//    @Override
//    @Autowired
//    public void setCorrectAnswer(CorrectAnswer correctAnswer) {
//        this.correctAnswer = correctAnswer;
//    }
//}
