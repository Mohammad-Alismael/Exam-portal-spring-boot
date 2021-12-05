package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Integer> {
    @Query(value = "SELECT * from Users where username=:username and password=:password",nativeQuery = true)
    List<Users> findUsersByUsernameAndAndPassword(@Param("username") String username,
                                                  @Param("password")String password);
    Object findByUsername(String username);
    @Query(value = "SELECT * from Users where username=:Username ",nativeQuery = true)
    Users findByUsernamev2(@Param("Username") String username);
    INullObject findByUserId(int userId);
    @Query(value = "SELECT * from Users where user_id=:userId ",nativeQuery = true)
    Users findByUserIdv2(@Param("userId") Integer userId);
    void addUser(Users user);

    @Query(value = "SELECT * from Users",nativeQuery = true)
    List<Users> findAll();
}
