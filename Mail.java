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
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport; 

public class Mail {

   // email info
   private String password = "";
   private String recipient = "";
   private String sender = "iste330@gmail.com";
   private String senderPassword = "emailPassword";
   private String host = "smtp.gmail.com";
   
   /**
    * Constructor for Mail class- sets password to send and recipient email
    * @param password
    * @param recipient
    */
   public Mail(String password, String recipient) {
      this.password = password;
      this.recipient = recipient;
   }
   
   /**
    * sendMessage method that constructs the email and sends it to the 
    */
   public void sendMessage() throws DLException{
      // adjust mail properties
      Properties properties = System.getProperties();
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.auth", "true");
      properties.setProperty("mail.smtp.host", host);
      Session session = Session.getInstance(properties,
               new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(sender, senderPassword);
                  }
               });
      
      try { 
         // MimeMessage object. 
         MimeMessage message = new MimeMessage(session); 
           
         // Set From Field: adding senders email to from field. 
         message.setFrom(new InternetAddress(sender)); 
           
         // Set To Field: adding recipient's email to from field. 
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
           
         // Set Subject: subject of the email 
         message.setSubject("Reset Password Request"); 
           
         // set body of the email. 
         message.setText("Here is your temporary password: " + password + "\nIt will expire in 5 minutes."); 
           
         // Send email. 
         Transport.send(message); 
         System.out.println("Mail successfully sent");
         
      } catch (MessagingException mex) { 
         new DLException(mex);
         System.out.println("Something went wrong in sending an email");
      } 
   }
}