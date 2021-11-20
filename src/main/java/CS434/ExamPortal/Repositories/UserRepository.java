package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Integer> {
    List<Users> findUsersByUsernameAndAndPassword(String username, String password);
    Users findByUsername(String username);
}
