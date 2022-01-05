package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer,Integer> {

    List<UserAnswer> findUserAnswerByUserId(Integer userId);
    List<UserAnswer> findUserAnswerById(Integer id);
    UserAnswer findUserAnswerByIdV2(Integer id);
    UserAnswer findUserAnswerByUserIdAndQuestionId(Integer userId,Integer questionId);

//    UserAnswer findUserAnswerByUserIdAndQuestionId(Integer userId,Integer questionId);

}
