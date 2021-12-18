package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions,Integer> {

    Object findByQuestionId(Integer questionId);

    @Query(value = "SELECT * from Questions where question_id=:questionId ",nativeQuery = true)
    Questions findByQusId(@Param("questionId") Integer questionId);

    @Query(value = "SELECT * FROM Questions", nativeQuery = true)
    List<Questions> findAllQuestions();

    List<Questions> findQuestionsByExamIdAndIsActiveAndCreatorExamId(String examId, Integer isActive, Integer creatorId);
    @Query(value = "SELECT * from Questions where exam_id=:examId AND is_active = 1 ",nativeQuery = true)
    List<Questions> findQuestionsByCreatorExamId(@Param("examId") String examId);


    List<Questions> findQuestionsByCreatorExamIdAndIsActive(@Param("examId") String examId, Integer isActive);

    List<Questions> findQuestionsByExamIdAndQuestionTypeAndIsActive(String examId,Integer questionType, Integer isActive);

    List<Questions> findQuestionsByExamIdAndWhoCanSeeAndIsActive(String examId, Integer who_can_see, Integer isActive);

    List<Questions> findQuestionsByExamIdAndIsActive(String examId,Integer isActive);
    @Transactional
    void addQuestion(Questions questions);
}
