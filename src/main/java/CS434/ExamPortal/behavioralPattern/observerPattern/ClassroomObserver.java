package CS434.ExamPortal.behavioralPattern.observerPattern;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.DAO.Notifications;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.Repositories.NotificationRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class ClassroomObserver implements Observer{
    private Classroom classroom;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    public ClassroomObserver(Classroom classroom) {
        this.classroom = classroom;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }



    @Override
    public void update(Classroom classroom, String announcementText) {
        Notifications notifications = new Notifications();
        notifications.setAnnouncementId(classroom.getId());
        notificationRepository.save(notifications);
    }
}
