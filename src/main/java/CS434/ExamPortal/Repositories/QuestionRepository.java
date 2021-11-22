package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DTO.QuestionsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions,Integer> {
    Questions findQuestionsByQuestionId(String questionId);
    List<Questions> findQuestionsByCreatorExamIdAndIsActive(String examId,Boolean isActive);
}
