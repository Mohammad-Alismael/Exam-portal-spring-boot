package CS434.ExamPortal.behavioralPattern.nullObject;

public class NullObject implements INullObject {
    public static NullObject quest = new NullObject();
    @Override
    public boolean isAvailable() {
        return false;
    }

    public static NullObject getInstance() {
        return quest;
    }
}