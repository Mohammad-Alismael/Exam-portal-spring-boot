package CS434.ExamPortal.behavioralPattern.nullObject;

public class NullQuestions implements IQuestions{
    public static NullQuestions quest = new NullQuestions();
    @Override
    public boolean isAvailable() {
        return false;
    }

    public static NullQuestions getInstance() {
        return quest;
    }
}
