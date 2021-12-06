package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClassroomController {

    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/set-classroom-to-students")
    public ResponseStatusException setClassroomStudents(@RequestBody Classroom classroom) {
        NullUser user = userRepositoryImpl.findByUserId(classroom.getStudentId());
        if (!user.isAvailable()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"student id doesn't exists");
        classroomRepository.save(classroom);
        return new ResponseStatusException(HttpStatus.ACCEPTED);
    }


    @GetMapping("/get-class-students")
    public ArrayList<Users> getClassStudents(@RequestBody Classroom classroom) {
        List<Classroom> classrooms = classroomRepository.findClassroomByInstructorId(classroom.getInstructorId());
        ArrayList<Users> users = new ArrayList<>();
        for (Classroom classroom1 : classrooms){
            Users u = userRepository.findByUserIdv2(classroom1.getStudentId());
            users.add(u);
        }
        return users;
    }

    @GetMapping("/get-class-instructor")
    public List<Classroom> getClassInstructor(@RequestBody Classroom classroom) {
        return classroomRepository.findClassroomByInstructorId(classroom.getInstructorId());
    }

    @PostMapping("/delete-class-instructor")
    public RuntimeException deleteClassInstructor(@RequestBody Classroom classroom) {
        classroomRepository.removeByInstructorIdAndStudentId(
                classroom.getInstructorId(),
                classroom.getStudentId()
        );
        return new ResponseStatusException(HttpStatus.GONE,"has been deleted successfully!");
    }

    @GetMapping("/get-all-classrooms")
    public List<Classroom> getAlClassInstructor() {
        return classroomRepository.findAll();
    }
}
