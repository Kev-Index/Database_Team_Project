import java.util.ArrayList;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
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
   private MySQLDatabase mysql;
   private int userId;
   private String lastName;
   private String firstName;
   private String email;
   private String pswd;
   private String canReview; // default is false for security purposes
   private String expiration;
   private int isAdmin;
   private int affiliationId;

   Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
   private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
   private static final String NUMBERS = "0123456789";
   private static final String POSSIBLE_COMBINATIONS = LOWERCASE + NUMBERS;

   
   /**
    * default constructor
    */
   public User(MySQLDatabase mysql) {
      this.userId = 0;
      this.lastName = "";
      this.firstName = "";
      this.email = "";
      this.pswd = "";
      this.canReview = "";
      this.expiration = "";
      this.isAdmin = 0;
      this.affiliationId = 0;
      this.mysql = mysql;
   }

   /**
    * parameterized constructor
    * @param email
    */
    public User(String email, MySQLDatabase mysql) {
      this.userId = 0;
      this.lastName = "";
      this.firstName = "";
      this.email = email;
      this.pswd = "";
      this.canReview = "";
      this.expiration = "";
      this.isAdmin = 0;
      this.affiliationId = 0;
      this.mysql = mysql;
   }

   /**
    * parameterized constructor
    * @param email
    * @param pswd
    */
   public User(String email, String pswd, MySQLDatabase mysql) {
      this.userId = 0;
      this.lastName = "";
      this.firstName = "";
      this.email = email;
      this.pswd = pswd;
      this.canReview = "";
      this.expiration = "";
      this.isAdmin = 0;
      this.affiliationId = 0;
      this.mysql = mysql;
   }
   

   /**
    * parameterized constructor
    * @param userId
    * @param email
    * @param pswd
    */
    public User(int userId, String email, String pswd, MySQLDatabase mysql) {
      this.userId = userId;
      this.lastName = "";
      this.firstName = "";
      this.email = email;
      this.pswd = pswd;
      this.canReview = "";
      this.expiration = "";
      this.isAdmin = 0;
      this.affiliationId = 0;
      this.mysql = mysql;
   }
   
   /**
    * method to retrive papers for specified user
    * @param userId
    * @return
    */
   public ArrayList<String> getPapers(int userId) {
      String query = "SELECT papers.title from papers LEFT JOIN paperauthors ON papers.paperId = paperauthors.paperId LEFT JOIN users ON paperauthors.userId = ? GROUP BY papers.title";
      try {
         ArrayList<ArrayList<String>> results = mysql.getData(query, userId + "");
         return results.get(0);
      }

      catch (Exception e) {
         System.out.println("There is no paper written by the user with id: " +  getUserId() + ". Check your connection and userId. ");
         new DLException(e);        
         return null;
       }
   }
   
   /**
    * method that returns information for instantiated user
    * @return ArrayList<String>
    */
   public ArrayList<String> getUser() {
      try {
         ArrayList<ArrayList<String>> results = mysql.getData("SELECT userId, lastName, firstName, email, canReview, expiration, isAdmin, affiliationId FROM USERS WHERE userId = ?", getUserId() + "");

         setUserId(Integer.parseInt(results.get(0).get(0)));
         setLastName(results.get(0).get(1));
         setFirstName(results.get(0).get(2));
         setEmail(results.get(0).get(3));
         setCanReview(results.get(0).get(4));
         setExpiration(results.get(0).get(5));
         setIsAdmin(Integer.parseInt(results.get(0).get(6)));
         setAffil(Integer.parseInt(results.get(0).get(7)));
         return results.get(0);
      }

      catch (Exception e) {
         System.out.println("Info retrive failed. Check your connection and id. ");
         new DLException(e);
         return null;
       }
   }
   
   /**
    * method that updates info or creates new entry in the database
    * @param lastName
    * @param firstName
    * @param email
    * @param pswd
    * @param affiliation
    */
   public void setUser(String lastName, String firstName, String email, String pswd, int affiliation) {
      try {
         if (getUserId() == 0) {
            int results = mysql.setData("INSERT INTO users (lastname, firstname, email, pswd, affiliationId) VALUES (?, ?, ?, ?, ?)", lastName, firstName, email, pswd, affiliation + "");
            System.out.println("Query OK, " + results + " rows affected");
         }
   
         else {
            int results = mysql.setData("UPDATE users SET lastname = ?, firstname = ?, email = ?, pswd = ?, affiliationId = ? WHERE userId = ?", lastName, firstName, email, pswd, affiliation + "", getUserId() + "");
            System.out.println("Query OK, " + results + " rows affected");
         }
      }

      catch (Exception e) {
         System.out.println("UPDATE or INSERT was unsuccessful. Check your connection, query and userId. ");
         new DLException(e);       
      }
      
   }

   /**
    * method to generate a random password of 45 chars (lowercase letters and numbers only)
    * @return newPassword
    */
   public String pswdGenerator() {
      
      Random random = new Random();
      char[] newPassword = new char[45];

      for (int i = 0; i < 45; i++) {
         newPassword[i] = POSSIBLE_COMBINATIONS.charAt(random.nextInt(POSSIBLE_COMBINATIONS.length()));
      }
      return new String(newPassword);
   }
   

   /**
    * method to reset password. New password is valid for only 5 minutes.
    */
   public void resetPassword() {
      
      try {
         String query = "UPDATE users SET pswd = ?, expiration = ? WHERE email = ?";
         Date currentDate = new Date((System.currentTimeMillis()+5*60*1000));
         String newExpTime = formatter.format(currentDate);
         String newPassword = pswdGenerator();
         int results = mysql.setData(query, newPassword, newExpTime, getEmail());
         System.out.println("Query OK, " + results + " rows affected");
         
         // @TEST ONLY
         System.out.println("The new password is " + newPassword);

      }

      catch (Exception e) {
         System.out.println("Reset Password failed. Check your connection. ");
         new DLException(e);
       }

   }
   
   /**
    * method to change the password of a logged in user
    * @param newPassword
    */
   public void setPassword(String newPassword) {
      
      try {
         if (login()) {
            String query = "UPDATE users SET pswd = ?, expiration = ? WHERE email = ?";
            int results = mysql.setData(query, newPassword, "20250101000000", getEmail());
            System.out.println("Query OK, " + results + " rows affected");
         } else {
            System.out.println("login() failed");
         }
   
       }
   
       catch (Exception e) {
         System.out.println("Changing password failed. Check your connection and email.");
         new DLException(e);
       }
   }

    /**
   * Retrives password and returns result of the login. If the username and password match, 
   * return true. Otherwise, return false.
   * @return boolean
   */
  public boolean login() {
   try {
    ArrayList<ArrayList<String>> results = mysql.getData("SELECT * FROM users WHERE email = ? AND pswd = ?", getEmail(), getPswd()); 
    if (!results.isEmpty()) {
         String expiration = results.get(0).get(6).substring(0,4) + "/" + results.get(0).get(6).substring(4,6) + "/" + results.get(0).get(6).substring(6,8) + " " + 
                             results.get(0).get(6).substring(8,10) + ":" + results.get(0).get(6).substring(10,12) + ":" + results.get(0).get(6).substring(12,14); 
         Date currentDate = new Date();
         Date expirationDate = new  SimpleDateFormat("yyyy/MM/dd HH:mm").parse(expiration);
         
         if (expirationDate.compareTo(currentDate) > 0) {
            setUserId(Integer.parseInt(results.get(0).get(0)));
            setLastName(results.get(0).get(1));
            setFirstName(results.get(0).get(2));
            setCanReview(results.get(0).get(5));
            setExpiration(results.get(0).get(6));
            setIsAdmin(Integer.parseInt(results.get(0).get(7)));
            setAffil(Integer.parseInt(results.get(0).get(8)));
            return true;
         }

         else {
            System.out.println("the credentials are expired");
            return false;
         }
    } 
    System.out.println("results is empty");
    return false;
   }

   catch (Exception e) {
     System.out.println("Login failed. Check your connection and credential");
     new DLException(e);
     return false;
   }
} // end of login

           
   /**
   * Checks authorization to edit
   * @return boolean
   */
   public boolean checkAuthorization(int inId)
   {
      boolean valid = false;
      
      // 1 is Admin, 0 is Not Admin
      if (getIsAdmin() == 1)
      {
         valid = true;
      }
      else
      {
         if (inId == getUserId())
         {
            valid = true;
         }
      }
      
      return valid;
   }

   
   //accessors
   public int getUserId() {
      return this.userId;
   }

   public String getLastName() {
      return this.lastName;
   }
   
   public String getFirstName() {
      return this.firstName;
   }
   
   public String getEmail() {
      return this.email;
   }
   
   public String getPswd() {
      return this.pswd;
   }

   public String getCanReview() {
      return this.canReview;
   }

   public String getExpiration() {
      return this.expiration;
   }

   public int getIsAdmin() {
      return this.isAdmin;
   }
   
   public int getAffil() {
      return this.affiliationId;
   }
   
   //mutators
   public void setUserId(int userId) {
      this.userId = userId;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   public void setPswd(String pswd) {
      this.pswd = pswd;
   }

   public void setCanReview(String canReview) {
      this.canReview = canReview;
   }

   public void setExpiration(String expiration) {
      this.expiration = expiration;
   }

   public void setIsAdmin(int isAdmin) {
      this.isAdmin = isAdmin;
   }
   
   public void setAffil(int affiliationId) {
      this.affiliationId = affiliationId;
   }
}
