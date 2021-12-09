package CS434.ExamPortal.behavioralPattern.observerPattern;

import CS434.ExamPortal.DAO.Classroom;

public interface Observer {
    void update(Classroom classroom, String announcementText);
    Classroom getClassroom();

}
