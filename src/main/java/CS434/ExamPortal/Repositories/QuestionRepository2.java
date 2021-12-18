package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository2 extends JpaRepository<Questions,Integer> {
    Questions findByExamIdAndQuestionText(String examId,String questionText);
    Questions findByQuestionId(Integer questionId);
}
