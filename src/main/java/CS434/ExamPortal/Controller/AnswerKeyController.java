package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.AnswerKey;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.Repositories.AnswerKeyRepository;
import CS434.ExamPortal.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AnswerKeyController {
    @Autowired
    private AnswerKeyRepository answerKeyRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping(path="/set-answer-key")
    public @ResponseBody
    AnswerKey setAnswerKey(@RequestBody AnswerKey answerKey) {
        Questions question = (Questions) questionRepository.findByQuestionId(answerKey.getQuestionId());

        if (question == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
        }

        answerKeyRepository.save(answerKey);
        return answerKey;
    }

    @PostMapping(path="/get-answer-key")
    public @ResponseBody
    AnswerKey getAnswerKey(@RequestBody AnswerKey answerKey) {
//        Questions question = questionRepository.findByQuestionId(answerKey.getQuestionId());

//        if (question == null){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
//        }

        return answerKeyRepository.findAnswerKeyByQuestionId(answerKey.getQuestionId());

    }

    @PostMapping(path="/update-answer-key")
    public @ResponseBody
    ResponseStatusException updateAnswerKey(@RequestBody AnswerKey answerKey) {
        Questions question = (Questions) questionRepository.findByQuestionId(answerKey.getQuestionId());

        AnswerKey currentAnswerKey = answerKeyRepository.findAnswerKeyById(answerKey.getId());
        if (currentAnswerKey == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
        }
        currentAnswerKey.setCorrectAnswer(answerKey.getCorrectAnswer());

        answerKeyRepository.save(currentAnswerKey);

        return  new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @PostMapping(path="/delete-answer-key")
    public @ResponseBody
    ResponseStatusException deleteAnswerKey(@RequestBody AnswerKey answerKey) {
        Questions question = (Questions) questionRepository.findByQuestionId(answerKey.getQuestionId());

        AnswerKey currentAnswerKey = answerKeyRepository.findAnswerKeyById(answerKey.getId());
        if (currentAnswerKey == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
        }
        currentAnswerKey.setCorrectAnswer(answerKey.getCorrectAnswer());

        answerKeyRepository.removeById(answerKey.getId());

        return  new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @GetMapping(path="/list-all-answer-key")
    public @ResponseBody
    List<AnswerKey> getAllAnswerKey() {

        return  answerKeyRepository.findAll();
    }
}
