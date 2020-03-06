/*
 * @course ISTE.330.01
 * @version Project.01
 * @author Christoforo, Jake
           Liu, Kevin 
           Pallotta, Andrea
           Sause, Daniel
           Wesel, Blake
 */
 
public class PaperAuthors
{
    private int paperId;
    private int userId;
    private int displayOrder;

    public PaperAuthors() {
        this.paperId = -1;
        this.userId = -1;
        this.displayOrder = -1;
    }

    public PaperAuthors(int paperId, int userId, int displayOrder) {
        this.paperId = paperId;
        this.userId = userId;
        this.displayOrder = displayOrder;
    }

    public int getPaperId() {
        return this.paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
   

}