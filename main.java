/**
 * Main class that's used to start the program, used to test the program
 * @course ISTE.330.01
 * @version Project.01
 * @author  Christoforo, Jake
            Liu, Kevin
            Pallotta, Andrea
            Sause, Daniel
            Wesel, Blake
 */
public class main {

    public static void main(String[] args) {

        // MySQLDatabase object
        MySQLDatabase mysql = new MySQLDatabase();
        mysql.connect();

        // Testing paper class
        Paper paper = new Paper(mysql);
        System.out.println("\nTesting Paper:\n");
        System.out.println("Paper getPaper result: " + paper.getPaper(38));

        // Paper updating
        String[] subjects = {"Accreditation and Assessment", "Administration of IT Education"};
        String[] first = {"Dan", "Stephen"};
        String[] last = {"Shao", "Zilora"};

        System.out.println("\nPaper setPaper with Id -1 results: ");
        paper.setPaper(-1, "testTitle", "testingAbstract", 1, "this is a test", subjects , first, last);

        // setting nothing
        System.out.println("\nPaper setPaper with Id 1 results: ");
        paper.setPaper(1, "testTitle", "testingAbstract", 1, "this is a test", subjects , first, last);

        // Testing user class
        System.out.println("\nTesting User:\n");
        User user = new User(mysql);
        user.setUserId(1);
        user.setEmail("stevez@cssconsult.com");
        System.out.println("User getPapers result:            " + user.getPapers(1));
        System.out.println("User getUser result:              " + user.getUser());

        user.setUser("Zilora","Stephen","stevez@cssconsult.com","Szilora123",78);

        // Testing login method for user with correct credentials with password that hasn't expired
        User loginUser1 = new User("Dan.Bogaard@rit.edu","bbce481b0d319a837446124a3edf39be7af063ba",mysql);
        System.out.println("Login results correct creds:      " + loginUser1.login());

        // Testing login method for user with expired password
        User loginUser2 = new User("dgerman@indiana.edu","706b5dc4aaec8ee2b85487f8a4b471223896446e",mysql);
        System.out.println("Login results expired password:   " + loginUser2.login());

        // Testing setPassword method for user with correct credentials with password that hasn't expired
        User setPasswordUser1 = new User("Dan.Bogaard@rit.edu","bbce481b0d319a837446124a3edf39be7af063ba",mysql);
        setPasswordUser1.setPassword("veryStrongPassword");

        // Testing resetPassword method
        setPasswordUser1.resetPassword();
    }

}