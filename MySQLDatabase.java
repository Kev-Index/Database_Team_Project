import java.sql.*;
import java.util.*;

public class MySQLDatabase {

   private String host = "";
   private String user = "";
   private String password = "";
   private Connection conn = null;  
   
   public MySQLDatabase() {
      // host = "jdbc:mysql://simon.ist.rit.edu:3306/networx_ser";
      host = "jdbc:mysql://localhost/csm?autoReconnect=true&useSSL=false";
   	  user = "root";
   	  password = "yourpassword";
   }
   
   public MySQLDatabase(String host, String user, String password) {
      this.host = host;
      this.user = user;
      this.password = password;
   }
   
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

   public boolean connect() throws DLException {
      boolean didItWork = false;
      try {
         conn = DriverManager.getConnection(host, user, password);
         didItWork = true;
      } catch(SQLException e){
         didItWork = false;
         System.out.println("Error: failure to accomplish task.");
         
         ArrayList<String> logMessages = new ArrayList<String>();
         logMessages.add("Exception in the connect method");
         logMessages.add("User: " + user);
         logMessages.add("Host: " + host);
         
         throw new DLException(e, logMessages);
         
         
      } catch(Exception ex) { }
      return didItWork;
   }
   
   public boolean close() throws DLException{
      boolean didItWork = false;
      if(conn != null) {
         try {
            conn.close();
            didItWork=true;
         } catch(SQLException e) {
            didItWork=false;
            System.out.println("Error: failure to accomplish task.");
            
            
            throw new DLException(e);
            
         } catch(Exception ex) {}
      }
      return didItWork;
   }
      
   public ArrayList<ArrayList<String>> getData(String sqlStmnt, boolean includeColumnData) throws DLException{
      ArrayList<ArrayList<String>> rowCollection = new ArrayList<ArrayList<String>>();
      try {
         Statement stmnt = conn.createStatement();
         ResultSet rs = stmnt.executeQuery(sqlStmnt);
         
         ResultSetMetaData rsmeta = rs.getMetaData();
         
         int numCols = rsmeta.getColumnCount();
         
         boolean headingsAdded = false;
         
         while ( rs.next() ) {
            ArrayList<String> rowList = new ArrayList<String>();
            ArrayList<String> nameList = new ArrayList<String>();
            ArrayList<String> sizeList = new ArrayList<String>();
                              
            if(includeColumnData && !headingsAdded) { 
               
               for (int i = 0; i < numCols; i++) {
                  String name = rsmeta.getColumnName(i+1);
                  nameList.add(name);
               }
               rowCollection.add(nameList);
               
               for (int i = 0; i < numCols; i++) {
                  int size = rsmeta.getPrecision(i+1);
                  sizeList.add(Integer.toString(size));
               }
               rowCollection.add(sizeList);
               headingsAdded = true;
            }
            
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
         logMessages.add("User: " + user);
         logMessages.add("SQL statement: " + sqlStmnt);
         logMessages.add("includeColumnData: " + includeColumnData);
         
         throw new DLException(e, logMessages);
         
      } catch(Exception ex) {
         ex.printStackTrace();
      }
      
      return rowCollection;
   }
   
   public ArrayList<ArrayList<String>> getData(String sqlQuery, ArrayList<String> vals) throws DLException{

      connect();
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
         logMessages.add("User: " + user);
         logMessages.add("SQL statement: " + sqlQuery);
         
         throw new DLException(e, logMessages);
         
      } catch(Exception ex) {}

      close();
      
      return rowCollection;
   }
   
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
         logMessages.add("User: " + user);
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
         logMessages.add("User: " + user);
         logMessages.add("SQL statement: " + sqlStmnt);
         
         throw new DLException(e, logMessages);
      }   
      
      return numAffected;
   
   }
   
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
         logMessages.add("User: " + user);
         logMessages.add("SQL statement: " + sqlStmnt);
         
         throw new DLException(e, logMessages);
         
      } catch(Exception ex) {}
      
      return numAffected;
   }

   // --------------------------------------ONLY INCLUDE THESE IF WE DECIDE TO DO TRANSACTIONS -- AND FIX UP THE TRY/CATCH
//    public void startTrans() {
//       try {
//          connect();
//          conn.setAutoCommit(false);
//       } 
//       catch(DLException dle) {
//          System.out.println("OOPS-- SHE DON'T WORK");
//       } 
//       catch(SQLException sqle) {
//          System.out.println("OOPS-- SHE DON'T WORK");
//       }
//    }
//    public void endTrans() {
//       try {
//          conn.commit();
//          conn.setAutoCommit(true);
//          close();
//       } 
//       catch(DLException dle) {
//          System.out.println("OOPS-- SHE DON'T WORK");
//       } 
//       catch(SQLException sqle) {
//          sqle.printStackTrace();
//          System.out.println("OOPS-- SHE DON'T WORK");
//       }
//    }
//    public void rollbackTrans() {
//       try {
//          conn.rollback();
//          conn.setAutoCommit(true);
//          close();
//       }
//       catch(DLException dle) {
//          System.out.println("OOPS-- SHE DON'T WORK");
//       } 
//       catch(SQLException sqle) {
//          System.out.println("OOPS-- SHE DON'T WORK");
//       }
//    }
   
}