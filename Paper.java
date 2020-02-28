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
   //Paper object info
   private int paperId;
   private String filename;
   private String[] subjects;
   
   //co-authors info
   private String[] firstnames;
   private String[] lastnames;
   
   //submission info
   private String submissionTitle;
   private String submissionAbstract;
   private int submissionType;

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
      @param (int)
      returns all info for a specific paper EXCEPT filename
      @return String
   */
   public String getPaper(int _paperId)
   {
      //create temp String to store full names
      String tempNames = "";
      //iterate through length of array for names
      for (int i = 0; i < firstnames.length; i++)
      {
         //concatenate first name and last name together
         if (i == 0)
         {
            tempNames += (firstnames[i] + " " + lastnames[i]);
         }
         else
         {
            tempNames += (", " + firstnames[i] + " " + lastnames[i]);
         }
      }
      
      //create temp String to store subjects
      String tempSubs = "";
      for (int i = 0; i < subjects.length; i++)
      {
         //contatenate subjects into one String for display
         if (i == 0)
         {
            tempSubs += subjects[i];
         }
         else
         {
            tempNames += (", " + subjects[i]);
         }
      }
   
      return ("Paper ID:" + paperId +
               "\nSubmission Title:" + submissionTitle +
               "\nSubmission Abstract:" + submissionAbstract +
               "\nSubmission Type:" + submissionType +
               "\nSubjects: " + tempSubs +
               "\nCo-Authors:" + tempNames);



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