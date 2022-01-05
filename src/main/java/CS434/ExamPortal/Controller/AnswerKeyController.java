package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.AnswerKey;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.Repositories.AnswerKeyRepository;
import CS434.ExamPortal.Repositories.QuestionRepository;
import CS434.ExamPortal.Repositories.QuestionRepository2;
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
    private QuestionRepository2 questionRepository2;

    @PostMapping(path="/set-answer-key")
    public @ResponseBody
    AnswerKey setAnswerKey(@RequestBody AnswerKey answerKey) {
        Questions question =  questionRepository2.findByQuestionId(answerKey.getQuestionId());
        AnswerKey answerKey1 = answerKeyRepository.findAnswerKeyByQuestionId(answerKey.getQuestionId());
        if (question != null && answerKey1 == null){
            answerKeyRepository.save(answerKey);
        }
        return answerKey;
    }

    @PostMapping(path="/get-answer-key")
    public @ResponseBody
    AnswerKey getAnswerKey(@RequestBody AnswerKey answerKey) {

        AnswerKey answerKey1 = answerKeyRepository.findAnswerKeyByQuestionId(answerKey.getQuestionId());
        if (answerKey1 == null){
            return new AnswerKey();
        }else {
            return answerKey1;
        }

    }

    @PostMapping(path="/get-answer-key-multiple")
    public @ResponseBody
    Object getAnswerKeyMultiple(@RequestBody AnswerKey answerKey) {

        List<AnswerKey> answerKey1 = (List<AnswerKey>) answerKeyRepository.findAnswerKeyByQuestionId(answerKey.getQuestionId());
        if (answerKey1 == null){
            return (List<AnswerKey>) new AnswerKey();
        }else {
            return answerKeyRepository.findAnswerKeyByQuestionId(answerKey.getQuestionId());
        }

    }

    @PostMapping(path="/update-answer-key")
    public @ResponseBody
    AnswerKey updateAnswerKey(@RequestBody AnswerKey answerKey) {
        AnswerKey currentAnswerKey = answerKeyRepository.findAnswerKeyById(answerKey.getId());
        if (currentAnswerKey == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
        }else {
            currentAnswerKey.setCorrectAnswer(answerKey.getCorrectAnswer());
            answerKeyRepository.save(currentAnswerKey);
            return answerKey;
        }
    }

    @PostMapping(path="/delete-answer-key")
    public @ResponseBody
    ResponseStatusException deleteAnswerKey(@RequestBody AnswerKey answerKey) {
        Questions question =  questionRepository2.findByQuestionId(answerKey.getQuestionId());
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
