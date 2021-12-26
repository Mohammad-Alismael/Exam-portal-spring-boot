package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.Repositories.NotificationRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import CS434.ExamPortal.behavioralPattern.observerPattern.ClassroomSubscriber;
import CS434.ExamPortal.behavioralPattern.observerPattern.ClassroomObserver;
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
    @Autowired
    private NotificationRepository notificationRepository;

    private ClassroomSubscriber classroomSubscriber = new ClassroomSubscriber();


    @PostMapping("/set-classroom-to-students")
    public ResponseStatusException setClassroomStudents(@RequestBody Classroom classroom) {
        System.out.println(classroom);
        NullUser user = userRepositoryImpl.findByUserId(classroom.getStudentId());
        Classroom  classroom1 = classroomRepository
                .findClassroomByInstructorIdAndStudentId(
                        classroom.getInstructorId(),
                        classroom.getStudentId());
        if (user.isAvailable()) if (classroom1 == null) classroomRepository.save(classroom);
        ClassroomObserver classroomObserver = new ClassroomObserver(classroom);
        classroomObserver.setNotificationRepository(notificationRepository);
        classroomSubscriber.subscribe(classroomObserver);

        return new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @PostMapping("/get-class-students")
    public ArrayList<Users> getClassStudents(@RequestBody Classroom classroom) {
        List<Classroom> classrooms = classroomRepository.findClassroomByInstructorId(classroom.getInstructorId());
        ArrayList<Users> users = new ArrayList<>();
        for (Classroom classroom1 : classrooms){
            Users u = userRepository.findByUserIdv2(classroom1.getStudentId());
            users.add(u);
        }
        return users;
    }

    @PostMapping("/get-instructor-id-from-student-id")
    public Classroom getInstructorId(@RequestBody Classroom classroom){
        return classroomRepository.findClassroomByStudentId(classroom.getStudentId());
    }

    @GetMapping("/get-class-instructor")
    public List<Classroom> getClassInstructor(@RequestBody Classroom classroom) {
        return classroomRepository.findClassroomByInstructorId(classroom.getInstructorId());
    }

    @PostMapping("/delete-class-instructor")
    public RuntimeException deleteClassInstructor(@RequestBody Classroom classroom) {
        NullUser user = userRepositoryImpl.findByUserId(classroom.getStudentId());
        Classroom  classroom1 = classroomRepository
                .findClassroomByInstructorIdAndStudentId(
                        classroom.getInstructorId(),
                        classroom.getStudentId());
        if (!user.isAvailable()) if (classroom1 != null) classroomRepository.
                removeByInstructorIdAndStudentId(classroom1.getInstructorId(),
                        classroom1.getStudentId());
        ClassroomObserver classroomObserver = new ClassroomObserver(classroom);
        classroomObserver.setNotificationRepository(notificationRepository);
        classroomSubscriber.unsubscribe(classroomObserver);
        return new ResponseStatusException(HttpStatus.GONE,"has been deleted successfully!");
    }

    @GetMapping("/get-all-classrooms")
    public List<Classroom> getAlClassInstructor() {
        return classroomRepository.findAll();
    }
}
