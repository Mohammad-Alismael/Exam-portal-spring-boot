package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Integer> {
    List<Users> findUsersByUsernameAndAndPassword(String username, String password);
    Users findByUsername(String username);
    INullObject findByUserId(int userId);
    @Query(value = "SELECT * from Users where user_id=:userId ",nativeQuery = true)
    Users findByUserIdv2(@Param("userId") Integer userId);
}
