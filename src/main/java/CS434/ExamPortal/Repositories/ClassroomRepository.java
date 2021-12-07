package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.DAO.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {

    ArrayList<Classroom> findClassroomByInstructorId(Integer instructorID);
    @Transactional
    void  removeByInstructorIdAndStudentId(Integer instructorID, Integer studentId);

    Classroom findClassroomByInstructorIdAndStudentId(Integer instructorID, Integer studentId);

    @Query(value = "select username,email_id from Users join Classroom C on Users.user_id = C.student_id where instructor_id=:instructorID;",
            nativeQuery = true)
    List<Users> listStudentsUsernamesByInstructorId(@Param("instructorID") Integer instructorID);
}
