package CS434.ExamPortal.behavioralPattern.observerPattern;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.DAO.ClassroomStudent;

public interface Observer {
    void update(ClassroomStudent classroom, Integer announcementId);
    ClassroomStudent getClassroom();

}
