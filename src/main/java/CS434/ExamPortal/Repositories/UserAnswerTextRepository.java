package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.UserAnswerText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerTextRepository extends JpaRepository<UserAnswerText,Integer> {
    UserAnswerText findUserAnswerTextByUserIdAndQuestionId(Integer userId, Integer qId);
}
