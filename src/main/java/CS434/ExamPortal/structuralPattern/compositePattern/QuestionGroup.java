package CS434.ExamPortal.structuralPattern.compositePattern;

import CS434.ExamPortal.DAO.QuestionOptions;
import CS434.ExamPortal.DTO.QuestionsDTO;

import java.util.ArrayList;
import java.util.List;

// song component is question DTO
public class QuestionGroup implements QuestionComponent{

    ArrayList<Question> questions = new ArrayList();

    public void remove(Question question){
        questions.remove(question);
    }

    public void add(Question question) {
        questions.add(question);
    }



    @Override
    public Object getQuestion(int index) {
        return questions.get(index);
    }

    @Override
    public List<QuestionOptions> getQuestionOptions() {
        return null;
    }
}
