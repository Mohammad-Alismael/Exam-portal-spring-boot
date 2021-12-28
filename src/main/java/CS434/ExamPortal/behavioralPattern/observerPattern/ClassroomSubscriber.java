package CS434.ExamPortal.behavioralPattern.observerPattern;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.DAO.ClassroomStudent;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.Repositories.NotificationRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Configurable
public class ClassroomSubscriber {
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    private UserRepository userRepository;

    private ClassroomRepository classroomRepository;

    private NotificationRepository notificationRepository;

    private Integer InstructorId;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setClassroomRepository(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }
    @Autowired
    public void setNotificationRepository(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Integer getInstructorId() {
        return InstructorId;
    }

    public void setInstructorId(Integer instructorId) {
//        System.out.println(userRepository.findByUserIdv2(instructorId));
//        Classroom room = classroomRepository
//                .findClassroomByInstructorId(instructorId);
//        List<ClassroomStudent> classrooms = classroomStudentRepository
//                .findClassroomStudentByClassroomId(room.getId());
//        for (ClassroomStudent classroom: classrooms){
//            ClassroomObserver classroomObserver = new ClassroomObserver(classroom);
//            classroomObserver.setNotificationRepository(notificationRepository);
//            observers.add(classroomObserver);
//        }
    }

    public void subscribe(Observer classroomObserver) {
        observers.add(classroomObserver);

    }

    public void unsubscribe(Observer classroomObserver) {
        observers.remove(classroomObserver);

    }

    public void notifySubscribers(Integer announcementId) {
//        Iterator<?> it = observers.iterator();
//        while (it.hasNext()) {
//            Observer o = (Observer) it.next();
//            o.update(o.getClassroom(),announcementId);
//        }
    }

}
