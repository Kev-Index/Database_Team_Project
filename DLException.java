import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DLException extends Exception{

   private static final long serialVersionUID = 1L;
   Exception e = null;
   ArrayList<String> messages = null;

   public DLException(Exception e) {
      super(e);
      this.e = e;
      writeLog();
   }
   
   public DLException(Exception e, ArrayList<String> messages) {
      super(e);
      this.e = e;
      this.messages = messages;
      writeLog();
   }
   
   public void writeLog() {
   
      // get date
      LocalDateTime date = LocalDateTime.now();
      DateTimeFormatter formatObject = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
      String formattedDate = date.format(formatObject);
      // get info
      String logMessage = "New Exception logged at time: ";
      logMessage += formattedDate + "\n";
      if(messages != null) {
         for(int i = 0; i<messages.size(); i++) {
            logMessage += messages.get(i) + "\n";
         }
      }
      
      // get stack trace and convert to String
      StringWriter sw = new StringWriter();
      e.printStackTrace(new PrintWriter(sw));
      String exceptionAsString = sw.toString();
      logMessage += "Stack Trace: " + exceptionAsString + "\n";
      
   
      // create the file
      try {
         File log = new File("log.txt");
         // if the file has not yet been created, create the file
         if (log.createNewFile()) {
           System.out.println("Log file created: " + log.getName());
         } else {
           System.out.println("Log file already exists.");
         }
       } catch (IOException e) {
         System.out.println("An error occurred in creating the log file.");
         e.printStackTrace();
       }
       
       // write to file
       try {
         FileWriter fileWriter = new FileWriter("log.txt", true);
         fileWriter.write(logMessage);
         fileWriter.close();
         System.out.println("Wrote to log file.");
       } catch (IOException e) {
         System.out.println("An error occurred in writing to the log file.");
         e.printStackTrace();
       }
   }

}