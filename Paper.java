/**
 * @course ISTE.330.01
 * @version Project.01
 * @author Pallotta, Andrea
 *         Christoforo, Jake
Liu, Kevin

Sause, Daniel
Wesel, Blake
 */

import java.util.*;

public class Paper {

    private MySQLDatabase mysql;

    // Paper object info
    private int paperId; //paperId - Not initialized to check if this is a new account in setPaper;
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
     *  @param mysql - the database data layer object
     *  Database constructor for Paper object
     *  initializes blank or unharmful values
     *  @return none
     */
    public Paper(MySQLDatabase mysql) {
        filename = "";
        submissionTitle = "";
        submissionAbstract = "";
        submissionType = 0;
        this.mysql = mysql;
    }

    /**
     * @param
     */
    public Paper(MySQLDatabase mysql, int paperId, String filename, String submissionTitle,
                 String submissionAbstract, int submissionType) {
        this.paperId            = paperId;
        this.filename           = filename;
        this.submissionTitle    = submissionTitle;
        this.submissionAbstract = submissionAbstract;
        this.submissionType     = submissionType;
        this.mysql              = mysql;
    }



    /**
     *  @param (int) - paperId
     *  returns all info for a specific paper EXCEPT filename
     *  @return String
     */
    public ArrayList<ArrayList<String>> getPaper(int paperId) {
        ArrayList<ArrayList<String>> results = null;
        try {
            String query = "SELECT * FROM papers WHERE paperId = ?";
            data.add(paperId + "");

            //getData() method will print out values
            results = mysql.getData(query,data);
            data.clear();
        }
        catch (Exception _e)
        {
            new DLException(_e);

        }
        return results;
    }



    /**
     *  @param (int,String,String,int,String,String[],String[],String[]) - paperId, submissionTitle, submissionAbstract, submissionType, filename, subjects, firstnames, lastnames
     *  @return none
     */
    public void setPaper(int paperId, String submissionTitle, String submissionAbstract, int submissionType,
                         String filename, String[] subjects, String[] firstnames, String[] lastnames) {
        int results = 0;

        try {
            if (this.paperId == 0 || this.paperId == -1) {

                mysql.startTrans();

                results = mysql.setData("INSERT INTO papers (paperId, title, abstract, submissionType, fileid) VALUES (?, ?, ?, ?, ?)", paperId + "", submissionTitle, submissionAbstract, submissionType + "", filename);
                System.out.println("INSERT paper Query OK, " + results + " rows affected");
                for (int i=0; i< firstnames.length; i++) {
                    ArrayList<ArrayList<String>> resultsSelect = mysql.getData("(SELECT userId from users where firstname=? AND lastname=? LIMIT 1)", firstnames[i], lastnames[i]);
                    results = mysql.setData(
                            "INSERT INTO paperauthors (paperId, userId) VALUES (?, ?)",
                            paperId+"", resultsSelect.get(0).get(0));

                    System.out.println("INSERT paperauthor Query OK, " + results + " rows affected");
                    if ( results == 0) {
                        System.out.println("This user: " + lastnames[i] +
                                "," + firstnames[i] + " Does not exist. Please have this user create an account.");
                        mysql.rollbackTrans();
                    }
                }

            } else {

                results = mysql.setData("DELETE FROM paperauthors where paperId=?", paperId+"");
                System.out.println("Query OK, " + results + " rows affected");

                results = mysql.setData("DELETE FROM paperSubjects where paperId=?", paperId+"");

                for (int i=0; i< firstnames.length; i++) {
                    results = mysql.setData(
                            "INSERT INTO paperauthors (paperId, userId) VALUES (?, (SELECT userId from users where firstname='?' AND lastname='?' LIMIT 1))",
                            paperId+"", firstnames[i], lastnames[i]);
                    System.out.println("Query OK, " + results + " rows affected");
                    if ( results == 0) {
                        System.out.println("This user: " + lastnames[i]
                                + "," + firstnames[i] + " Does not exist. Please have this user create an account.");
                        mysql.rollbackTrans();
                    }

                }
                for(int i =0; i<subjects.length; i++) {
                    results = mysql.setData("INSERT INTO papersubjects (paperId, subjectId) VALUES (?,(SELECT subjectId FROM _subjects WHERE subjectName=?))",paperId+"",subjects[i]);
                    System.out.println("Query OK, " + results + " rows affected");
                }

                results = mysql.setData("UPDATE papers SET title=?, abstract=?, fileId=? WHERE paperId=?",submissionTitle, submissionAbstract,filename,paperId+"");
                System.out.println("Query OK, " + results + " rows affected");
            }
        } catch (Exception e) {
            System.out.println("UPDATE or INSERT was unsuccessful. Check your connection, query and paperId. ");
            mysql.rollbackTrans();
            new DLException(e);

        }
        mysql.endTrans();

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
