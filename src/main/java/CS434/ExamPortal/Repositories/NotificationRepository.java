package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notifications,Integer> {
    List<Notifications> findNotificationsByStudentId(Integer studentId);
    List<Notifications> findNotificationsByStudentIdAndSeen(Integer studentId,Integer seen);

}
