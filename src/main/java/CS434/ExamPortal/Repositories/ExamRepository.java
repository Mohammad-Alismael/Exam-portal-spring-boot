package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Exams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ExamRepository extends JpaRepository<Exams,String> {

    @Query(value = "SELECT * FROM Exams", nativeQuery = true)
    List<Exams> findAllExams();

    Exams findExamsByCreatorIdAndExamId(Integer creatorId,String ExamId);

    List<Exams> findExamsByCreatorId(Integer creatorId);

    String deleteByCreatorIdAndExamId(Integer creatorId,String ExamId);

    @Transactional
    void removeByCreatorIdAndExamId(Integer creatorId,String ExamId);

}
