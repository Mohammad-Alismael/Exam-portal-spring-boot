package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DAO.UserAnswerText;
import CS434.ExamPortal.Repositories.QuestionRepository2;
import CS434.ExamPortal.Repositories.UserAnswerTextRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAnswerTextController {
    @Autowired
    UserAnswerTextRepository userAnswerTextRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private QuestionRepository2 questionRepository2;
    @PostMapping(path="/set-user-answer-text")
    public @ResponseBody
    UserAnswerText getUserById(@RequestBody UserAnswerText userAnswerText) {
        NullUser users = userRepositoryImpl.findByUserId(userAnswerText.getUserId());
        System.out.println(users.isAvailable());
        if (users.isAvailable()){
            Questions question = questionRepository2.findByQuestionId(userAnswerText.getQuestionId());
            System.out.println(question != null);
            System.out.println(userAnswerText.getUserAnswer());
            if (question != null) {

                UserAnswerText userAnswerText1 = userAnswerTextRepository
                        .findUserAnswerTextByUserIdAndQuestionId(userAnswerText.getUserId(),
                                            userAnswerText.getUserId());

                if (userAnswerText1 == null) {
                    userAnswerTextRepository.save(userAnswerText);
                }
            }
        }
        return userAnswerText;
    }

    @PostMapping(path="/set-user-answer-text-v2")
    public @ResponseBody
    UserAnswerText getUserByIdv2(@RequestBody UserAnswerText userAnswerText) {

        return userAnswerText;
    }
}
