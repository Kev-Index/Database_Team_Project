/*
 * @course ISTE.330.01
 * @version Project.01
 * @author Christoforo, Jake
           Liu, Kevin 
           Pallotta, Andrea
           Sause, Daniel
           Wesel, Blake
 */
 
public class User
{
   //User object info
   private String lastName;
   private String firstName;
   private String email;
   private String pswd;
   private String affiliation;
   
   /*
      @param none
      default constructor for User object
      initializes blank or unharmful values
      @return none
   */
   public User()
   {
      lastName = "";
      firstName = "";
      email = "";
      pswd = "";
      affiliation = "";
   }
   
   /*
      @param (int)
      returns all papers for specified user
      @return Paper[]
   */
   public Paper[] getPapers(int _userId)
   {
      return null;
   }
   
   /*
      @param none
      returns attributes of User EXCEPT password
      @return String
   */
   public String getUser()
   {
      return "";
   }
   
   /*
      @param (String,String,String,String,String)
      updates User attributes to db
      creates new entry if no matching userId
      @return none
   */
   public void setUser(String _lastName, String _firstName, String _email, String _pswd, String _affiliation)
   {
      
   }
   
   /*
      @param (String)
      creates new password and emails to specified address
      ONLY GOOD FOR 5 MINUTES      
      @return none
   */
   public void resetPassword(String _email)
   {
   
   }
   
   /*
      @param (String)
      changes password of currently logged in user to param
      @return none
   */
   public void setPassword(String _password)
   {
   
   }
   
   /*
      @param (String,String)
      IF good credentials, returns token
      @return token?
   */
   public void login(String _email, String _password)
   {
   
   }
   
   //accessors
   public String getLastName()
   {
      return lastName;
   }
   
   public String getFirstName()
   {
      return firstName;
   }
   
   public String getEmail()
   {
      return email;
   }
   
   public String getPswd()
   {
      return pswd;
   }
   
   public String getAffil()
   {
      return affiliation;
   }
   
   //mutators
   public void setLastName(String _lastName)
   {
      lastName = _lastName;
   }
   
   public void setFirstName(String _firstName)
   {
      lastName = _firstName;
   }
   
   public void setEmail(String _email)
   {
      email = _email;
   }
   
   public void setPswd(String _pswd)
   {
      pswd = _pswd;
   }
   
   public void setAffil(String _affiliation)
   {
      affiliation = _affiliation;
   }
}