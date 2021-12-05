package CS434.ExamPortal.RepositoriesImplement;

import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.Repositories.QuestionRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import CS434.ExamPortal.behavioralPattern.nullObject.NullObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class QuestionRepositoryImpl implements QuestionRepository {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public INullObject findByQuestionId(Integer questionId) {
       Questions question = questionRepository
               .findByQusId(questionId);

        if(question == null){
            return NullObject.getInstance();
        }else {
            return question;
        }
    }

    @Override
    public Questions findByQusId(Integer questionId) {
        return null;
    }

    @Override
    public List<Questions> findAllQuestions() {
        return null;
    }

    @Override
    public List<Questions> findQuestionsByExamIdAndIsActiveAndCreatorExamId(String examId, Integer isActive, Integer creatorId) {
        return null;
    }

    @Override
    public List<Questions> findQuestionsByCreatorExamIdAndIsActive(String examId, Integer isActive) {
        return null;
    }

    @Override
    public List<Questions> findQuestionsByExamIdAndQuestionTypeAndIsActive(String examId, Integer questionType, Integer isActive) {
        return null;
    }

    @Override
    public List<Questions> findQuestionsByExamIdAndWhoCanSeeAndIsActive(String examId, Integer who_can_see, Integer isActive) {
        return null;
    }

    @Override
    public List<Questions> findQuestionsByExamIdAndIsActive(String examId, Integer isActive) {
        return null;
    }

    @Override
    public void addQuestion(Questions questions) {
        System.out.println(questions);
        questionRepository.save(questions);
    }

    @Override
    public List<Questions> findAll() {
        return null;
    }

    @Override
    public List<Questions> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Questions> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Questions> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Questions entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Questions> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Questions> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Questions> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Questions> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Questions> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Questions> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Questions> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Questions getOne(Integer integer) {
        return null;
    }

    @Override
    public Questions getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Questions> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Questions> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Questions> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Questions> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Questions> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Questions> boolean exists(Example<S> example) {
        return false;
    }
}
