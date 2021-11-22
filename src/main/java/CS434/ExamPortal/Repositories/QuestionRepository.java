package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Questions,Integer> {
}
