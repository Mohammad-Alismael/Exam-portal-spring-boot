package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/list")
    public @ResponseBody Iterable<Users>test() {
        return userRepository.findAll();
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestBody Users userInfo) {
        userRepository.save(userInfo);
        return "Saved";
    }

}
