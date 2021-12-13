package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Announcements;
import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.Repositories.AnnouncementRepository;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.Repositories.NotificationRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.observerPattern.ClassroomSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
public class AnnouncementController {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    private ClassroomSubscriber classroomSubscriber = new ClassroomSubscriber();


    @PostMapping("/set-announcement-to-students")
    public ResponseStatusException setAnnouncement(@RequestBody Announcements announcement) {
        classroomSubscriber.setUserRepository(userRepository);
        classroomSubscriber.setClassroomRepository(classroomRepository);
        classroomSubscriber.setNotificationRepository(notificationRepository);
        announcement.setCreatedAt(new Date().getTime());
        Announcements newAnnouncement = announcementRepository.save(announcement);
        System.out.println();
        classroomSubscriber.setInstructorId(announcement.getInstructorId());
        classroomSubscriber.notifySubscribers(newAnnouncement.getId());

        return new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-announcement-instructor-id")
    public List<Announcements> getAnnouncement(@RequestBody Announcements announcement) {
        return announcementRepository.findByInstructorId(announcement.getInstructorId());
    }
    @GetMapping("/get-announcements")
    public List<Announcements> getAnnouncement2(@RequestParam(required = false) Integer id) {
        return announcementRepository.findByInstructorId(id);
    }
    @GetMapping("/get-announcement-student-id")
    public List<Announcements> getAnnouncementStudentId(@RequestBody Classroom user) {

        return userRepositoryImpl.listAnnouncementForStudents(user.getStudentId());
    }
    @GetMapping("/get-announcements-all")
    public List<Announcements> getAnnouncement() {
        return announcementRepository.findAll();
    }

}
