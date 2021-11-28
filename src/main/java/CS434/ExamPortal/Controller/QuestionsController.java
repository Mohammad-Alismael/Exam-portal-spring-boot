package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.QuestionRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class QuestionsController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add-question") // Map ONLY POST Requests
    public @ResponseBody
    ResponseStatusException storeQuestion (@RequestBody Questions question) {
       Users user = userRepository.findByUserId(question.getCreatorExamId());
        if (user.getRoleId() != 1){
           throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
       }
        System.out.println(question);
       questionRepository.save(question);

       return  new ResponseStatusException(HttpStatus.CREATED);

    }

    @PostMapping(path="/add-question2") // Map ONLY POST Requests
    public @ResponseBody
    Questions storeQuestion2 (@RequestBody Questions question) {

        return question;

    }

    @PostMapping(path="/update-question") // Map ONLY POST Requests
    public @ResponseBody
    ResponseStatusException updateQuestion (@RequestBody Questions question) {
        Users user = userRepository.findByUserId(question.getCreatorExamId());
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        Questions quest = questionRepository.findByQuestionId(question.getQuestionId());
        quest.setQuestionText(question.getQuestionText());
        quest.setPoints(question.getPoints());
        // the rest
        questionRepository.save(quest);
        return  new ResponseStatusException(HttpStatus.ACCEPTED);

    }

    @PostMapping(path="/delete-question")
    public @ResponseBody
    ResponseStatusException deleteQuestion (@RequestBody Questions question) {
        Users user = userRepository.findByUserId(question.getCreatorExamId());
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        Questions quest = questionRepository.findByQuestionId(question.getQuestionId());
        quest.setIsActive(0);
        questionRepository.save(quest);
        return  new ResponseStatusException(HttpStatus.ACCEPTED);

    }

    @GetMapping (path="/list-questions")
    public @ResponseBody
    List<Questions> listQuestions (@RequestBody Exams exam) {
        String examId = exam.getExamId();
        Integer creatorId = exam.getCreatorId();
        return  questionRepository.findQuestionsByExamIdAndIsActiveAndCreatorExamId(examId,1,creatorId);
    }

    @GetMapping (path="/list-question-by-id")
    public @ResponseBody
    Object listQuestionById (@RequestBody Questions question) {
        String examId = question.getExamId();
        Integer questionId = question.getQuestionId();
        Questions q = questionRepository
                .findQuestionsByQuestionIdAndAndExamIdAndIsActive(questionId,examId,1);
        if (q == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you have no permission!");
        }

        return q;
    }

    @GetMapping (path="/list-questions-all")
    public @ResponseBody
    List<Questions> listQuestions () {
        return questionRepository.findAllQuestions();
    }
}
