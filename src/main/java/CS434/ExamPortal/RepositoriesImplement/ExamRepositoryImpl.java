package CS434.ExamPortal.RepositoriesImplement;

import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.Repositories.ExamRepository;
import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import CS434.ExamPortal.behavioralPattern.nullObject.NullObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class ExamRepositoryImpl implements ExamRepository {

    @Autowired
    private ExamRepository examRepository;

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

    @Override
    public Exams findExamsByCreatorIdAndExamIdv2(Integer creatorId, String ExamId) {
        return null;
    }

    @Override
    public List<Exams> findExamsByCreatorId(Integer creatorId) {
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
}
