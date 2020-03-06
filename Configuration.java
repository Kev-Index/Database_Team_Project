import java.util.Date;

/*
 * @course ISTE.330.01
 * @version Project.01
 * @author Christoforo, Jake
           Liu, Kevin 
           Pallotta, Andrea
           Sause, Daniel
           Wesel, Blake
 */
 
public class Configuration
{

    private Date submissionOpen;
    private Date submissionClose;
    private String pcEmail;
    private String pcName;
    private String pc2Email;
    private String pc2Name;
    private String shortName;
    private String logoFile;

    public Configuration() {
        this.submissionOpen = null;
        this.submissionClose = null;
        this.pcEmail = "";
        this.pcName = "";
        this.pc2Email = "";
        this.pc2Name = "";
        this.shortName = "";
        this.logoFile = "";
    }

    public Configuration(Date submissionOpen, Date submissionClose, String pcEmail, String pcName, String pc2Email,
            String pc2Name, String shortName, String logoFile) {
        this.submissionOpen = submissionOpen;
        this.submissionClose = submissionClose;
        this.pcEmail = pcEmail;
        this.pcName = pcName;
        this.pc2Email = pc2Email;
        this.pc2Name = pc2Name;
        this.shortName = shortName;
        this.logoFile = logoFile;
    }

    public Date getSubmissionOpen() {
        return this.submissionOpen;
    }

    public void setSubmissionOpen(Date submissionOpen) {
        this.submissionOpen = submissionOpen;
    }

    public Date getSubmissionClose() {
        return this.submissionClose;
    }

    public void setSubmissionClose(Date submissionClose) {
        this.submissionClose = submissionClose;
    }

    public String getPcEmail() {
        return this.pcEmail;
    }

    public void setPcEmail(String pcEmail) {
        this.pcEmail = pcEmail;
    }

    public String getPcName() {
        return this.pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public String getPc2Email() {
        return this.pc2Email;
    }

    public void setPc2Email(String pc2Email) {
        this.pc2Email = pc2Email;
    }

    public String getPc2Name() {
        return this.pc2Name;
    }

    public void setPc2Name(String pc2Name) {
        this.pc2Name = pc2Name;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLogoFile() {
        return this.logoFile;
    }

    public void setLogoFile(String logoFile) {
        this.logoFile = logoFile;
    }

    

}