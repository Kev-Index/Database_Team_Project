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
        // Testing paper class
        Paper paper = new Paper();
        paper.getPaper(38);
        // updating
        String[] var1 = {"subject1", "subject2"};
        String[] var2 = {"Jack", "Blake"};
        String[] var3 = {"Harlow", "Wesel"};
        paper.setPaper(1, "test", "testing", 1, "this is a test", var1 , var2, var3);
        // setting nothing
        
    }

}