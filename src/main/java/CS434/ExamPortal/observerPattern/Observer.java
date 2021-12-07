package CS434.ExamPortal.observerPattern;

import CS434.ExamPortal.DAO.Classroom;

public interface Observer {
    void update(Classroom classroom);
    Classroom getClassroom();
    void setClassroom(Classroom classroom);

}
