/*
 * @course ISTE.330.01
 * @version Project.01
 * @author Christoforo, Jake
           Liu, Kevin 
           Pallotta, Andrea
           Sause, Daniel
           Wesel, Blake
 */
 
public class Paper
{
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

   /*
      @param none
      default constructor for Paper object
      initializes blank or unharmful values
      @return none
   */
   public Paper()
   {
      paperId = 0;
      filename = "";
      submissionTitle = "";
      submissionAbstract = "";
      submissionType = 0;
   }
   
   /*
      @param (int,MySQLDatabase)
      returns all info for a specific paper EXCEPT filename
      @return String
   */
   public String getPaper(int _paperId, MySQLDatabase mysqldb)
   {
      try
      {
      
         String query = "SELECT * FROM papers WHERE paperId = ?";
         data.add(getId());
         
         //getData() method will print out values
         mysqldb.getData(query,data);
         data.clear();
         
      }  
      catch (Exception _e)
      {
         new DLException(_e,"Operation Failed When Retrieving Paper");
         
      }
   }
   
   /*
      @param (int,String,String,int,String,String[],String[],String[])
      default constructor for Paper object
      @return none
   */
   public void setPaper(int _paperId, String _submissionTitle, String _submissionAbstract, int _submissionType, String _filename, String[] _subjects, String[] _firstNames, String[] _lastnames)
   {
   
   }
   
   //accessors
   public int getId()
   {
      return paperId;
   }
   
   public String getSubTitle()
   {
      return submissionTitle;
   }
   
   public String getSubAbstract()
   {
      return submissionAbstract;
   }
   
   public int getSubType()
   {
      return submissionType;
   }
   
   public String getFileName()
   {
      return filename;
   }
   
   public String[] getSubjects()
   {
      return subjects;
   }
   
   public String[] getFirstNames()
   {
      return firstnames;
   }
   
   public String[] getLastNames()
   {
      return lastnames;
   }
   
   //mutators
   public void setId(int _paperId)
   {
      paperId = _paperId;
   }
   
   public void setSubTitle(String _submissionTitle)
   {
      submissionTitle = _submissionTitle;
   }
   
   public void setSubAbstract(String _submissionAbstract)
   {
      submissionAbstract = _submissionAbstract;
   }
   
   public void setSubType(int _submissionType)
   {
      submissionType = _submissionType;
   }
   
   public void setFileName(String _filename)
   {
      filename = _filename;
   }
   
   public void setSubjects(String[] _subjects)
   {
      subjects = _subjects;
   }
   
   public void setFirstNames(String[] _firstnames)
   {
      firstnames = _firstnames;
   }
   
   public void setLastNames(String[] _lastnames)
   {
      lastnames = _lastnames;
   }
}
