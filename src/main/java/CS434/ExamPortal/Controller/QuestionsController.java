package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.ExamRepository;
import CS434.ExamPortal.Repositories.OptionRepository;
import CS434.ExamPortal.Repositories.QuestionRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class QuestionsController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private OptionRepository optionRepository;

    @PostMapping(path="/add-question") // Map ONLY POST Requests
    public @ResponseBody
    ResponseStatusException storeQuestion (@RequestBody Questions question) {
       Users user = userRepository.findByUserId(question.getCreatorExamId());
        if (user.getRoleId() != 1){
           throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
       }
        Exams exam = examRepository
                .findExamsByCreatorIdAndExamId(
                        question.getCreatorExamId(),
                        question.getExamId()
                        );
       if (exam == null)  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exam id doesn't exists");

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
        if (quest.getCreatorExamId() != user.getUserId()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
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

    @GetMapping (path="/list-questions-randomly")
    public @ResponseBody
    ArrayList<Questions> listQuestionsRandomly (@RequestBody Exams exam) {
        String examId = exam.getExamId();
        List<Questions> a = questionRepository.
                findQuestionsByExamIdAndIsActive(examId,1);
        ArrayList<Questions> randomQuestions = new ArrayList<>() ;
        Random random = new Random();
        for (int i = 0; i <a.size() ; i++) {
            int randomNumber = 0 + random.nextInt(a.size());
            randomQuestions.add(a.get(randomNumber));
        }

        return randomQuestions;
    }

    @PostMapping (path="/list-question-by-id")
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

    @PostMapping (path="/list-question-by-type")
    public @ResponseBody
    List<Questions> listQuestionByType (@RequestBody Questions question) {
        String examId = question.getExamId();
        Integer questionType = question.getQuestionType();

        return questionRepository
                .findQuestionsByExamIdAndQuestionTypeAndIsActive(examId,questionType,1);
    }

    @GetMapping (path="/list-questions-all")
    public @ResponseBody
    List<Questions> listQuestions () {
        return questionRepository.findAllQuestions();
    }

    @PostMapping (path="/list-questions-by-accessibility")
    public @ResponseBody
    List<Questions> listQuestionsAccessibility(@RequestBody Questions question) {
        return questionRepository.
                findQuestionsByExamIdAndWhoCanSeeAndIsActive(
                        question.getExamId(),
                        question.getWhoCanSee(),
                        1);
    }


}
