package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.DAO.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ExamRepository2 extends JpaRepository<Exams,String> {
    @Transactional
//    @Query("DELETE FROM Exams where exam_id=:examId AND creator_id =:creatorId")
    void removeByCreatorIdAndExamId(@Param("creatorId") Integer creatorId,@Param("examId") String ExamId);
}
