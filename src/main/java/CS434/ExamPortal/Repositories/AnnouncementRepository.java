package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcements,Integer> {
    List<Announcements> findByInstructorId(Integer instructorId);
}
