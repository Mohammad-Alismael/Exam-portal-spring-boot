package CS434.ExamPortal.observerPattern;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClassroomObserver implements Observer{
    private Classroom classroom;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private ClassroomRepository classroomRepository;

    public ClassroomObserver(Classroom classroom) {
        this.classroom = classroom;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }



    @Override
    public void update(Classroom classroom) {

    }
}
