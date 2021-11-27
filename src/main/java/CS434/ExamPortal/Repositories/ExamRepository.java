package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Exams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exams,String> {

    @Query(value = "SELECT * FROM Exams", nativeQuery = true)
    List<Exams> findAllExams();

    Exams findExamsByCreatorIdAndExamId(Integer creatorId,String ExamId);
}
