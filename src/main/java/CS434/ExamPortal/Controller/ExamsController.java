package CS434.ExamPortal.Controller;

import CS434.ExamPortal.Classes.RandomUuidStringCreator;
import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.DTO.ExamsDTO;
import CS434.ExamPortal.Repositories.ExamRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
public class ExamsController {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list-all-exams")
    public List<Exams> listAllExams() {
        return examRepository.findAllExams();
    }

    @PostMapping("/list-all-exams-creator-id")
    public List<Exams> ListMyExams(@RequestBody Exams exam) {
        return examRepository.findExamsByCreatorId(exam.getCreatorId());
    }

    @PostMapping("/add-exam")
    public Exams addExam(@RequestBody Exams exam) {
        Users user = userRepository.findByUserId(exam.getCreatorId());
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        exam.setExamId(RandomUuidStringCreator.getInstance().getRandomUuid());
        Timestamp instant = Timestamp.from(Instant.now());
        exam.setStartingAt(exam.getStartingAt());
        exam.setEndingAt(exam.getEndingAt());
        exam.setCreatedAt(new Date().getTime());
        examRepository.save(exam);
        return exam;

    }

    @PostMapping("/update-exam")
    public ResponseStatusException updateExam(@RequestBody Exams newExam) {
        Users user = userRepository.findByUserId(newExam.getCreatorId());
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        Exams currentExam = examRepository.findExamsByCreatorIdAndExamId(newExam.getCreatorId(),newExam.getExamId());
        if (currentExam == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        currentExam.setTitle(newExam.getTitle());
        currentExam.setScore(newExam.getScore());

        if (newExam.getStartingAt() != 0){
            currentExam.setStartingAt(newExam.getStartingAt());
        }
        if (newExam.getEndingAt() != 0){
            currentExam.setStartingAt(newExam.getStartingAt());
        }
        examRepository.save(currentExam);
        return  new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete-exam")
    public ResponseStatusException deleteExam(@RequestBody ExamsDTO exam) {
        Users user = userRepository.findByUserId(exam.getCreatorId());
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }

        examRepository.removeByCreatorIdAndExamId(exam.getCreatorId(), exam.getExamId());
        return  new ResponseStatusException(HttpStatus.ACCEPTED);

    }
}
