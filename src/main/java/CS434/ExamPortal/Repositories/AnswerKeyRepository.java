package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.AnswerKey;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface AnswerKeyRepository extends JpaRepository<AnswerKey,Integer> {
    AnswerKey findAnswerKeyByQuestionId(Integer questionId);
    AnswerKey findAnswerKeyById(Integer id);
    @Transactional
    void removeById(Integer id);
}
