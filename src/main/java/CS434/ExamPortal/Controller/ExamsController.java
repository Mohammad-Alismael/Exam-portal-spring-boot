package CS434.ExamPortal.Controller;

import CS434.ExamPortal.Classes.RandomUuidStringCreator;
import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.Repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
public class ExamsController {

    @Autowired
    private ExamRepository examRepository;

    @GetMapping("/list-all-exams")
    public List<Exams> listAllExams() {
        return examRepository.findAllExams();
    }

    @PostMapping("/add-exam")
    public Exams addExam(@RequestBody Exams exam) {
        exam.setExamId(RandomUuidStringCreator.getInstance().getRandomUuid());

        Timestamp instant= Timestamp.from(Instant.now());
        exam.setStartingAt(instant);
        exam.setEndingAt(instant);
        exam.setCreatedAt(instant);
        examRepository.save(exam);
        return exam;

    }

    @PostMapping("/update-exam")
    public ResponseStatusException updateExam(@RequestBody Exams newExam) {
        Exams currentExam = examRepository.findExamsByCreatorIdAndExamId(newExam.getCreatorId(),newExam.getExamId());
        if (currentExam == null){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        currentExam.setTitle(newExam.getTitle());
        currentExam.setScore(newExam.getScore());
        if (newExam.getStartingAt() != null){
            currentExam.setStartingAt(newExam.getStartingAt());
        }
        if (newExam.getEndingAt() != null){
            currentExam.setEndingAt(newExam.getEndingAt());
        }
        examRepository.save(newExam);
        return  new ResponseStatusException(HttpStatus.ACCEPTED);

    }
}
