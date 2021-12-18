package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ExamRepository extends JpaRepository<Exams,String> {

    @Query(value = "SELECT * FROM Exams", nativeQuery = true)
    List<Exams> findAllExams();

    Object findExamsByCreatorIdAndExamId(Integer creatorId, String ExamId);

    @Query(value = "SELECT * from Exams where creator_id=:creatorId and exam_id=:ExamId ",nativeQuery = true)
    Exams findExamsByCreatorIdAndExamIdv2(@Param("creatorId") Integer creatorId,
                                          @Param("ExamId") String ExamId);

    @Query(value = "SELECT * FROM Exams WHERE creator_id=:creatorId", nativeQuery = true)
    List<Exams> findExamsByCreatorId(@Param("creatorId") Integer creatorId);

    @Query(value = "SELECT * FROM Exams WHERE exam_id=:examId", nativeQuery = true)
    Exams findExamsByExamId(@Param("examId") String examId);

    String deleteByCreatorIdAndExamId(Integer creatorId,String ExamId);

    @Transactional
    void removeByCreatorIdAndExamId(Integer creatorId,String ExamId);

}
