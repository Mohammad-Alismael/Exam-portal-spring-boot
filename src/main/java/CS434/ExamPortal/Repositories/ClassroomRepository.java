package CS434.ExamPortal.Repositories;

import CS434.ExamPortal.DAO.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {

    List<Classroom> findClassroomByInstructorId(Integer instructorID);

}
