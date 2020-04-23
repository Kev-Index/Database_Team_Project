/**
 * @course ISTE.330.01
 * @version Project.01
 * @author Christoforo, Jake
Liu, Kevin
Pallotta, Andrea
Sause, Daniel
Wesel, Blake
 */

import java.sql.*;
import java.sql.ResultSetMetaData;
import java.util.*;

// start class MySQLDatabase
public class MySQLDatabase {



    // Attributes
    private Connection conn = null;
    private String id;
    private String password;
    private String uri;
    private String driver;



    // constructor that instantiate the driver and the connection parameters
    // and calls the methods to connect and close the connection
    public MySQLDatabase() {
        id = "root";
        password = "Bwesel7018"; // your password
        uri = "jdbc:mysql://localhost:3306/csm";
        driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|    Driver loaded      |");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");

        }

        catch (ClassNotFoundException cnfe) {
            System.out.println("Driver not found.");
        }
        catch (Exception e) {
            System.out.println("Error while connecting to the server.");
            new DLException(e);
        }
    } // end of constructor




    public MySQLDatabase(String uri, String id, String password) {

        this.uri = uri;
        this.id = id;
        this.password = password;

    } // end of constructor



    /**
     * method that creates the connection and starts the connection
     *
     * @return true if the connection is successful
     */
    public boolean connect() {
        try {
            conn = DriverManager.getConnection(uri, id, password);
            // use the following to throw an exception
            // conn = DriverManager.getConnection(wrongUri, id, password);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("| Connected to MySQLDatabase |");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return true;
        }

        catch (SQLException sqle) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("| Error connecting to the MySQLDatabase |");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return false;
        }

        catch (Exception e) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("| Error connecting to MySQLDatabase |");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            new DLException(e);
            return false;
        }

    } // end of connect()

    /**
     * method that closes the connection
     *
     * @return true if the connection is successfully closed
     */
    public boolean close() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("| Disconnected to MySQLDatabase |");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                return true;
            }

            return false;

        }

        catch (SQLException sqle) {

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("| Error disconnecting from MySQLDatabase |");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return false;
        }

        catch (Exception e) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("| Error disconnecting from MySQLDatabase |");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            new DLException(e);
            return false;
        }

    } // end of close()

    /**
     * Method that executes a preparedStatement and returns a 2d arraylist of data
     * and metadata
     *
     * @param query
     * @param values
     */
    public ArrayList<ArrayList<String>> getData(String query, String... values) throws DLException {
        ArrayList<ArrayList<String>> wrapper = new ArrayList<>();
        ArrayList<String> row_holder = new ArrayList<>();

        try {
            PreparedStatement stmt = prepare(query, values);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    row_holder.add(resultSet.getString(i));
                }
                wrapper.add(row_holder);
            }
            stmt.close();        
         } catch(SQLException e) {
            System.out.println("Couldn't retrieve data from the database.");
            ArrayList<String> logMessages = new ArrayList<String>();
            logMessages.add("Exception in the getData method");
            logMessages.add("User: " + id);
            logMessages.add("SQL statement: " + query);

            throw new DLException(e, logMessages);

        } catch(Exception e) {
            System.out.println("Couldn't retrieve data from the database.");
            ArrayList<String> logMessages = new ArrayList<String>();
            logMessages.add("Exception in the getData method");
            logMessages.add("User: " + id);
            logMessages.add("SQL statement: " + query);

            throw new DLException(e, logMessages);

        }

        return wrapper;
    } // end of getData

    public ArrayList<ArrayList<String>> getData(String sqlQuery, ArrayList<String> vals) throws DLException{

        ArrayList<ArrayList<String>> rowCollection = new ArrayList<ArrayList<String>>();
        try {
            PreparedStatement prepStmt = prepare(sqlQuery, vals);
            ResultSet rs = prepStmt.executeQuery();
            ResultSetMetaData rsmeta = rs.getMetaData();

            int numCols = rsmeta.getColumnCount();
            // boolean headingsAdded = false;

            while ( rs.next() ) {
                ArrayList<String> rowList = new ArrayList<String>();
                // ArrayList<String> nameList = new ArrayList<String>();
                // ArrayList<String> sizeList = new ArrayList<String>();


                for (int i = 0; i < numCols; i++) {
                    String data = rs.getString(i+1);
                    rowList.add(data);
                }

                rowCollection.add(rowList);
            }
        } catch(SQLException e) {
            System.out.println("Error: failure to accomplish task.");
            ArrayList<String> logMessages = new ArrayList<String>();
            logMessages.add("Exception in the getData method");
            logMessages.add("User: " + id);
            logMessages.add("SQL statement: " + sqlQuery);

            throw new DLException(e, logMessages);

        } catch(Exception ex) {}

        return rowCollection;
    }

    /**
     * Method that executes a preparedStatement and returns an the number of rows
     * affected
     *
     * @param query
     * @param values
     */
    public int setData(String query, String... values) {
        int rc = executeStmt(query, values);
        return rc;
    } // end of setData

    public int setData(String sqlStmnt) throws DLException{

        int numAffected = -1;
        try {
            Statement stmnt = conn.createStatement();

            numAffected = stmnt.executeUpdate(sqlStmnt);
        } catch(SQLException e) {
            numAffected = -1;
            System.out.println("Error: failure to accomplish task.");

            ArrayList<String> logMessages = new ArrayList<String>();
            logMessages.add("Exception in the setData method");
            logMessages.add("User: " + id);
            logMessages.add("SQL statement: " + sqlStmnt);

            throw new DLException(e, logMessages);

        } catch(Exception ex) {}

        return numAffected;
    }

    public int setData(String sqlStmnt, ArrayList<String> vals) throws DLException {

        int numAffected = -1;
        try {
            numAffected = executeStmt(sqlStmnt, vals);
        } catch(Exception e) {
            numAffected = -1;
            System.out.println("Error: failure to accomplish task.");

            ArrayList<String> logMessages = new ArrayList<String>();
            logMessages.add("Exception in the setData method");
            logMessages.add("User: " + id);
            logMessages.add("SQL statement: " + sqlStmnt);

            throw new DLException(e, logMessages);
        }

        return numAffected;

    }

    /**
     * Method that creates and returns a prepared statement
     *
     * @param query
     * @param values
     */
    private PreparedStatement prepare(String query, String... values) {

        try {
            int counter = 1;
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (String value : values) {
                pstmt.setString(counter, value);
                counter++;
            }
            return pstmt;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("Issue while creating the statement. Check your connection and values");
            return null;
        }

    } // end of prepare

    private PreparedStatement prepare(String queryString, ArrayList<String> vals) throws DLException {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(queryString);

            for(int i=0; i<vals.size(); i++) {
                stmt.setInt(i+1, Integer.parseInt(vals.get(i)));
            }
        } catch(SQLException e) {
            System.out.println("Error: failure to accomplish task.");
            ArrayList<String> logMessages = new ArrayList<String>();
            logMessages.add("Exception in the prepare method");

            throw new DLException(e, logMessages);
        }
        return stmt;
    }

    /**
     * Method that executes a preparedStatement and returns an the number of rows
     * affected
     *
     * @param query
     * @param values
     */
    public int executeStmt(String query, String... values) {
        try {
            PreparedStatement pstmt = prepare(query, values);
            System.out.println("Prepared statement: " + pstmt);
            int rc = pstmt.executeUpdate();
            System.out.println("rc: " + rc);
            pstmt.close();
            return rc;
        } catch (SQLException sqle) {
            System.out.println("Issue while executing the query. Check your connection and query");
            return -1;
        } catch (Exception e) {
            System.out.println("Issue while executing the query. Check your connection and query");
            new DLException(e);
            return -1;
        }
    } // end of executeStmt

    public int executeStmt(String sqlStmnt, ArrayList<String> vals) throws DLException{
        int numAffected = -1;
        try {
            PreparedStatement prepStmt = prepare(sqlStmnt, vals);

            numAffected = prepStmt.executeUpdate();
        } catch(SQLException e) {
            numAffected = -1;
            System.out.println("Error: failure to accomplish task.");

            ArrayList<String> logMessages = new ArrayList<String>();
            logMessages.add("Exception in the executeStmt method");
            logMessages.add("User: " + id);
            logMessages.add("SQL statement: " + sqlStmnt);

            throw new DLException(e, logMessages);
        } catch(Exception ex) {}

        return numAffected;
    }

    /**
     * Method that starts the transaction
     */
    public void startTrans() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException sqle) {
            System.out.println("Issue while starting the transaction. Check your connection.");
        }
    }

    /**
     * Method that ends the transaction
     */
    public void endTrans() {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException sqle) {
            System.out.println("Issue while ending the transaction. Check your connection.");
        }
    }

    /**
     * Method that executes a rollback
     */
    public void rollbackTrans() {
        try {
            conn.rollback();
        }

        catch (SQLException sqle) {
            System.out.println("Issue while executes the rollback. Check your connection.");
        }
    }

    /**
     * Method that executes a commit
     */
    public void commitTrans() {
        try {
            conn.commit();
        } catch (SQLException sqle) {
            System.out.println("Issue while executes the rollback. Check your connection.");
        }
    }

} // end of class
