package CS434.ExamPortal.Repositories;


import CS434.ExamPortal.DAO.QuestionOptions;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface OptionRepository extends JpaRepository<QuestionOptions,Integer> {

    List<QuestionOptions> findQuestionOptionsByQuestionId(Integer questionId);
    void removeByQuestionIdAndOptionValue(Integer questionId,String option);
    @Transactional
    void removeById(Integer id);

}
