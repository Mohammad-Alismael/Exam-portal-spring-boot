package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DTO.QuestionsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions,Integer> {

    Questions findByQuestionId(Integer questionId);

    Questions findQuestionsByQuestionIdAndAndExamIdAndIsActive(Integer questionId,String examId,Integer isActive);

    @Query(value = "SELECT * FROM Questions", nativeQuery = true)
    List<Questions> findAllQuestions();

    List<Questions> findQuestionsByExamIdAndIsActiveAndCreatorExamId(String examId, Integer isActive, Integer creatorId);

    List<Questions> findQuestionsByExamIdAndQuestionTypeAndIsActive(String examId,Integer questionType, Integer isActive);

    List<Questions> findQuestionsByExamIdAndWhoCanSeeAndIsActive(String examId, Integer who_can_see, Integer isActive);

    List<Questions> findQuestionsByExamIdAndIsActive(String examId,Integer isActive);

}
