package CS434.ExamPortal.Controller;

import CS434.ExamPortal.DAO.Announcements;
import CS434.ExamPortal.Repositories.AnnouncementRepository;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.observerPattern.ClassroomSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AnnoucementController {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;

    private ClassroomSubscriber classroomSubscriber = new ClassroomSubscriber();

    @PostMapping("/set-announcement-to-students")
    public ResponseStatusException setAnnouncement(@RequestBody Announcements announcement) {
        announcementRepository.save(announcement);
        classroomSubscriber.setInstructorId(announcement.getInstructorId());
        classroomSubscriber.notifySubscriber(announcement.getAnnouncementText());

        return new ResponseStatusException(HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-announcement-instructor-id")
    public List<Announcements> getAnnouncement(@RequestBody Announcements announcement) {
        return announcementRepository.findByInstructorId(announcement.getInstructorId());
    }

    @GetMapping("/get-announcements-all")
    public List<Announcements> getAnnouncement() {
        return announcementRepository.findAll();
    }
}
