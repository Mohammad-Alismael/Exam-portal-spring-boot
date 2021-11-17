package CS434.ExamPortal.DAO;

import javax.persistence.*;

@Entity
@Table(name = "Question_with_picture")
public class QuestionWithPicture {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "filename_path")
    private String filenamePath;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getFilenamePath() {
        return this.filenamePath;
    }

    public void setFilenamePath(String filenamePath) {
        this.filenamePath = filenamePath;
    }
}
