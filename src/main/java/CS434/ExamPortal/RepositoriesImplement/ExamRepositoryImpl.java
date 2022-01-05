package CS434.ExamPortal.RepositoriesImplement;

import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.Repositories.ExamRepository;
import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import CS434.ExamPortal.behavioralPattern.nullObject.NullObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public class ExamRepositoryImpl implements ExamRepository {

    @Autowired
    private ExamRepository examRepository;
    @PersistenceContext
    public EntityManager em;

    @Override
    public List<Exams> findAllExams() {
        return null;
    }

    @Override
    public INullObject findExamsByCreatorIdAndExamId(Integer creatorId, String ExamId) {
        Exams exam = examRepository
                .findExamsByCreatorIdAndExamIdv2(
                        creatorId,
                        ExamId
                );
        System.out.println(exam);
        if (exam == null){
            return NullObject.getInstance();
        }else {
            return exam;
        }
    }
    public Exams addExam(Exams exam){
        em.createNativeQuery("INSERT INTO Exams " +
                "(exam_id, title,score,creator_id,starting_at,ending_at,created_at) " +
                "VALUES (?,?,?,?,?,?,?)")
                .setParameter(1, exam.getExamId())
                .setParameter(2, exam.getTitle())
                .setParameter(3, exam.getScore())
                .setParameter(4, exam.getCreatorId())
                .setParameter(5, exam.getStartingAt())
                .setParameter(6, exam.getEndingAt())
                .setParameter(7, exam.getCreatedAt())
                .executeUpdate();


        return exam;
    }
    @Override
    public Exams findExamsByCreatorIdAndExamIdv2(Integer creatorId, String ExamId) {
        return null;
    }

    @Override
    public List<Exams> findExamsByCreatorId(Integer creatorId) {
        return null;
    }

    @Override
    public Exams findExamsByExamId(String examId) {
        return null;
    }

    @Override
    public String deleteByCreatorIdAndExamId(Integer creatorId, String ExamId) {
        return null;
    }

    @Override
    public void removeByCreatorIdAndExamId(Integer creatorId, String ExamId) {

    }

    @Override
    public List<Exams> findAll() {
        return null;
    }

    @Override
    public List<Exams> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Exams> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Exams> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Exams entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Exams> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Exams> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Exams> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Exams> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Exams> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Exams> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Exams> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Exams getOne(String s) {
        return null;
    }

    @Override
    public Exams getById(String s) {
        return null;
    }

    @Override
    public <S extends Exams> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Exams> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Exams> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Exams> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Exams> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Exams> boolean exists(Example<S> example) {
        return false;
    }

    public List<Exams> listExamsbyStudentId(Integer studentId,Integer classroomId){
//        SELECT exam_id,title,score,starting_at,ending_at from Exams join Classroom on Exams.creator_id = Classroom.instructor_id where student_id= 15;
        return em.createNativeQuery("SELECT exam_id,title,score,starting_at,ending_at from Exams\n" +
                "    where creator_id = ( SELECT distinct instructor_id\n" +
                "    from Classroom join classroom_student cs on Classroom.id\n" +
                "    = cs.classroom_id where student_id= ? and classroom_id= ?)")
                .setParameter(1, studentId)
                .setParameter(2, classroomId)
                .getResultList();
    }
    public boolean checkExamSubmission(Integer studentId,String examId){
        List<Exams> questions = em.createNativeQuery("select question_id from User_answer\n" +
                "where exists(select question_id from Questions\n" +
                "where exam_id= ?)\n" +
                "and user_id = ?")
                .setParameter(1, examId)
                .setParameter(2, studentId)
                .getResultList();
        System.out.println(questions);
        return questions.size() != 0;
    }
}
