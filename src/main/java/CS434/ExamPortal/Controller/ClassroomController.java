package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.DAO.ClassroomStudent;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.Repositories.ClassroomStudentRepository;
import CS434.ExamPortal.Repositories.NotificationRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import CS434.ExamPortal.behavioralPattern.observerPattern.ClassroomSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    private ClassroomStudentRepository classroomStudentRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    private ClassroomSubscriber classroomSubscriber = new ClassroomSubscriber();


    @PostMapping("/set-classroom-to-students")
    public Classroom setClassroomStudents(@RequestBody Classroom classroom) {
        NullUser user = userRepositoryImpl.findByUserId(classroom.getInstructorId());
        Classroom  classroom1 = classroomRepository
                .findClassroomByInstructorId(classroom.getInstructorId());
        if (user.isAvailable()) if (classroom1 == null) classroomRepository.save(classroom);
//        ClassroomObserver classroomObserver = new ClassroomObserver(classroom);
//        classroomObserver.setNotificationRepository(notificationRepository);
//        classroomSubscriber.subscribe(classroomObserver);

        return classroom;
    }

    @PostMapping("/set-classroom-to-students-v2")
    public ClassroomStudent setClassroomStudentsv2(@RequestBody ClassroomStudent classroom) {
        Classroom  classroom1 = classroomRepository
                .findClassroomById(classroom.getClassroomId());
         if (classroom1 == null) classroomStudentRepository.save(classroom);
        return classroom;
    }

    @PostMapping("/get-class-students-from-instructor")
    public ArrayList<Users> getClassStudents(@RequestBody Classroom classroom) {
        Classroom room = classroomRepository.findClassroomByInstructorId(classroom.getInstructorId());
        if (room == null) return new ArrayList<Users>();
        List<ClassroomStudent> classroomStudents = classroomStudentRepository
                .findClassroomStudentByClassroomId(room.getId());
        ArrayList<Users> users = new ArrayList<>();
        for (ClassroomStudent classroom1 : classroomStudents){
            Users u = userRepository.findByUserIdv2(classroom1.getStudentId());
            users.add(u);
        }
        return users;
    }

    @PostMapping("/get-instructor-id-from-student-id")
    public Classroom getInstructorId(@RequestBody ClassroomStudent classroom){

        Classroom room = classroomRepository.findClassroomById(classroom.getClassroomId());
        return room;
    }

    @PostMapping("/get-classroom-id-by-instructor-id")
    public Classroom getClassroomIdByInstructorId(@RequestBody Classroom classroom){
        Classroom room = classroomRepository.findClassroomByInstructorId(classroom.getInstructorId());
        if (room == null){
            return new Classroom();
        }else {
            return room;
        }

    }

    @PostMapping("/get-class-students-from-student-id")
    public ArrayList<Users> getClassInstructor(@RequestBody ClassroomStudent classroom) {
        List<ClassroomStudent> classroomStudents = classroomStudentRepository
                .findClassroomStudentByClassroomId(classroom.getClassroomId());
        ArrayList<Users> users = new ArrayList<>();
        for (ClassroomStudent classroom1 : classroomStudents){
            Users u = userRepository.findByUserIdv2(classroom1.getStudentId());
            users.add(u);
        }
        return users;
    }
    @PostMapping("/get-class-id-from-instructor")
    public Classroom getClassIdFromInstructor(@RequestBody Classroom classroom) {

        Classroom classroom1 = classroomRepository
                .findClassroomByInstructorId(classroom.getInstructorId());
        if (classroom1 == null){
            return new Classroom();
        }else {
            return classroom1;
        }
    }

    @PostMapping("/set-student-to-classroom")
    public ClassroomStudent getClassIdFromInstructor(@RequestBody ClassroomStudent classroom) {
        ClassroomStudent classroomStudent = classroomStudentRepository.findByStudentId(classroom.getStudentId());
        if (classroomStudent == null) classroomStudentRepository.save(classroom);
        return classroom;
    }
    @PostMapping("/get-classroom-ids-from-student-id")
    public List<ClassroomStudent> getClassIdFromStudent(@RequestBody ClassroomStudent classroom) {
        List<ClassroomStudent> classroomStudents = classroomStudentRepository
                .findClassroomStudentByStudentId(classroom.getStudentId());
        return  classroomStudents;
    }
//    @PostMapping("/delete-class-instructor")
//    public RuntimeException deleteClassInstructor(@RequestBody Classroom classroom) {
//        NullUser user = userRepositoryImpl.findByUserId(classroom.getStudentId());
//        Classroom  classroom1 = classroomRepository
//                .findClassroomByInstructorIdAndStudentId(
//                        classroom.getInstructorId(),
//                        classroom.getStudentId());
//        if (!user.isAvailable()) if (classroom1 != null) classroomRepository.
//                removeByInstructorIdAndStudentId(classroom1.getInstructorId(),
//                        classroom1.getStudentId());
//        ClassroomObserver classroomObserver = new ClassroomObserver(classroom);
//        classroomObserver.setNotificationRepository(notificationRepository);
//        classroomSubscriber.unsubscribe(classroomObserver);
//        return new ResponseStatusException(HttpStatus.GONE,"has been deleted successfully!");
//    }

    @GetMapping("/get-all-classrooms")
    public List<Classroom> getAlClassInstructor() {
        return classroomRepository.findAll();
    }
}
