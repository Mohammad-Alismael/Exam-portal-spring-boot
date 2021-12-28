package CS434.ExamPortal.behavioralPattern.observerPattern;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.DAO.ClassroomStudent;
import CS434.ExamPortal.DAO.Notifications;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.Repositories.NotificationRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
@Service
@Configurable
public class ClassroomObserver implements Observer{
    private ClassroomStudent classroom;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private ClassroomRepository classroomRepository;

    private NotificationRepository notificationRepository;

    public ClassroomObserver() {

    }
    public ClassroomObserver(ClassroomStudent classroom) {
        this.classroom = classroom;

    }

    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public ClassroomStudent getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomStudent classroom) {
        this.classroom = classroom;
    }



    @Override
    public void update(ClassroomStudent classroom, Integer announcementId) {

        Notifications notifications = new Notifications();
        notifications.setAnnouncementId(announcementId);
        notifications.setSeen(0);
        notifications.setStudentId(classroom.getStudentId());
        notificationRepository.save(notifications);
    }
}
