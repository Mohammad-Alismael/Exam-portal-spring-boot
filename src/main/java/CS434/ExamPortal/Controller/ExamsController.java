package CS434.ExamPortal.Controller;

import CS434.ExamPortal.Classes.RandomUuidStringCreator;
import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.DAO.ClassroomStudent;
import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.DTO.ExamsDTO;
import CS434.ExamPortal.Repositories.ExamRepository;
import CS434.ExamPortal.Repositories.ExamRepository2;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.RepositoriesImplement.ExamRepositoryImpl;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
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
    private ExamRepository2 examRepository2;
    @Autowired
    private ExamRepositoryImpl examRepositoryImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
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
        NullUser user = userRepositoryImpl.findByUserId(exam.getCreatorId());
        if (!user.isAvailable()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user doesn't exist");
        }
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        exam.setExamId(RandomUuidStringCreator.getInstance().getRandomUuid());
        exam.setStartingAt(exam.getStartingAt());
        exam.setEndingAt(exam.getEndingAt());
        exam.setCreatedAt(new Date().getTime());
        examRepository2.save(exam);
        return exam;

    }

    @PostMapping("/update-exam")
    public ResponseStatusException updateExam(@RequestBody Exams newExam) {
        Users user = userRepository.findByUserIdv2(newExam.getCreatorId());
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }
        Exams currentExam = examRepository.findExamsByCreatorIdAndExamIdv2(newExam.getCreatorId(),newExam.getExamId());
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
        Users user = userRepository.findByUserIdv2(exam.getCreatorId());
        if (user.getRoleId() != 1){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you have no permission!");
        }

        examRepository.removeByCreatorIdAndExamId(exam.getCreatorId(), exam.getExamId());
        return  new ResponseStatusException(HttpStatus.ACCEPTED);

    }
    @PostMapping("/get-exam-id-student-id")
    public List<Exams> getExamIdByStudentId(@RequestBody ClassroomStudent classroom) {
        return examRepositoryImpl.listExamsbyStudentId(classroom.getStudentId());
    }

    @PostMapping("/get-exam-id-info")
    public Exams getExamInfo(@RequestBody Exams exam) {
        return examRepository.findExamsByExamId(exam.getExamId());
    }
}
