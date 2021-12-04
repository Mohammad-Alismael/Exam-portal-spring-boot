package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.UserAnswer;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.UserAnswerRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserAnswerController {

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/get-user-answer")
    public @ResponseBody
    List<UserAnswer> getUserAnswer(@RequestBody UserAnswer userAnswer) {
        return userAnswerRepository.findUserAnswerByUserId(userAnswer.getUserId());
    }

    @PostMapping(path="/set-user-answer")
    public @ResponseBody
    ResponseStatusException postUserAnswer(@RequestBody UserAnswer userAnswer) {
        Users currentUser = userRepository.findByUserId(userAnswer.getUserId());
        if (currentUser == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
        }

         userAnswerRepository.save(userAnswer);

        return  new ResponseStatusException(HttpStatus.GONE);
    }

    @PostMapping(path="/update-user-answer")
    public @ResponseBody
    ResponseStatusException updateUserAnswer(@RequestBody UserAnswer userAnswer) {
        Users currentUser = userRepository.findByUserId(userAnswer.getUserId());

        UserAnswer currentUserAnswer = userAnswerRepository
                .findUserAnswerByUserIdAndQuestionId(
                        userAnswer.getUserId(),
                        userAnswer.getQuestionId()
                );

        if (currentUser == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user id doesn't exists!");
        }
        currentUserAnswer.setUserAnswer(userAnswer.getUserAnswer());
        userAnswerRepository.save(currentUserAnswer);

        return  new ResponseStatusException(HttpStatus.GONE);
    }
}
