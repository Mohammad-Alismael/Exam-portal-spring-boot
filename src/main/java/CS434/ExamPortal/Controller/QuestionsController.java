package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class QuestionsController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/authenticate") // Map ONLY POST Requests
    public @ResponseBody
    List<Users> storeQuestion (@RequestBody Questions question) {
       return null;

    }
}
