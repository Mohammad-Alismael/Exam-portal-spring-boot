package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.UserAnswer;
import CS434.ExamPortal.Repositories.UserAnswerRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
public class UserAnswerController {

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @GetMapping(path="/get-user-answer")
    public @ResponseBody
    List<UserAnswer> getUserAnswer(@RequestBody UserAnswer userAnswer) {
        return userAnswerRepository.findUserAnswerByUserId(userAnswer.getUserId());
    }

    @PostMapping(path="/set-user-answer")
    public @ResponseBody
    ResponseStatusException postUserAnswer(@RequestBody UserAnswer userAnswer) {
        NullUser currentUser = userRepositoryImpl.findByUserId(userAnswer.getUserId());
        if (!currentUser.isAvailable()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
        }
        userAnswer.setAnsweredAt(new Date().getTime());
        List<UserAnswer> userAnswer1 = userAnswerRepository.
                findUserAnswerByUserIdAndQuestionId(
                        userAnswer.getUserId(),
                        userAnswer.getQuestionId());
         if (userAnswer1.size() == 0) userAnswerRepository.save(userAnswer);
        userAnswerRepository.save(userAnswer);

        return new ResponseStatusException(HttpStatus.GONE);
    }

    @PostMapping(path="/update-user-answer")
    public @ResponseBody
    ResponseStatusException updateUserAnswer(@RequestBody UserAnswer userAnswer) {
//        NullUser currentUser = userRepositoryImpl.findByUserId(userAnswer.getUserId());
//
//        UserAnswer currentUserAnswer = userAnswerRepository
//                .findUserAnswerByUserIdAndQuestionIdv2(
//                        userAnswer.getUserId(),
//                        userAnswer.getQuestionId()
//                );
//
//        if (!currentUser.isAvailable()){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "question id doesn't exists!");
//        }
//        currentUserAnswer.setUserAnswer(userAnswer.getUserAnswer());
//        userAnswerRepository.save(currentUserAnswer);

        return  new ResponseStatusException(HttpStatus.GONE);
    }
}
