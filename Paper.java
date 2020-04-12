/**
 * @course ISTE.330.01
 * @version Project.01
 * @author Pallotta, Andrea
 *         Christoforo, Jake
           Liu, Kevin 
           
           Sause, Daniel
           Wesel, Blake
 */
 
public class Paper {

   // Paper object info
   private int paperId; //paperId
   private String filename; //title
   private String fileId; //fileId
   private String[] subjects; //array of subjectName
   private String[] track; //track
   private String status; //status
   private String tStatus; //tentativeStatus
   
   // Co-authors info
   private String[] firstnames;
   private String[] lastnames;
   
   // Submission info
   private String submissionTitle;
   private String submissionAbstract; //abstract
   private int submissionType; //submissionType
   private String submitterId; //submitterId
           
   // Paper Data to be Changed
   private ArrayList<String> data = new ArrayList<String>();

           
           
   /**
    *  @param none
    *  default constructor for Paper object
    *  initializes blank or unharmful values 
    *  @return none
    */
   public Paper() {
      paperId = 0;
      filename = "";
      submissionTitle = "";
      submissionAbstract = "";
      submissionType = 0;
   }
   
           
           
   /**
    *  @param (int) - paperId
    *  returns all info for a specific paper EXCEPT filename
    *  @return String
    */
   public String getPaper(int paperId) {
      // Access DB and do a search query for paper ID
   }
  
           
           
   /**
    *  @param (int,String,String,int,String,String[],String[],String[]) - paperId, submissionTitle, submissionAbstract, submissionType, filename, subjects, firstnames, lastnames
    *  default constructor for Paper object
    *  @return none
    */
   public void setPaper(int paperId, String submissionTitle, String submissionAbstract, int submissionType, 
                        String filename, String[] subjects, String[] firstnames, String[] lastnames) {
      if(this.paperId == null || this.paperId == 0) {
         // create new "entry" - what ever that means...

      } else {
         this.paperId            = paper;
         this.submissionTitle    = submissionTitle;
         this.submissionAbstract = submissionAbstract;
         this.submissionType     = submissionType;
         this.filename           = filename;
         this.subjects           = subjects;
         this.firstnames         = firstnames;
         this.lastnames          = lastnames;
      }
      
   }
   
           
           
   // Accessors
   public int getId() {
      return paperId;
   }
   
   public String getSubTitle() {
      return submissionTitle;
   }
   
   public String getSubAbstract() {
      return submissionAbstract;
   }
   
   public int getSubType() {
      return submissionType;
   }
   
   public String getFileName() {
      return filename;
   }
   
   public String[] getSubjects() {
      return subjects;
   }
   
   public String[] getFirstNames() {
      return firstnames;
   }
   
   public String[] getLastNames() {
      return lastnames;
   }
   
           
           
   // Mutators
   public void setId(int paperId) {
      this.paperId = paperId;
   }
   
   public void setSubTitle(String submissionTitle) {
      this.submissionTitle = submissionTitle;
   }
   
   public void setSubAbstract(String submissionAbstract) {
      this.submissionAbstract = submissionAbstract;
   }
   
   public void setSubType(int submissionType) {
      this.submissionType = submissionType;
   }
   
   public void setFileName(String filename) {
      this.filename = filename;
   }
   
   public void setSubjects(String[] subjects) {
      this.subjects = subjects;
   }
   
   public void setFirstNames(String[] firstnames) {
      this.firstnames = firstnames;
   }
   
   public void setLastNames(String[] lastnames) {
      this.lastnames = lastnames;
   }
}
