package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.QuestionOptions;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.Repositories.OptionRepository;
import CS434.ExamPortal.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class OptionsController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @PostMapping(path="/set-question-options")
    public @ResponseBody
    QuestionOptions setOptions(@RequestBody QuestionOptions questionOptions) {
        Questions question = (Questions) questionRepository.findByQuestionId(questionOptions.getQuestionId());

        if (question == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
        }

        optionRepository.save(questionOptions);
        return questionOptions;
    }

    @PostMapping (path="/update-question-options")
    public @ResponseBody
    QuestionOptions updateOptions(@RequestBody QuestionOptions questionOptions) {
        Questions question = (Questions) questionRepository.findByQuestionId(questionOptions.getQuestionId());
        List<QuestionOptions> currentQ = optionRepository
                .findQuestionOptionsByQuestionId(questionOptions.getQuestionId());
        if (question == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
        }
        currentQ.get(0).setOptionValue(questionOptions.getOptionValue());

        optionRepository.save(currentQ.get(0));
        return questionOptions;
    }

    @PostMapping (path="/get-question-options")
    public @ResponseBody
    List<QuestionOptions> getOptions(@RequestBody QuestionOptions questionOptions) {

        return optionRepository
                .findQuestionOptionsByQuestionId(questionOptions.getQuestionId());
    }

    @PostMapping (path="/delete-question-options")
    public @ResponseBody
    ResponseStatusException deleteOptions(@RequestBody QuestionOptions questionOptions) {

        optionRepository
                .removeById(questionOptions.getId());
        return  new ResponseStatusException(HttpStatus.GONE);
    }
}
