package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.*;
import CS434.ExamPortal.RepositoriesImplement.ExamRepositoryImpl;
import CS434.ExamPortal.RepositoriesImplement.QuestionRepositoryImpl;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import CS434.ExamPortal.structuralPattern.compositePattern.Question;
import CS434.ExamPortal.structuralPattern.compositePattern.QuestionComponent;
import CS434.ExamPortal.structuralPattern.compositePattern.QuestionGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
public class QuestionsController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionRepository2 questionRepository2;
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
    @Autowired
    private AnswerKeyRepository answerKeyRepository;

    @PostMapping(path="/add-question")
    public @ResponseBody
    INullObject storeQuestion (@RequestBody Questions question) {
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
//       if (!exam.isAvailable())  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "exam id doesn't exists");
        if (!exam.isAvailable()) System.out.println("exam id doesn't exists");
//        questionRepositoryImpl.addQuestion(question);
        Questions currentQuestion = questionRepository2.findByExamIdAndQuestionText(
                question.getExamId(),
                question.getQuestionText());
        if (currentQuestion == null)
            questionRepository2.save(question);
//       return  new ResponseStatusException(HttpStatus.CREATED,"created successfully!");
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
        System.out.println(quest);
        quest.setQuestionText(question.getQuestionText());
        quest.setPoints(question.getPoints());
        quest.setWhoCanSee(question.getWhoCanSee());
        // the rest
        questionRepositoryImpl.addQuestion(quest);
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

    @PostMapping (path="/list-questions")
    public @ResponseBody
    ArrayList<Question> listQuestions (@RequestBody Exams exam) {
        String examId = exam.getExamId();
        List<Questions> questions = questionRepository.findQuestionsByCreatorExamId(examId);
        QuestionComponent questionGroup = new QuestionGroup();
        for(Questions question: questions){
            Question q = new Question( question);
            q.setOptionRepository(optionRepository);
            q.setAnswerKeyRepository(answerKeyRepository);
            questionGroup.add(q);
        }
        return  ((QuestionGroup) questionGroup).getQuestions();
    }

    @PostMapping (path="/list-questions-randomly")
    public @ResponseBody
    ArrayList<Object> listQuestionsRandomly (@RequestBody Questions exam) {
        String examId = exam.getExamId();
        List<Questions> questions = questionRepository2.
                findQuestionsByExamIdForStudents(examId,exam.getWhoCanSee());

        ArrayList<Object> randomQuestions = new ArrayList<>() ;
        Random random = new Random();

        QuestionComponent questionGroup = new QuestionGroup();
        for(Questions question: questions){
            Question q = new Question( question);
            q.setOptionRepository(optionRepository);
            q.setAnswerKeyRepository(answerKeyRepository);
            questionGroup.add(q);
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<questions.size(); i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i=0; i<questions.size(); i++) {
            randomQuestions.add(questionGroup.getQuestion(i));

        }

//        for (int i = 0; i <a.size() ; i++) {
//            int randomNumber = 0 + random.nextInt(a.size());
//            randomQuestions.add(questionGroup.getQuestion(randomNumber));
//        }

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
