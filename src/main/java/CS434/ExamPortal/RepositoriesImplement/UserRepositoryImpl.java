package CS434.ExamPortal.RepositoriesImplement;

import CS434.ExamPortal.DAO.Announcements;
import CS434.ExamPortal.DAO.Users;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUserObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<Users> findUsersByUsernameAndAndPassword(String username, String password) {
        return null;
    }
    @PersistenceContext
    public EntityManager em;
    @Override
    public NullUser findByUsername(String username) {
        Users user = userRepository.findByUsernamev2(username);
        if (user == null){
            return NullUserObject.getInstance();
        }else {
            return  user;
        }
    }

    @Override
    public Users findByUsernamev2(String username) {
        return null;
    }

    @Override
    public NullUser findByUserId(int userId) {
       Users user = userRepository.findByUserIdv2(userId);
        System.out.println(user);
       if (user == null){
           return NullUserObject.getInstance();
       }else {
           return  user;
       }
    }

    @Override
    public Users findByUserIdv2(Integer userId) {
        return null;
    }

    public NullUser findByUserIdv3(int userId) {
        List query = em.createNativeQuery("SELECT * FROM Users WHERE user_id=(?)")
                .setParameter(1, userId)
                .getResultList();
        Users user = (Users) query.get(0);
        System.out.println(user);
        if (user == null){
            return NullUserObject.getInstance();
        }else {
            return  user;
        }
    }

    @Transactional
    @Override
    public void addUser(Users user) {
        em.createNativeQuery("INSERT INTO Users " +
                "(username, password,email_id,role_id) " +
                "VALUES (?,?,?,?)")
                .setParameter(1, user.getUsername())
                .setParameter(2, user.getPassword())
                .setParameter(3, user.getEmailId())
                .setParameter(4, user.getRoleId())
                .executeUpdate();
    }


    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public List<Users> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Users> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Users> findAllById(Iterable<Integer> integers) {
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
    public void delete(Users entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Users> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Users> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Users> findById(Integer integer) {
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
    public <S extends Users> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Users> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Users getOne(Integer integer) {
        return null;
    }

    @Override
    public Users getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Users> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Users> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Users> boolean exists(Example<S> example) {
        return false;
    }

    public List<Announcements> listAnnouncementForStudents(Integer studentId){
        System.out.println(studentId);
        return em.createNativeQuery("select A.instructor_id,announcement_text,created_at " +
                "from Classroom join Announcements A on Classroom.instructor_id = A.instructor_id where student_id = ?")
                .setParameter(1, studentId)
                .getResultList();
    }
}
