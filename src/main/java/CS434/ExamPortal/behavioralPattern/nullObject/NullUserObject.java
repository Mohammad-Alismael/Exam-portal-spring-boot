package CS434.ExamPortal.behavioralPattern.nullObject;

public class NullUserObject implements NullUser{
    public static NullUserObject quest = new NullUserObject();
    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public Integer getRoleId() {
        return null;
    }
    public static NullUserObject getInstance() {
        return quest;
    }
}
