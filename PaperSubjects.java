/*
 * @course ISTE.330.01
 * @version Project.01
 * @author Christoforo, Jake
           Liu, Kevin 
           Pallotta, Andrea
           Sause, Daniel
           Wesel, Blake
 */
 
public class PaperSubjects
{

    private int paperId;
    private int subjectId;

    public PaperSubjects() {
        this.paperId = -1;
        this.subjectId = -1;
    }

    public PaperSubjects(int paperId, int subjectId) {
        this.paperId = paperId;
        this.subjectId = subjectId;
    }

    public int getPaperId() {
        return this.paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    

}