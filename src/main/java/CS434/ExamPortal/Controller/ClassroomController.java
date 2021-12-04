package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ClassroomController {

    @Autowired
    private ClassroomRepository classroomRepository;

    @PostMapping("/set-classroom-to-students")
    public ResponseStatusException setClassroomStudents(@RequestBody Classroom classroom) {
        classroomRepository.save(classroom);
        return  new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-class-students")
    public List<Classroom> getClassStudents(@RequestBody Classroom classroom) {
        return classroomRepository.findClassroomByInstructorId(classroom.getInstructorId());
    }

    @GetMapping("/get-class-instructor")
    public List<Classroom> getClassInstructor(@RequestBody Classroom classroom) {
        return classroomRepository.findClassroomByInstructorId(classroom.getInstructorId());
    }

}
