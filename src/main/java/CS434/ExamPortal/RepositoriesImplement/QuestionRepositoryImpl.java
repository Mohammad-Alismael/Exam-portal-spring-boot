package CS434.ExamPortal.RepositoriesImplement;

import CS434.ExamPortal.DAO.Exams;
import CS434.ExamPortal.DAO.Questions;
import CS434.ExamPortal.DTO.QuestionsDTO;
import CS434.ExamPortal.Repositories.QuestionRepository;
import CS434.ExamPortal.Repositories.UserRepository;
import CS434.ExamPortal.behavioralPattern.nullObject.INullObject;
import CS434.ExamPortal.behavioralPattern.nullObject.NullObject;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

public class QuestionRepositoryImpl implements QuestionRepository {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    public EntityManager em;

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
    public List<Questions> findQuestionsByCreatorExamId(String examId) {
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

    @Transactional
    @Override
    public void addQuestion(Questions questions) {
        System.out.println(questions);
        em.createNativeQuery("INSERT INTO Questions " +
                "(question_type, creator_exam_id,question_text,points,exam_id,is_active,who_can_see) " +
                "VALUES (?,?,?,?,?,?,?)")
                .setParameter(1, questions.getQuestionType())
                .setParameter(2, questions.getCreatorExamId())
                .setParameter(3, questions.getQuestionText())
                .setParameter(4, questions.getPoints())
                .setParameter(5, questions.getExamId())
                .setParameter(6, questions.getIsActive())
                .setParameter(7, questions.getWhoCanSee())
                .executeUpdate();
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

    public ArrayList<QuestionsDTO> getUserAnswers(Integer studentId, String examId){
        List resultList = em.createNativeQuery("select distinct U.id,U.question_id,Q.question_type,U.user_answer from User_answer U\n" +
                "    join Questions Q on Q.question_id = U.question_id\n" +
                "    join Exams E on E.exam_id = Q.exam_id\n" +
                "where user_id = ? and E.exam_id = ?")
                .setParameter(1, studentId)
                .setParameter(2, examId)
                .getResultList();
        ArrayList<QuestionsDTO> questionsDTOS = new ArrayList<>();
        Iterator itr = resultList.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            QuestionsDTO questionsDTO = new QuestionsDTO();
            Integer answerId = Integer.parseInt(String.valueOf(obj[0]));
            questionsDTO.setAnswerId(answerId);
            Integer questionId = Integer.parseInt(String.valueOf(obj[1]));
            questionsDTO.setQuestionId(questionId);
            Integer questionType = Integer.parseInt(String.valueOf(obj[2]));
            questionsDTO.setQuestionType(questionType);
            Integer userAnswer  = Integer.parseInt(String.valueOf(obj[3] == null ? "-1" : obj[3]));
            questionsDTO.setUserAnswer(userAnswer);
            System.out.println("answerId=> "+answerId);
            System.out.println("questionId=> "+questionId);
            System.out.println("questionType=> "+questionType);
            System.out.println("userAnswer=> "+userAnswer);
            questionsDTOS.add(questionsDTO);
        }

        return questionsDTOS;
    }

    public ArrayList<QuestionsDTO> getResult(Integer studentId, String examId){
        List resultList = em.createNativeQuery("select distinct U.question_id,Q.question_type,U.user_answer,U.is_correct,U.points,AK.correct_answer " +
                "from User_answer U\n" +
                "    join Questions Q on Q.question_id = U.question_id\n" +
                "    join Exams E on E.exam_id = Q.exam_id\n" +
                "    join Answer_key Ak on Q.question_id = Ak.question_id\n" +
                "where user_id = ? and E.exam_id = ?")
                .setParameter(1, studentId)
                .setParameter(2, examId)
                .getResultList();
        ArrayList<QuestionsDTO> questionsDTOS = new ArrayList<>();
        Iterator itr = resultList.iterator();
        while(itr.hasNext()){
            Object[] obj = (Object[]) itr.next();
            QuestionsDTO questionsDTO = new QuestionsDTO();
            Integer questionId = Integer.parseInt(String.valueOf(obj[0]));
            questionsDTO.setQuestionId(questionId);
            Integer questionType = Integer.parseInt(String.valueOf(obj[1]));
            questionsDTO.setQuestionType(questionType);
            Integer userAnswer  = Integer.parseInt(String.valueOf(obj[2] == null ? "-1" : obj[3]));
            questionsDTO.setUserAnswer(userAnswer);
            Integer isCorrect  = Integer.parseInt(String.valueOf(obj[3]));
            questionsDTO.setIsCorrect(isCorrect);
            questionsDTO.setUserAnswer(userAnswer);
            Integer points = Integer.parseInt(String.valueOf(obj[4]));
            questionsDTO.setPoints(points);
            Integer correctAnswer = Integer.parseInt(String.valueOf(obj[5]));
            questionsDTO.setCorrectAnswer(correctAnswer);
            System.out.println("questionId=> "+questionId);
            System.out.println("questionType=> "+questionType);
            System.out.println("userAnswer=> "+userAnswer);
            questionsDTOS.add(questionsDTO);
        }

        return questionsDTOS;
    }

}
