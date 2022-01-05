package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer,Integer> {

    List<UserAnswer> findUserAnswerByUserId(Integer userId);
    List<UserAnswer> findUserAnswerById(Integer id);
//    UserAnswer findUserAnswerByIdSingle(Integer id);
    List<UserAnswer> findUserAnswerByUserIdAndQuestionId(Integer userId,Integer questionId);

    @Query(value = "SELECT * FROM User_answer WHERE user_id=:userId AND question_id=:questionId", nativeQuery = true)
    UserAnswer findUserAnswerByUserIdAndQuestionIdSingle(@Param("userId") Integer userId,@Param("questionId") Integer questionId);

}
