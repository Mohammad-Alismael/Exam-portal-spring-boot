package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DTO.QuestionsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository2 extends JpaRepository<Questions,Integer> {
    Questions findByExamIdAndQuestionText(String examId,String questionText);
    Questions findByQuestionId(Integer questionId);

    @Query(value = "SELECT * from Questions where exam_id=:examId AND is_active = 1 having who_can_see=:see or who_can_see=3",nativeQuery = true)
    List<Questions> findQuestionsByExamIdForStudents(@Param("examId") String examId, @Param("see") Integer see);
}
