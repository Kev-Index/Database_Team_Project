/*
 * @course ISTE.330.01
 * @version Project.01
 * @author Christoforo, Jake
           Liu, Kevin 
           Pallotta, Andrea
           Sause, Daniel
           Wesel, Blake
 */
 
public class Affilliations
{
    private int affilliationId;
    private String affilliationName;

    public Affilliations() {
        this.affilliationId = -1;
        this.affilliationName = "";
    }

    public Affilliations(int affilliationId, String affilliationName) {
        this.affilliationId = affilliationId;
        this.affilliationName = affilliationName;
    }

    public int getAffilliationId() {
        return this.affilliationId;
    }

    public void setAffilliationId(int affilliationId) {
        this.affilliationId = affilliationId;
    }

    public String getAffilliationName() {
        return this.affilliationName;
    }

    public void setAffilliationName(String affilliationName) {
        this.affilliationName = affilliationName;
    }

    

}