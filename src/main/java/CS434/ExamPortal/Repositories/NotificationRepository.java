package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Integer, Notifications> {

}
