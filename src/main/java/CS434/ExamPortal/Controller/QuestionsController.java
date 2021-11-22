package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

public class QuestionsController {
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping(path="/add-question") // Map ONLY POST Requests
    public @ResponseBody
    ResponseStatusException storeQuestion (@RequestBody Questions question) {
       if (question.getCreatorExamId() != 1){
           throw new ResponseStatusException(HttpStatus.FORBIDDEN, "incorrect username or password");
       }
       questionRepository.save(question);

       return  new ResponseStatusException(HttpStatus.ACCEPTED);

    }

    @PostMapping(path="/update-question") // Map ONLY POST Requests
    public @ResponseBody
    ResponseStatusException updateQuestion (@RequestBody Questions question) {
        if (question.getCreatorExamId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "incorrect username or password");
        }
        Questions quest = questionRepository.findQuestionsByQuestionId(question.getQuestionId());
        quest.setQuestionText(question.getQuestionText());
        quest.setPoints(question.getPoints());
        // the rest
        return  new ResponseStatusException(HttpStatus.ACCEPTED);

    }

    @PostMapping(path="/delete-question") // Map ONLY POST Requests
    public @ResponseBody
    ResponseStatusException deleteQuestion (@RequestBody Questions question) {
        if (question.getCreatorExamId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "incorrect username or password");
        }
        Questions quest = questionRepository.findQuestionsByQuestionId(question.getQuestionId());
        quest.setIsActive(false);
        questionRepository.save(quest);
        return  new ResponseStatusException(HttpStatus.ACCEPTED);

    }
}