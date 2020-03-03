/**
 * @course ISTE.330.01
 * @version Project.01
 * @author Christoforo, Jake
           Liu, Kevin 
           Pallotta, Andrea
           Sause, Daniel
           Wesel, Blake
 */
 
public class User {
   //User object info
   private int    userId;
   private String lastName;
   private String firstName;
   private String email;
   private String pswd;
   private String affiliation;
   
   /**
    * @param none
    * default constructor for User object
    * initializes blank or unharmful values
    * @return none
   */
   public User() {
      userId             = 0;
      lastName           = "";
      firstName          = "";
      String email       = "";
      String pswd        = "";
      String affiliation = "";
   }
   
   /**
    * @param (int) - The user ID
    * returns all papers for specified user
    * @return Paper[]
   */
   public Paper[] getPapers(int userId) {
      return null;
   }
   
   /**
    * @param none
    * returns attributes of User EXCEPT password - FORMAT(userId, lastName, firstName, email, affiliation)
    * @return String
   */
   public String[] getUser() {
      String[] info = new String[5];
      info[0] = userId;
      info[1] = lastName;
      info[2] = firstName;
      info[3] = email;
      info[4] = affiliation;
      
      return info;
   }
   
   /**
    * @param (int,String,String,String,String,String) - userId, lastName, firstName, email, pswd, affiliation
    * updates User attributes to db
    * creates new entry if no matching userId
    * @return none
   */
   public void setUser(int userId, String lastName, String firstName, String email, String pswd, String affiliation) {
      if (userId == 0 || userId == null) {
         // create a new "entry" - whatever that means
      } else {
         this.userId      = userId;
         this.lastName    = lastName;
         this.firstName   = firstName;
         this.email       = email;
         this.pswd        = pswd;
         this.affiliation = affiliation;
      }
   }
   
   /** 
    * @param (String) - email
    * creates new password and emails to specified address
    * ONLY GOOD FOR 5 MINUTES      
    * @return none
   */
   public void resetPassword(String email) {
      
   }
   
   /** 
    * @param (String) - password
    * changes password of currently logged in user to param
    * @return none
   */
   public void setPassword(String pswd) {
      this.pswd = pswd;
   }
   
   /**
    * @param (String,String) - Email and password
    * IF good credentials, returns token
    * @return token
   */
   public void login(String email, String password) {
   
   }
   
   //accessors
   public String getLastName() {
      return lastName;
   }
   
   public String getFirstName() {
      return firstName;
   }
   
   public String getEmail() {
      return email;
   }
   
   public String getPswd() {
      return pswd;
   }
   
   public String getAffil() {
      return affiliation;
   }
   
   //mutators
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   
   public void setFirstName(String firstName) {
      this.lastName = firstName;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   public void setPswd(String pswd) {
      this.pswd = pswd;
   }
   
   public void setAffil(String affiliation) {
      this.affiliation = affiliation;
   }
}