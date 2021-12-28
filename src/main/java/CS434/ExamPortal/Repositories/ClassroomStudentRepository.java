package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.ClassroomStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomStudentRepository extends JpaRepository<ClassroomStudent,Integer> {
    List<ClassroomStudent> findClassroomStudentByClassroomId(Integer classroomId);
    ClassroomStudent findByStudentId(Integer studentId);
}
