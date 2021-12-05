package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.ExamRepository;
import CS434.ExamPortal.Repositories.OptionRepository;
import CS434.ExamPortal.Repositories.QuestionRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.RepositoriesImplement.ExamRepositoryImpl;
import CS434.ExamPortal.RepositoriesImplement.QuestionRepositoryImpl;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUserObject;
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
    private QuestionRepositoryImpl questionRepositoryImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamRepositoryImpl examRepositoryImpl;
    @Autowired
    private OptionRepository optionRepository;

    @PostMapping(path="/add-question")
    public @ResponseBody
    ResponseStatusException storeQuestion (@RequestBody Questions question) {
       NullUser user = userRepositoryImpl.findByUserId(question.getCreatorExamId());
        if (!user.isAvailable()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user doesn't exist");
        }
        if (user.getRoleId() != 1){
           throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
       }

        INullObject exam = examRepositoryImpl
                .findExamsByCreatorIdAndExamId(
                        question.getCreatorExamId(),
                        question.getExamId()
                        );
       if (!exam.isAvailable())  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exam id doesn't exists");

        questionRepository.save(question);

       return  new ResponseStatusException(HttpStatus.CREATED,"created successfully!");

    }

    @PostMapping(path="/add-question2") // Map ONLY POST Requests
    public @ResponseBody
    Questions storeQuestion2 (@RequestBody Questions question) {

        return question;

    }

    @PostMapping(path="/update-question") // Map ONLY POST Requests
    public @ResponseBody
    ResponseStatusException updateQuestion (@RequestBody Questions question) {
        Users user = userRepository.findByUserIdv2(question.getCreatorExamId());
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        Questions quest = (Questions) questionRepository.findByQuestionId(question.getQuestionId());
        quest.setQuestionText(question.getQuestionText());
        quest.setPoints(question.getPoints());
        // the rest
        questionRepository.save(quest);
        return  new ResponseStatusException(HttpStatus.ACCEPTED);

    }

    @PostMapping(path="/delete-question")
    public @ResponseBody
    ResponseStatusException deleteQuestion (@RequestBody Questions question) {
        Users user = userRepository.findByUserIdv2(question.getCreatorExamId());
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        Questions quest = (Questions) questionRepository.findByQuestionId(question.getQuestionId());
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
        return  questionRepository.findQuestionsByCreatorExamIdAndIsActive(examId,1);
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
        Integer questionId = question.getQuestionId();
        INullObject quest = questionRepositoryImpl
                .findByQuestionId(questionId);
        if (!quest.isAvailable()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question not found!");
        }
        return quest;

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
