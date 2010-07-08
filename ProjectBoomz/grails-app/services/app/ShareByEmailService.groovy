package app

import javax.mail.*
import javax.mail.internet.*
import java.util.*

class ShareByEmailService
{

    static transactional = true

    def boolean postMail(String[] recipients, String subject, String message , String fromName, String fromAddress)
    {
        // Create a session
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");

        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(props, auth);

        // We build the message and send it!
        try
        {
            // Create a message
            Message msg = new MimeMessage(session);

            // Set sender
            // InternetAddress addressFrom = new InternetAddress("collaborez.mail@gmail.com" , "Project Boomz");
            InternetAddress addressFrom = new InternetAddress(fromAddress , fromName);
            msg.setFrom(addressFrom);
            msg.setReplyTo(   new InternetAddress(fromAddress, fromName)   )

            // Set recipients
            InternetAddress[] recipientList = new InternetAddress[recipients.length];
            for(int i = 0; i < recipients.length; i++)
            {
                recipientList[i] = new InternetAddress(recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, recipientList);

            // Set the subject
            msg.setSubject(subject);

            // Set the message body
            msg.setContent(message, "text/plain");

            // Finally, send the email
            Transport.send(msg);
            return true;
        }
        catch(MessagingException e)
        {
            e.printStackTrace();
            return false;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}

class SMTPAuthenticator extends javax.mail.Authenticator
{
    def PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication("collaborez.mail", "raiseyourhandsupintheairandscream");
    }
}


