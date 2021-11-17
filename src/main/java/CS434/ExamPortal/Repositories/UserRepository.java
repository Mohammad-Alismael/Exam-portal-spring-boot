package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
}
