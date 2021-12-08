package CS434.ExamPortal.observerPattern;

import CS434.ExamPortal.DAO.Classroom;
import CS434.ExamPortal.Repositories.ClassroomRepository;
import CS434.ExamPortal.RepositoriesImplement.UserRepositoryImpl;
import CS434.ExamPortal.behavioralPattern.nullObject.NullUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;

public class ClassroomSubscriber {
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private ClassroomRepository classroomRepository;

    private Integer InstructorId;

    public Integer getInstructorId() {
        return InstructorId;
    }

    public void setInstructorId(Integer instructorId) {
        ArrayList<Classroom> classrooms = classroomRepository.findClassroomByInstructorId(instructorId);
        for (Classroom classroom: classrooms){
            observers.add(new ClassroomObserver(classroom));
        }
    }

    public void subscribe(Observer classroomObserver) {
        observers.add(classroomObserver);
        NullUser user = userRepositoryImpl.findByUserId(classroomObserver.getClassroom().getStudentId());
        Classroom  classroom1 = classroomRepository
                .findClassroomByInstructorIdAndStudentId(
                        classroomObserver.getClassroom().getInstructorId(),
                        classroomObserver.getClassroom().getStudentId());
        if (!user.isAvailable()) if (classroom1 == null) classroomRepository.save(classroomObserver.getClassroom());

    }

    public void unsubscribe(Observer classroomObserver) {
        observers.remove(classroomObserver);
        NullUser user = userRepositoryImpl.findByUserId(classroomObserver.getClassroom().getStudentId());
        Classroom  classroom1 = classroomRepository
                .findClassroomByInstructorIdAndStudentId(
                        classroomObserver.getClassroom().getInstructorId(),
                        classroomObserver.getClassroom().getStudentId());
        if (!user.isAvailable()) if (classroom1 == null) classroomRepository.
                removeByInstructorIdAndStudentId(classroom1.getInstructorId(),
                        classroom1.getStudentId());
    }

    public void notifySubscriber(String announcementText) {
        Iterator<?> it = observers.iterator();
        while (it.hasNext()) {
            Observer o = (Observer) it.next();
            o.update(o.getClassroom(),announcementText);
        }
    }

}
